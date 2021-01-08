package com.hanjum.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import com.hanjum.board.vo.BoardBean;
import com.hanjum.board.vo.ProjectBean;
import com.hanjum.board.vo.WaitingBean;

import static com.hanjum.db.JdbcUtil.*;
import com.hanjum.vo.Constant;

public class ProjectDAO {
	private ProjectDAO() {}
	
	private static ProjectDAO instance = new ProjectDAO();

	public static ProjectDAO getInstance() {
		return instance;
	}
	
	Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	
	// GET ======================================================================================
	
	public ProjectBean selectProjectInfo(BoardBean boardBean) { // 에디터 게시물 조회
		System.out.println("ProjectDAO - selectProjectInfo()");
		ProjectBean projectBean = null;
		if(boardBean instanceof ProjectBean) {
			projectBean = (ProjectBean)boardBean;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * from board_creator WHERE board_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, projectBean.getBoard_id());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				projectBean.setBoard_creator_cam_num(rs.getInt("board_creator_cam_num"));
				projectBean.setBoard_creator_content_detail(rs.getString("board_creator_content_detail"));
				projectBean.setBoard_creator_cre_max_price(rs.getInt("board_creator_cre_max_price"));
				projectBean.setBoard_creator_cre_min_price(rs.getInt("board_creator_cre_min_price"));
				projectBean.setBoard_creator_cre_ref(rs.getString("board_creator_cre_ref"));
				projectBean.setBoard_creator_edit_length(rs.getInt("board_creator_edit_length"));
				projectBean.setBoard_creator_genre(rs.getString("board_creator_genre"));
				projectBean.setBoard_creator_ori_clip_num(rs.getInt("board_creator_ori_clip_num"));
				projectBean.setBoard_creator_ori_length(rs.getInt("board_creator_ori_length"));
				projectBean.setBoard_creator_ori_transfer(rs.getInt("board_creator_ori_transfer"));
				projectBean.setBoard_creator_recording(rs.getInt("board_creator_recording"));
				projectBean.setBoard_creator_status(rs.getInt("board_creator_status"));
			}
		} catch (Exception e) {
			System.out.println("selectProjectInfo() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return projectBean;
	}
	
	public int selectProjectSearchCount(HashMap<String, String> search) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT COUNT(b.board_id) FROM board b JOIN user u ON b.user_id = u.user_id "
					+ "JOIN board_creator c ON c.board_id = b.board_id WHERE b.board_type = 1 ";
			if(search.containsKey("keyword")) {sql+="AND CONCAT(b.board_subject,b.board_content) LIKE '%"+search.get("keyword")+"%' ";}
			if(search.containsKey("genre")) {sql+="AND c.board_creator_genre REGEXP '"+search.get("genre")+"' ";}
			if(search.containsKey("price_x")) {sql+="AND c.board_creator_cre_min_price < "+search.get("price_x")+" ";}
			if(search.containsKey("price_n")) {sql+="AND c.board_creator_cre_max_price > "+search.get("price_n")+" ";}
			if(search.containsKey("recording")) {sql+="AND c.board_creator_recording IN("+search.get("recording")+") ";}
			if(search.containsKey("camnum")) {sql+="AND c.board_creator_cam_num IN("+search.get("camnum")+") ";} 
			if(search.containsKey("clipnum")) {sql+="AND c.board_creator_ori_clip_num IN("+search.get("clipnum")+") ";}
			if(search.containsKey("oriLength")) {sql+="AND c.board_creator_ori_length IN("+search.get("oriLength")+") ";} 
			if(search.containsKey("editLength")) {sql+="AND c.board_creator_edit_length IN("+search.get("editLength")+") ";} 
			if(search.containsKey("transfer")) {sql+="AND c.board_creator_ori_transfer IN("+search.get("transfer")+") ";} 
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println("selectProjectSearchCount() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return count;
	}
	
	public HashMap<String, Integer> selectGenreCount() { // 분야별 프로젝트 갯수
		System.out.println("ProjectDAO - selectGenreCount()");
		HashMap<String, Integer> countList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT category.category_id, category.category_content, " + 
					"IFNULL((SELECT COUNT(board_id) FROM board_creator " + 
					"WHERE board_creator_genre LIKE CONCAT('%',category.category_id,'%')),0) AS Count " + 
					"FROM category " + 
					"ORDER BY IFNULL((SELECT COUNT(board_id) FROM board_creator " + 
					"WHERE board_creator_genre LIKE CONCAT('%',category.category_id,'%')),0) DESC " + 
					"LIMIT 6;";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			countList = new HashMap<String, Integer>();
			while(rs.next()) {
				countList.put(rs.getString("category_content"), rs.getInt("Count"));
			}
		} catch (Exception e) {
			System.out.println("selectGenreCount 오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		} 
		
		return countList;
	}
	
		public HashMap<Integer,Integer> selectStatusCount(){
			System.out.println("ProjectDAO - selectStatusCount()");
			HashMap<Integer, Integer> countList = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "SELECT (SELECT COUNT(board_id) FROM board_creator) AS 'a' , "+ 
						"(SELECT COUNT(board_id) FROM board_creator WHERE board_creator_status = 0)  AS 'b'," + 
						"(SELECT COUNT(user_id) FROM editor)  AS 'c', " + 
						"(SELECT COUNT(board_id) FROM board_creator WHERE board_creator_status = 1)  AS 'd';";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					countList = new HashMap<Integer, Integer>();
					countList.put(1, rs.getInt(1));
					countList.put(2, rs.getInt(2));
					countList.put(3, rs.getInt(3));
					countList.put(4, rs.getInt(4));
				}
			} catch (Exception e) {
				System.out.println("selectGenreCount 오류");
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
			
			return countList;
		}
	// CHECK ====================================================================================
		
		public int checkApplyProject(String user_id, int board_id) { // 에디터와 프로젝트와의 관계 (지원전, 지원중, 계약중)
			int check = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "SELECT COUNT(contract_id) FROM contract WHERE contract_status = 1 AND contract_editor = ? AND board_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user_id);
				pstmt.setInt(2, board_id);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					if(rs.getInt(1)==1) {
						check = 1; // 대기자 상태
						
					} else {
						sql = "SELECT COUNT(contract_id) FROM contract WHERE contract_status = 2 AND contract_editor = ? AND board_id = ?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, user_id);
						pstmt.setInt(2, board_id);
						rs = pstmt.executeQuery();
						if(rs.next()) {
							if(rs.getInt(1)==1) {
								check = 2; // 계약중인 상태
							}
						}
					
					}
				}
				
			} catch (Exception e) {
				System.out.println("checkApplyProject() 오류! - " + e.getMessage());
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
			
			return check;
		}
		
		public int checkProjectStatus(int board_id) { // 프로젝트 상태
			System.out.println("ProjectDAO - checkProjectStatus()");
			int check = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "SELECT board_creator_status FROM board_creator WHERE board_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, board_id);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					check = rs.getInt(1);
				}
			} catch (Exception e) {
				System.out.println("checkProjectStatus() 오류! - " + e.getMessage());
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
			
			return check;

		}
	// INSERT ===================================================================================
	
	public int insertProject(ProjectBean projectBean) { // 에디터 게시물 생성
		System.out.println("ProjectDAO - insertProject()");
		int insertCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "INSERT INTO board_creator (board_id, board_creator_content_detail, board_creator_genre, "
					+ "board_creator_recording, board_creator_cam_num, board_creator_ori_clip_num, board_creator_ori_length, "
					+ "board_creator_edit_length, board_creator_ori_transfer, board_creator_cre_ref, "
					+ "board_creator_cre_min_price, board_creator_cre_max_price, board_creator_status)"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, projectBean.getBoard_id());
			pstmt.setString(2, projectBean.getBoard_creator_content_detail());
			pstmt.setString(3, projectBean.getBoard_creator_genre());
			pstmt.setInt(4, projectBean.getBoard_creator_recording());
			pstmt.setInt(5, projectBean.getBoard_creator_cam_num());
			pstmt.setInt(6, projectBean.getBoard_creator_ori_clip_num());
			pstmt.setInt(7, projectBean.getBoard_creator_ori_length());
			pstmt.setInt(8, projectBean.getBoard_creator_edit_length());
			pstmt.setInt(9, projectBean.getBoard_creator_ori_transfer());
			pstmt.setString(10, projectBean.getBoard_creator_cre_ref());
			pstmt.setInt(11, projectBean.getBoard_creator_cre_min_price());
			pstmt.setInt(12, projectBean.getBoard_creator_cre_max_price());
			pstmt.setInt(13, projectBean.getBoard_creator_status());
			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("insertProject() 오류! - " + e.getMessage());

		} finally {
			close(pstmt);
		}
		return insertCount;
	}
	
	public int insertWaiting(WaitingBean waitingBean) {
		System.out.println("ProjectDAO - insertWaiting()");
		int insertCount = 0;
		PreparedStatement pstmt = null;
		try {
			String sql = "INSERT INTO waiting (waiting_editor, waiting_date, user_id, board_id, waiting_price) "
					+ "VALUES (?,NOW(),?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, waitingBean.getWaiting_editor());
			pstmt.setString(2, waitingBean.getUser_id());
			pstmt.setInt(3, waitingBean.getBoard_id());
			pstmt.setInt(4, waitingBean.getWaiting_price());
			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("insertWaiting() - "+e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return insertCount;
	}
		
	// UPDATE ===================================================================================
		
	public int updateProject(ProjectBean projectBean) { // 에디터 게시물 수정
		System.out.println("ProjectDAO - updateProject()");
		int updateCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE board_creator SET board_creator_content_detail = ?, board_creator_genre = ?, board_creator_recording = ?, "
					+ "board_creator_cam_num = ?,board_creator_ori_clip_num = ?, board_creator_ori_length = ?, board_creator_edit_length = ?, "
					+ "board_creator_ori_transfer = ?, board_creator_cre_ref = ?, board_creator_cre_min_price = ?, board_creator_cre_max_price = ?, board_creator_status = ? "
					+ "WHERE board_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, projectBean.getBoard_creator_content_detail());
			pstmt.setString(2, projectBean.getBoard_creator_genre());
			pstmt.setInt(3, projectBean.getBoard_creator_recording());
			pstmt.setInt(4, projectBean.getBoard_creator_cam_num());
			pstmt.setInt(5, projectBean.getBoard_creator_ori_clip_num());
			pstmt.setInt(6, projectBean.getBoard_creator_ori_length());
			pstmt.setInt(7, projectBean.getBoard_creator_edit_length());
			pstmt.setInt(8, projectBean.getBoard_creator_ori_transfer());
			pstmt.setString(9, projectBean.getBoard_creator_cre_ref());
			pstmt.setInt(10, projectBean.getBoard_creator_cre_min_price());
			pstmt.setInt(11, projectBean.getBoard_creator_cre_max_price());
			pstmt.setInt(12, projectBean.getBoard_creator_status());
			pstmt.setInt(13, projectBean.getBoard_id());
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateProject() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateCount;
	}
		
	public int updateStatus(int board_id, int board_creator_status) {
		System.out.println("ProjectDAO - updateStatus()");
		int updateCount = 0;
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE board_creator SET board_creator_status = ? WHERE board_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_creator_status);
			pstmt.setInt(2, board_id);
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateStatus() 오류 - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateCount;
	}
	// DELETE ===================================================================================
		
	public int deleteProject(int board_id) { // 에디터 게시글 삭제
		System.out.println("ProjectDAO - deleteProject()");
		int deleteProject = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM board_creator WHERE board_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			deleteProject = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("deleteProject() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteProject;
	}
	
	public int deleteWaiting(int board_id, String waiting_editor) {
		System.out.println("ProjectDAO - deleteWaiting()");
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM waiting WHERE board_id = ? AND waiting_editor = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			pstmt.setString(2, waiting_editor);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("deleteWaiting() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}
	
	public int deleteWaiting(int board_id) {
		System.out.println("ProjectDAO - deleteWaiting()");
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM waiting WHERE board_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("deleteWaiting() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}
		
	// LIST =====================================================================================
		
	public ArrayList<ProjectBean> selectListProject(int page){ // 에디터 게시글 리스트
		System.out.println("ProjectDAO - selectListProject()");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int limit = Constant.BOARD_PAGE_SIZE;
		int startRow = (page - 1) * limit;
		ArrayList<ProjectBean> list = null;
		
		try {
			String sql = "SELECT * FROM board b " + 
					"JOIN board_creator c " + 
					"ON b.board_id = c.board_id "
					+ "JOIN user u "
					+ "ON b.user_id = u.user_id " +
					"ORDER BY c.board_creator_status ASC, b.board_date DESC " +
					"LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			list = new ArrayList<ProjectBean>();
			
			while(rs.next()) {
				ProjectBean projectBean = new ProjectBean();
				projectBean.setBoard_id(rs.getInt("board_id"));
				projectBean.setBoard_subject(rs.getString("board_subject"));
				projectBean.setBoard_content(rs.getString("board_content"));
				projectBean.setBoard_date(rs.getTimestamp("board_date"));
				projectBean.setBoard_type(rs.getInt("board_type"));
				projectBean.setUser_id(rs.getString("user_id"));
				projectBean.setUser_name(rs.getString("user_name"));
				projectBean.setUser_level(rs.getInt("user_level"));
				projectBean.setBoard_creator_cam_num(rs.getInt("board_creator_cam_num"));
				projectBean.setBoard_creator_content_detail(rs.getString("board_creator_content_detail"));
				projectBean.setBoard_creator_cre_max_price(rs.getInt("board_creator_cre_max_price"));
				projectBean.setBoard_creator_cre_min_price(rs.getInt("board_creator_cre_min_price"));
				projectBean.setBoard_creator_cre_ref(rs.getString("board_creator_cre_ref"));
				projectBean.setBoard_creator_edit_length(rs.getInt("board_creator_edit_length"));
				projectBean.setBoard_creator_genre(rs.getString("board_creator_genre"));
				projectBean.setBoard_creator_ori_clip_num(rs.getInt("board_creator_ori_clip_num"));
				projectBean.setBoard_creator_ori_length(rs.getInt("board_creator_ori_length"));
				projectBean.setBoard_creator_ori_transfer(rs.getInt("board_creator_ori_transfer"));
				projectBean.setBoard_creator_recording(rs.getInt("board_creator_recording"));
				projectBean.setBoard_creator_status(rs.getInt("board_creator_status"));
				
				list.add(projectBean);
			}
		} catch (Exception e) {
			System.out.println("selectListProject() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
 		
		return list;
	}
	
	public ArrayList<ProjectBean> selectListSearchProject(int page, HashMap<String, String> search){ // 에디터 검색
		System.out.println("ProjectDAO - selectListSearchProject()");
		ArrayList<ProjectBean> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int limit = Constant.BOARD_PAGE_SIZE;
		int startRow = (page - 1) * limit;
		try {
			String sql = "SELECT b.board_id, b.board_subject, b.board_content, b.board_date, b.board_type, u.user_id, u.user_name, u.user_level, c.board_creator_genre," + 
					"c.board_creator_recording, c.board_creator_cam_num, c.board_creator_ori_clip_num, c.board_creator_ori_length," + 
					"c.board_creator_edit_length, c.board_creator_ori_transfer, c.board_creator_cre_min_price, c.board_creator_cre_max_price, c.board_creator_status, u.user_name FROM board b  "
					+ "JOIN board_creator c ON c.board_id = b.board_id "
					+ "JOIN user u ON u.user_id = b.user_id WHERE b.board_type = 1 ";
			if(search.containsKey("keyword")) {sql+="AND CONCAT(b.board_subject,b.board_content) LIKE '%"+search.get("keyword")+"%' ";}
			if(search.containsKey("genre")) {sql+="AND c.board_creator_genre REGEXP '"+search.get("genre")+"' ";}
			if(search.containsKey("price_x")) {sql+="AND c.board_creator_cre_min_price < "+search.get("price_x")+" ";}
			if(search.containsKey("price_n")) {sql+="AND c.board_creator_cre_max_price > "+search.get("price_n")+" ";}
			if(search.containsKey("recording")) {sql+="AND c.board_creator_recording IN("+search.get("recording")+") ";}
			if(search.containsKey("camnum")) {sql+="AND c.board_creator_cam_num IN("+search.get("camnum")+") ";} 
			if(search.containsKey("clipnum")) {sql+="AND c.board_creator_ori_clip_num IN("+search.get("clipnum")+") ";}
			if(search.containsKey("oriLength")) {sql+="AND c.board_creator_ori_length IN("+search.get("oriLength")+") ";} 
			if(search.containsKey("editLength")) {sql+="AND c.board_creator_edit_length IN("+search.get("editLength")+") ";} 
			if(search.containsKey("transfer")) {sql+="AND c.board_creator_ori_transfer IN("+search.get("transfer")+") ";} 
			
			System.out.println(sql);
			
			sql += "ORDER BY c.board_creator_status ASC, b.board_date DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			list = new ArrayList<ProjectBean>();
			while(rs.next()) {
				ProjectBean projectBean = new ProjectBean();
				projectBean.setBoard_id(rs.getInt("board_id"));
				projectBean.setBoard_subject(rs.getString("board_subject"));
				projectBean.setBoard_content(rs.getString("board_content"));
				projectBean.setBoard_date(rs.getTimestamp("board_date"));
				projectBean.setBoard_type(rs.getInt("board_type"));
				projectBean.setUser_id(rs.getString("user_id"));
				projectBean.setUser_name(rs.getString("user_name"));
				projectBean.setUser_level(rs.getInt("user_level"));
				projectBean.setBoard_creator_cam_num(rs.getInt("board_creator_cam_num"));
				projectBean.setBoard_creator_cre_max_price(rs.getInt("board_creator_cre_max_price"));
				projectBean.setBoard_creator_cre_min_price(rs.getInt("board_creator_cre_min_price"));
				projectBean.setBoard_creator_edit_length(rs.getInt("board_creator_edit_length"));
				projectBean.setBoard_creator_genre(rs.getString("board_creator_genre"));
				projectBean.setBoard_creator_ori_clip_num(rs.getInt("board_creator_ori_clip_num"));
				projectBean.setBoard_creator_ori_length(rs.getInt("board_creator_ori_length"));
				projectBean.setBoard_creator_ori_transfer(rs.getInt("board_creator_ori_transfer"));
				projectBean.setBoard_creator_recording(rs.getInt("board_creator_recording"));
				projectBean.setBoard_creator_status(rs.getInt("board_creator_status"));
				// 검색리스트
				list.add(projectBean);
			}
			
		} catch (Exception e) {
			System.out.println("selectListSearchProject() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}


	public ArrayList<String> selectWaitingDeclineList(int board_id, String waiting_editor) {
		System.out.println("ProjectDAO - selectWaitingDeclineList()");
		ArrayList<String> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT waiting_editor FROM waiting WHERE board_id = ? AND waiting_editor NOT IN(?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			pstmt.setString(2, waiting_editor);
			rs = pstmt.executeQuery();
			list = new ArrayList<String>();
			while(rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println("selectWaitingDeclineList() 오류 - "+e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return list;
	}





	
		
		
	// OTHER ====================================================================================
	
}
