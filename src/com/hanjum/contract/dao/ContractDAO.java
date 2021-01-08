package com.hanjum.contract.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static com.hanjum.db.JdbcUtil.*;

import com.hanjum.contract.action.ContractSearchAction;
import com.hanjum.contract.vo.ContractBean;
import com.hanjum.contract.vo.ContractSearchBean;
import com.hanjum.user.dao.UserDAO;

public class ContractDAO {

	private ContractDAO() {
	}

	private static ContractDAO instance = new ContractDAO();

	public static ContractDAO getinstance() {
		return instance;
	}

	Connection con;

	public void setConnection(Connection con) {
		this.con = con;

	}

	public ArrayList<ContractBean> selectContractList(int page, int limit) {

		ArrayList<ContractBean> contractList = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int startRow = (page - 1) * limit;

		try {
//			String sql = "SELECT c.contract_id, b. board_subject, c.contract_price, c.contract_creator, c.contract_editor, c.contract_address, c.contract_begin_date, c.contract_end_date, c.contract_status, c.board_id, (SELECT COUNT(waiting_id) FROM waiting WHERE board_id = c.board_id) AS wating  \r\n"
//					+ "FROM contract as c \r\n" + "left outer join board as b\r\n"
//					+ "on c.board_id = b.board_id order by contract_id desc LIMIT ?,?";
			
			String sql = " SELECT c.contract_id, b.board_subject, c.contract_price, c.contract_creator, c.contract_editor, c.contract_address, c.contract_begin_date, c.contract_end_date, c.contract_status, c.board_id, (SELECT COUNT(waiting_id) FROM waiting WHERE board_id = c.board_id) AS waiting " + 
					" FROM contract as c left outer join board as b" + 
					" on c.board_id = b.board_id order by contract_id desc LIMIT ?,?";
			
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);

			rs = pstmt.executeQuery();

			contractList = new ArrayList<ContractBean>();

			while (rs.next()) {
				ContractBean cb = new ContractBean();

				cb.setContract_id(rs.getInt("contract_id"));
				cb.setBoard_subject(rs.getString("board_subject"));
				cb.setContract_price(rs.getInt("contract_price"));
				cb.setContract_creator(rs.getString("contract_creator"));
				cb.setContract_editor(rs.getString("contract_editor"));
				cb.setContract_address(rs.getString("contract_address"));
				cb.setContract_begin_date(rs.getTimestamp("contract_begin_date"));
				cb.setContract_end_date(rs.getTimestamp("contract_end_date"));
				cb.setContract_status(rs.getInt("contract_status"));
				cb.setBoard_id(rs.getInt("board_id"));
				cb.setWating(rs.getInt("waiting"));

				contractList.add(cb);
			}
		} catch (Exception e) {
			System.out.println("selectContractList - 오류 : " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return contractList;

	}

	public int selectListCount() {
		int listCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT COUNT(contract_id) FROM contract";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// 조회 결과가 있을 경우(= 게시물이 하나라도 존재하는 경우)
			// => 게시물 수를 listCount 에 저장
			if (rs.next()) {
				listCount = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println("selectListCount() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			// 자원 반환
			// 주의! DAO 클래스 내에서 Connection 객체 반환 금지!
			close(rs);
			close(pstmt);
		}

		return listCount;
	}

	public int insertContract(ContractBean cBean) {
		// Service 클래스로부터 BoardBean 객체를 전달받아
		// DB의 board 테이블에 INSERT 작업 수행하고 결과(int타입)를 리턴
		System.out.println("ContractDAO - insertContract()");
		int insertCount = 0; // INSERT 작업 수행 결과를 저장할 변수

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int num = 1; // 새 글 번호를 저장할 변수

		try {
			// 현재 게시물 번호(board_num) 중 가장 큰 번호를 조회하여
			// 해당 번호 + 1 값을 새 글 번호(num)으로 저장
			String sql = "SELECT MAX(contract_id) FROM contract";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1) + 1; // 새 글 번호 만들기
			}

			sql = "INSERT INTO contract VALUES (?,?,?,?,now(),now(),?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			// BoardBean 객체로부터 데이터를 꺼내서 쿼리문 ? 대체
			pstmt.setInt(1, num); // 글번호
			pstmt.setString(2, cBean.getBoard_subject());
			pstmt.setString(3, cBean.getContract_creator());
			pstmt.setString(4, cBean.getContract_editor());
			pstmt.setInt(5, cBean.getContract_price());
			pstmt.setInt(6, cBean.getContract_status()); // 참조글 번호(새 글이므로 자신이 참조글이 됨)
			pstmt.setString(7, cBean.getBoard_ed_address());
			pstmt.setInt(8, cBean.getBoard_id());

			// INSERT 구문 실행 결과값을 int형 변수 insertCount 에 저장
			insertCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("insertArticle() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			// 자원 반환
			// 주의! DAO 클래스 내에서 Connection 객체 반환 금지!
			close(rs);
			close(pstmt);
		}

		return insertCount;

	}

	//----------------------------SearchService 메서드----------------------------------------------------------------
	//
	   public int selectSearchListCount(ContractSearchBean csb) {
	      int listCount = 0;

	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      ContractBean cBean = new ContractBean();

	      try {
	         String sql = "SELECT COUNT(contract_id) FROM contract as c left outer join board as b on c.board_id = b.board_id ";

//	         ---------------------검색 조건문 추가 --------------------------
	         // 계약금액 쿼리문
	         sql = sql.concat("WHERE ");
	         sql = sql.concat(csb.getContract_pay1() + "<=");
	         sql = sql.concat(" contract_price AND ");
	         sql = sql.concat("contract_price <=" + csb.getContract_pay2());

	         // 라디오박스 지역 체크에 따른 조건문 추가
	         if (csb.getRegion() != null) {

	            sql = sql.concat(" AND contract_address in('");
	            for (int i = 0; i < csb.getRegion().length - 1; i++) {
	               sql = sql.concat(csb.getRegion()[i] + "', '");
	            }
	            sql = sql.concat(csb.getRegion()[csb.getRegion().length - 1] + "') ");

	         }

	         // 날짜 선택에 따른 조건문 추가
	         if (csb.getDate_check().equals("on")) {
	            sql = sql.concat(" AND contract_begin_date >= ");
	            sql = sql.concat("'" + csb.getSearch_begin_date() + "' ");
	            sql = sql.concat(" AND contract_end_date <= ");
	            sql = sql.concat("'" + csb.getSearch_end_date() + "' ");
	         }

	         // 계약 상태 조건 검색
	         if (csb.getContract_status() != null) {

	            sql = sql.concat(" AND contract_status in(");

	            for (int i = 0; i < csb.getContract_status().length - 1; i++) {
	               sql = sql.concat(csb.getContract_status()[i] + ", ");
	            }
	            sql = sql.concat(csb.getContract_status()[csb.getContract_status().length - 1]) + ") ";
	         }

	         // 검색 타입과 검색명 으로 조회
	      
	            
	            
	            
	            if (csb.getSearchtype() != null && csb.getSearch_word() != null && csb.getSearch_word() != "") {
	                                 
	               sql = sql.concat(" AND ");

	               if (csb.getSearchtype().equals("contract_id")) {
	                  sql = sql.concat(csb.getSearchtype() + " = '" + csb.getSearch_word() + "'");
	               }else if(csb.getSearchtype().equals("board_id")) {
	                  sql = sql.concat("b."+csb.getSearchtype() + " = '" + csb.getSearch_word() + "'");
	               }
	               else if (csb.getSearchtype().equals("board_subject")) {
	                  sql = sql.concat("b." + csb.getSearchtype() + " like "); // board_subject는 앞에 AS값을 붙여야 오류가 안남
	                  sql = sql.concat("'%" + csb.getSearch_word() + "%' ");
	               } else {
	                  sql = sql.concat(csb.getSearchtype() + " like ");
	                  sql = sql.concat("'%" + csb.getSearch_word() + "%' ");
	               }
	            

	         }

//	         ---------------------검색 조건문 종료 --------------------------   

	         System.out.println("count sql : " + sql);

	         pstmt = con.prepareStatement(sql);

	         rs = pstmt.executeQuery();

	         // 조회 결과가 있을 경우(= 게시물이 하나라도 존재하는 경우)
	         // => 게시물 수를 listCount 에 저장
	         if (rs.next()) {
	            listCount = rs.getInt(1);
	         }

	      } catch (SQLException e) {
	         System.out.println("selectSearchListCount() 오류! - " + e.getMessage());
	         e.printStackTrace();
	      } finally {
	         // 자원 반환
	         // 주의! DAO 클래스 내에서 Connection 객체 반환 금지!
	         close(rs);
	         close(pstmt);
	      }
	      System.out.println("리스트 카운트" + listCount);

	      return listCount;
	   }

	   public ArrayList<ContractBean> searchContractList(int page, int limit, ContractSearchBean csb) {
	      ArrayList<ContractBean> contractList = null;

	      PreparedStatement pstmt = null;
	      ResultSet rs = null;

	      int startRow = (page - 1) * limit;

	      try {
	         String sql = "SELECT c.contract_id, b. board_subject, c.contract_price, c.contract_creator, c.contract_editor, c.contract_address, c.contract_begin_date, c.contract_end_date, c.contract_status, c.board_id, (SELECT COUNT(waiting_id) FROM waiting WHERE board_id = c.board_id) AS waiting   \r\n"
	               + "FROM contract as c \r\n" + "left outer join board as b on c.board_id = b.board_id ";

//	         ---------------------검색 조건문 추가 --------------------------
	         // 계약금액 쿼리문
	         sql = sql.concat("WHERE ");
	         sql = sql.concat(csb.getContract_pay1() + "<=");
	         sql = sql.concat(" contract_price AND ");
	         sql = sql.concat("contract_price <=" + csb.getContract_pay2());

	         // 라디오박스 지역 체크에 따른 조건문 추가
	         if (csb.getRegion() != null) {

	            sql = sql.concat(" AND contract_address in('");
	            for (int i = 0; i < csb.getRegion().length - 1; i++) {
	               sql = sql.concat(csb.getRegion()[i] + "', '");
	            }
	            sql = sql.concat(csb.getRegion()[csb.getRegion().length - 1] + "') ");

	         }

	         // 날짜 선택에 따른 조건문 추가
	         if (csb.getDate_check().equals("on")) {
	            sql = sql.concat(" AND contract_begin_date >= ");
	            sql = sql.concat("'" + csb.getSearch_begin_date() + "' ");
	            sql = sql.concat(" AND contract_end_date <= ");
	            sql = sql.concat("'" + csb.getSearch_end_date() + "' ");
	         }

	         // 계약 상태 조건 검색
	         if (csb.getContract_status() != null) {

	            sql = sql.concat(" AND contract_status in(");

	            for (int i = 0; i < csb.getContract_status().length - 1; i++) {
	               sql = sql.concat(csb.getContract_status()[i] + ", ");
	            }
	            sql = sql.concat(csb.getContract_status()[csb.getContract_status().length - 1]) + ") ";
	         }

	         // 검색 타입과 검색명 으로 조회
	      
	            
	            
	            
	            if (csb.getSearchtype() != null && csb.getSearch_word() != null && csb.getSearch_word() != "") {
	                                 
	               sql = sql.concat(" AND ");

	               if (csb.getSearchtype().equals("contract_id")) {
	                  sql = sql.concat(csb.getSearchtype() + " = '" + csb.getSearch_word() + "'");
	               }else if(csb.getSearchtype().equals("board_id")) {
	                  sql = sql.concat("b."+csb.getSearchtype() + " = '" + csb.getSearch_word() + "'");
	               }
	               else if (csb.getSearchtype().equals("board_subject")) {
	                  sql = sql.concat("b." + csb.getSearchtype() + " like "); // board_subject는 앞에 AS값을 붙여야 오류가 안남
	                  sql = sql.concat("'%" + csb.getSearch_word() + "%' ");
	               } else {
	                  sql = sql.concat(csb.getSearchtype() + " like ");
	                  sql = sql.concat("'%" + csb.getSearch_word() + "%' ");
	               }
	            

	         }

//	         ---------------------검색 조건문 종료 --------------------------   

	         sql = sql.concat(" order by contract_id desc LIMIT ?,? ");

	         System.out.println("실제 리스트 sql 문 : " + sql);

	         pstmt = con.prepareStatement(sql);
	         pstmt.setInt(1, startRow);
	         pstmt.setInt(2, limit);

	         rs = pstmt.executeQuery();

	         contractList = new ArrayList<ContractBean>();

	         while (rs.next()) {
	            ContractBean cb = new ContractBean();

	            cb.setContract_id(rs.getInt("contract_id"));
	            cb.setBoard_subject(rs.getString("board_subject"));
	            cb.setContract_price(rs.getInt("contract_price"));
	            cb.setContract_creator(rs.getString("contract_creator"));
	            cb.setContract_editor(rs.getString("contract_editor"));
	            cb.setContract_address(rs.getString("contract_address"));
	            cb.setContract_begin_date(rs.getTimestamp("contract_begin_date"));
	            cb.setContract_end_date(rs.getTimestamp("contract_end_date"));
	            cb.setContract_status(rs.getInt("contract_status"));
	            cb.setBoard_id(rs.getInt("board_id"));

	            contractList.add(cb);
	         }
	      } catch (Exception e) {
	         System.out.println("selectContractList - 오류 : " + e.getMessage());
	         e.printStackTrace();
	      } finally {
	         close(pstmt);
	         close(rs);
	      }

	      return contractList;
	   }

	
	public int selectContractStatus(int board_id) {
		System.out.println("ContractDAO - selectContractStatus()");
		int status = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT contract_status FROM contract WHERE board_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				status = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("selectContractStatus 오류 - "+e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return status;
	}
	
	public int updateContract(ContractBean cBean) {
		// BoardBean 객체에 저장된 수정 내용(작성자, 제목, 내용)을 사용하여
		// 글번호(board_num)에 해당하는 레코드를 수정 후 결과 리턴
		int updateCount = 0;

		PreparedStatement pstmt = null;

		try {
			String sql = "UPDATE contract " + "SET contract_price=?, contract_status=? " + "WHERE contract_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cBean.getContract_price());
			pstmt.setInt(2, cBean.getContract_status());
			pstmt.setInt(3, cBean.getContract_id());
			updateCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("updateContract() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return updateCount;
	}
	
	public int updateStatus(int board_id, int contract_status) {
		System.out.println("ContractDAO - updateStatus()");
		int updateCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "UPDATE contract SET contract_status = ? WHERE board_id = ? AND contract_status != 4";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, contract_status);
			pstmt.setInt(2, board_id);
			updateCount = pstmt.executeUpdate();
			
			if(updateCount > 0) { // 1. 업데이트가 성공했을때
				// 2.계약이 완료 될 경우에만
				if(contract_status == 3) {
					sql = "select * from contract where board_id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, board_id);
					rs=pstmt.executeQuery();
					
					if(rs.next()) {
						UserDAO userDAO = UserDAO.getInstance();
						userDAO.setConnection(con);
						userDAO.userExp(rs.getString("contract_creator"), 100); // 거래완료 Exp100 부여
						userDAO.userExp(rs.getString("contract_editor"), 100); 
						int count = 0;
						// 거래건수 추가 2번반복
						for(int i = 1 ; i<3 ; i ++) {
							switch (i) {
							case 1:
								count = getContractSuccessCount(rs.getString("contract_creator"));
								sql = "update user set user_project_count=? where user_id =?";
								pstmt = con.prepareStatement(sql);
								pstmt.setInt(1, count);
								pstmt.setString(2, rs.getString("contract_creator"));
								pstmt.executeUpdate();
								break;
							case 2:
								count = getContractSuccessCount(rs.getString("contract_editor"));
								sql = "update user set user_project_count=? where user_id =?";
								pstmt = con.prepareStatement(sql);
								pstmt.setInt(1, count);
								pstmt.setString(2, rs.getString("contract_editor"));
								pstmt.executeUpdate();
								break;
							}
						}
					}
					close(rs);
				}
			}
			
		} catch (Exception e) {
			System.out.println("updateStatus 오류 - "+e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateCount;
	}
	
	public int deleteContract(int contract_id) {
		int deleteCount = 0;

		PreparedStatement pstmt = null;

		try {
			String sql = "DELETE FROM contract WHERE contract_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, contract_id);
			deleteCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("deleteContract() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return deleteCount;
	}
	
	public int deleteContract(int board_id, String contract_editor) { // 지원취소 했을때 삭제
		System.out.println("ContractDAO - deleteContract()");
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		try {
			String sql = "DELETE FROM contract WHERE board_id = ? AND contract_editor = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			pstmt.setString(2, contract_editor);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("deleteContract 오류 - "+e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}
	
	public int deleteContractOther(int board_id, String contract_editor) { // 수락한 지원자 외 전체 삭제
		System.out.println("ContractDAO - deleteContract()");
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		try {
			String sql = "DELETE FROM contract WHERE board_id = ? AND contract_editor NOT IN (?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			pstmt.setString(2, contract_editor);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("deleteContract 오류 - "+e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}
	
	public ContractBean selectContractInfo(int board_id) {
		System.out.println("ContractDAO - deleteContract()");
		ContractBean contractBean = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM contract WHERE board_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				contractBean = new ContractBean();
				contractBean.setContract_id(rs.getInt("contract_id"));
				contractBean.setContract_address(rs.getString("contract_address"));
				contractBean.setContract_begin_date(rs.getTimestamp("contract_begin_date"));
				contractBean.setContract_creator(rs.getString("contract_creator"));
				contractBean.setContract_editor(rs.getString("contract_editor"));
				contractBean.setContract_end_date(rs.getTimestamp("contract_end_date"));
				contractBean.setContract_price(rs.getInt("contract_price"));
				contractBean.setContract_status(rs.getInt("contract_status"));
				contractBean.setBoard_id(rs.getInt("board_id"));
			}
		} catch (Exception e) {
			System.out.println("selectContractInfo 오류 - "+e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return contractBean;
		
	}
	
	public int checkSuccessEditor(String editor_id, String creator_id) {
		System.out.println("ContractDAO - checkSuccessEditor");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int check = 0;
		try {
			String sql = "SELECT count(contract_id) FROM contract WHERE contract_editor = ? AND contract_creator = ? AND contract_status = 3";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, editor_id);
			pstmt.setString(2, creator_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1)>0) {
					check = 1;
				}
			}
		} catch (Exception e) {
			System.out.println("checkSuccessEditor - 오류 "+e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return check;
	}
	
	public int getContractSuccessCount(String user_id) {
		System.out.println("ContractDAO - getContractSuccessCount");
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT COUNT(contract_id) FROM contract WHERE contract_editor = ? OR contract_creator = ? AND contract_status = 3";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getContractSuccessCount - 오류 "+e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return count;
	}
}