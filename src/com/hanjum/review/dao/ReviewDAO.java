package com.hanjum.review.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hanjum.review.vo.ReviewBean;
import com.hanjum.user.dao.UserDAO;

import static com.hanjum.db.JdbcUtil.*;

public class ReviewDAO {
private ReviewDAO() {}
	
	private static ReviewDAO instance = new ReviewDAO();

	public static ReviewDAO getInstance() {
		return instance;
	}
	

	Connection con; 
	public void setConnection(Connection con) {
		this.con = con;
	}

	// 리뷰 작성
	public int insertArticle(ReviewBean reviewBean) {
		System.out.println("reviewDAO");
		int insertCount=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 1;
		try {
			
			String sql = "select MAX(review_id) from review";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt(1) +1;
			}
			
			sql = "insert into review values(?,?,?,?,?,?,now(),?,?)";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, reviewBean.getUser_id());
			pstmt.setString(2, reviewBean.getReview_content());
			pstmt.setInt(3, reviewBean.getReview_speciality());
			pstmt.setInt(4, reviewBean.getReview_satisfaction());
			pstmt.setInt(5, reviewBean.getReview_positivity());
			pstmt.setInt(6, reviewBean.getReview_communication());
//			pstmt.setTimestamp(7, reviewBean.getReview_date());
			pstmt.setString(7, reviewBean.getReview_from_id());
			pstmt.setInt(8, num);
			
			insertCount = pstmt.executeUpdate();
			
			// 리뷰등록 성공 시 경험치 부여
			if(insertCount>0) {
				UserDAO userDAO = UserDAO.getInstance();
				userDAO.setConnection(con);
				userDAO.userExp(reviewBean.getReview_from_id(), 30); // 리뷰어 에게는 Exp30 고정적인수치 부여
				int score = reviewBean.getReview_speciality()+
						reviewBean.getReview_satisfaction()+
						reviewBean.getReview_positivity()+
						reviewBean.getReview_communication();
				score /= 4;
				userDAO.userExp(reviewBean.getUser_id(), score*10); // 리뷰 대상자에게는 평균점수*10 Exp 부여
			}
			
		}catch (SQLException e) {
			System.out.println("insertArticle() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return insertCount;
	}


	// 전체 리뷰수 조회
		public int selectListCount(String user_id) {
			int listCount = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
			
				String sql = "SELECT COUNT(review_id) FROM review WHERE user_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user_id);
				rs = pstmt.executeQuery();
				
				
				if(rs.next()) {
					listCount = rs.getInt(1);
				}
				
			} catch (SQLException e) {
				System.out.println("selectListCount() 오류! - " + e.getMessage());
				e.printStackTrace();
			} finally {
				
				close(rs);
				close(pstmt);
			}
			
			return listCount;
		}

		// 리뷰 목록 조회
		public ArrayList<ReviewBean> selectArticleList(int page, int limit, String user_id) {

			ArrayList<ReviewBean> articleList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
		
			int startRow = (page - 1) * limit;
			
			try {
				
				String sql = "SELECT * FROM review WHERE user_id = ? "
						+ "LIMIT ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user_id);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, limit);
				rs = pstmt.executeQuery();
				
				
				articleList = new ArrayList<ReviewBean>();
			
				while(rs.next()) {
					
					ReviewBean article = new ReviewBean();
					article.setUser_id(rs.getString("user_id"));
					article.setReview_content(rs.getString("review_content"));
					article.setReview_speciality(rs.getInt("review_speciality"));
					article.setReview_satisfaction(rs.getInt("review_satisfaction"));
					article.setReview_positivity(rs.getInt("review_positivity"));
					article.setReview_communication(rs.getInt("review_communication"));
					article.setReview_date(rs.getTimestamp("review_date"));
					article.setReview_from_id(rs.getString("review_from_id"));
					article.setReview_id(rs.getInt("review_id"));
					
					articleList.add(article);
				}
				
			} catch (SQLException e) {
				System.out.println("selectArticleList() 오류! - " + e.getMessage());
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return articleList;
		}
		
		// 리뷰작성자 확인
		public boolean isArticleReviewWriter(int review_id, String review_from_id) {
			
			boolean isArticleWriter = false;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT review_from_id FROM review WHERE review_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, review_id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					if(review_from_id.equals(rs.getString("review_from_id"))) {
						isArticleWriter = true;
					}
				}
				
			} catch (SQLException e) {
				System.out.println("isArticleReviewWriter() 오류! - " + e.getMessage());
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return isArticleWriter;
		}

		// 리뷰 수정
		public int updateArticle(ReviewBean article) {
			int updateCount = 0;
			
			PreparedStatement pstmt = null;
			
			try {
				String sql = "UPDATE review "
						+ "SET review_content=?,review_speciality=?,review_satisfaction=?, review_positivity=?, review_communication=?, review_date = now() "
						+ "WHERE review_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, article.getReview_content());
				pstmt.setInt(2, article.getReview_speciality());
				pstmt.setInt(3, article.getReview_satisfaction());
				pstmt.setInt(4, article.getReview_positivity());
				pstmt.setInt(5, article.getReview_communication());
				pstmt.setInt(6, article.getReview_id());
				updateCount = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("updateArticle() 오류! - " + e.getMessage());
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return updateCount;
		}

		// 리뷰삭제
		public int deleteArticle(int review_id) {
			int deleteCount = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select * from review where review_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, review_id);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					sql = "DELETE FROM review WHERE review_id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, review_id);
					deleteCount = pstmt.executeUpdate();
					if(deleteCount > 0) {
						UserDAO userDAO = UserDAO.getInstance();
						userDAO.setConnection(con);
						userDAO.userExp(rs.getString("review_from_id"), -30);; // 리뷰어에게 부여됐던 Exp30 삭감
						int score = rs.getInt("review_speciality")+
								rs.getInt("review_satisfaction")+
								rs.getInt("review_positivity")+
								rs.getInt("review_communication");
						score /= 4;
						score *= -1;
						userDAO.userExp(rs.getString("user_id"), score*10); // 리뷰 대상자에게 부여됐던 평균점수*10 Exp 삭감
					}
				}
				
			} catch (SQLException e) {
				System.out.println("deleteArticle() 오류! - " + e.getMessage());
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return deleteCount;
		
		}
		
		
		public ReviewBean selectArticle(int review_id) {
			// 글번호(board_num)에 해당하는 레코드를 SELECT
			// 조회 결과가 있을 경우 BoardBean 객체에 저장한 뒤 리턴
			ReviewBean article = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT * FROM review WHERE review_id=?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, review_id);
				rs = pstmt.executeQuery();
				
				// 게시물이 존재할 경우 BoardBean 객체를 생성하여 게시물 내용 저장
				if(rs.next()) {
					article = new ReviewBean();
					article.setReview_id(rs.getInt("review_id"));					
					article.setReview_content(rs.getString("review_content"));
					article.setReview_speciality(rs.getInt("review_speciality"));
					article.setReview_satisfaction(rs.getInt("review_satisfaction"));
					article.setReview_positivity(rs.getInt("review_positivity"));
					article.setReview_communication(rs.getInt("review_communication"));
					article.setReview_from_id(rs.getString("review_from_id"));
					// 임시 확인용 상세 내용 출력
//					System.out.println("글 제목 : " + article.getBoard_subject());
				}
					
				
			} catch (SQLException e) {
				System.out.println("selectArticle() 오류! - " + e.getMessage());
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			
			
			return article;
		}
		
		public int checkWriteReview(String editor_id, String user_id) {
			System.out.println("ReviewDAO - checkWriteReview");
			int check = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "SELECT count(review_id) FROM review WHERE review_from_id = ? AND user_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user_id);
				pstmt.setString(2, editor_id);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					if(rs.getInt(1)>0) {
						check=1;
					}
				}
			} catch (Exception e) {
				System.out.println("checkWriteReview 오류 - "+e.getMessage());
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
			return check;
		}
		
		public int getReviewAvg(String user_id) { // 리뷰 점수 평균 구해주기
			System.out.println("ReviewDAO - getReviewAvg");
			int avg = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "SELECT SUM((review_speciality + review_satisfaction + review_positivity + review_communication)/4)/count(review_id) " + 
						"FROM review WHERE user_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user_id);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					avg = rs.getInt(1);
				}
				
			} catch (Exception e) {
				System.out.println("getReviewAvg 오류 - "+e.getMessage());
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
			
			return avg;
		}
}
