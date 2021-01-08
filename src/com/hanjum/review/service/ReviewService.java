package com.hanjum.review.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.hanjum.review.dao.ReviewDAO;
import com.hanjum.review.vo.ReviewBean;

import static com.hanjum.db.JdbcUtil.*;
public class ReviewService {

	public boolean rigisterWriter(ReviewBean reviewBean) throws Exception{
		System.out.println("reviewrigisterWriter");
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);
		
		int insertCount = reviewDAO.insertArticle(reviewBean);
		
		if(insertCount>0) {
			commit(con);
			isWriteSuccess = true;
		}else {
			rollback(con);
		}
		close(con);
		return isWriteSuccess;
	}
	
	public int getListCount(String user_id) throws Exception{
		System.out.println("ReviewListService");
		int listCount = 0;
		
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);
		listCount = reviewDAO.selectListCount(user_id);
		close(con);
		return listCount;
				
	}

	public ArrayList<ReviewBean> getArticleList(int page, int limit, String user_id) {
		ArrayList<ReviewBean> articleList = null;
		
		Connection con = getConnection();
	    ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);
		articleList = reviewDAO.selectArticleList(page, limit, user_id);
		close(con);
		
		return articleList;
	}

	// 리뷰 수정 작업 전 작성자 확인을 위한 아이디 비교
	public boolean isArticleWriter(int review_id, String review_from_id) {
		
		
				boolean isArticleWriter = false;
				Connection con = getConnection();
				ReviewDAO reviewDAO = ReviewDAO.getInstance();
				reviewDAO.setConnection(con);
				System.out.println(review_id);
				isArticleWriter = reviewDAO.isArticleReviewWriter(review_id, review_from_id);
				
				close(con);
				
				return isArticleWriter;
	}

	
	
	// 리뷰 수정
	public boolean uodateArticle(ReviewBean article) {
		
				boolean isUpdateSuccess = false;
				Connection con = getConnection();
				
			
				ReviewDAO reviewDAO = ReviewDAO.getInstance();
				
				
				reviewDAO.setConnection(con);
				
		
				int updateCount = reviewDAO.updateArticle(article);
				
				if(updateCount > 0) {
					commit(con);
					isUpdateSuccess = true;
				} else {
					rollback(con);
				}
				
				
				close(con);
				
				return isUpdateSuccess;
	}

	// 리뷰 삭제
	public static boolean removeArticle(int review_id) {
		boolean isDeleteSuccess = false;
		Connection con = getConnection();
		
	
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);
		int deleteCount = reviewDAO.deleteArticle(review_id);
		
		if(deleteCount > 0) {
			commit(con);
			isDeleteSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isDeleteSuccess;
	}

	public ReviewBean getArticle(int review_id) {

		Connection con = getConnection();
		
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		
		
		reviewDAO.setConnection(con);
		
		ReviewBean article = reviewDAO.selectArticle(review_id);
		System.out.println(article.getReview_id());
		
		
		close(con);
		
		return article;
	}
	
	public int checkWriteReview(String editor_id, String user_id) {
		System.out.println("checkWriteReview 서비스");
		Connection con = getConnection();
				
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);
		int check = reviewDAO.checkWriteReview(editor_id, user_id); 
		close(con);
		return check;
	}
	
	public int getReviewAvg(String user_id) {
		System.out.println("getReviewAvg 서비스");
		Connection con = getConnection();
		
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);
		int avg = reviewDAO.getReviewAvg(user_id);
		
		close(con);
		return avg;
	}
}
	


