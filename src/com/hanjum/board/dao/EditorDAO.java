package com.hanjum.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.hanjum.board.vo.BoardBean;
import com.hanjum.board.vo.EditorBean;
import com.hanjum.vo.Constant;

import static com.hanjum.db.JdbcUtil.*;

public class EditorDAO {
	private EditorDAO() {}
	
	private static EditorDAO instance = new EditorDAO();

	public static EditorDAO getInstance() {
		return instance;
	}
	
	Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
	// GET ======================================================================================
	
	public EditorBean selectEditorInfo(String user_id) { // 에디터 게시물 조회
		System.out.println("EditorDAO - selectEditorInfo()");
		EditorBean editorBean = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM board_ed e JOIN board b ON e.board_id = b.board_id JOIN user u ON u.user_id = b.user_id where b.user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				editorBean = new EditorBean();
				editorBean.setBoard_id(rs.getInt("board_id"));
				editorBean.setUser_id(rs.getString("user_id"));
				editorBean.setUser_level(rs.getInt("user_level"));
				editorBean.setBoard_content(rs.getString("board_content"));
				editorBean.setBoard_date(rs.getTimestamp("board_date"));
				editorBean.setBoard_subject(rs.getString("board_subject"));
				editorBean.setBoard_readcount(rs.getInt("board_readcount"));
				editorBean.setBoard_report(rs.getInt("board_report"));
				editorBean.setBoard_ed_address(rs.getString("board_ed_address"));
				editorBean.setBoard_ed_content_detail(rs.getString("board_ed_content_detail"));
				editorBean.setBoard_ed_fort(rs.getInt("board_ed_fort"));
				editorBean.setBoard_ed_inventory(rs.getString("board_ed_inventory"));
				editorBean.setBoard_ed_max_price(rs.getInt("board_ed_max_price"));
				editorBean.setBoard_ed_meeting(rs.getInt("board_ed_meeting"));
				editorBean.setBoard_ed_min_price(rs.getInt("board_ed_min_price"));
				editorBean.setBoard_ed_program(rs.getString("board_ed_program"));
				editorBean.setBoard_ed_sample(rs.getInt("board_ed_sample"));
				editorBean.setBoard_ed_solution(rs.getString("board_ed_solution"));
				editorBean.setBoard_ed_upload(rs.getInt("board_ed_upload"));
				editorBean.setBoard_ed_work(rs.getInt("board_ed_work"));
				editorBean.setUser_score(rs.getInt("user_score"));
				
				sql = "UPDATE board SET board_readcount = board_readcount+1 WHERE board_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, rs.getInt("board_id"));
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println("selectProjectInfo() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return editorBean;
	}
	
	public int getEditorBoardCount() {
		System.out.println("EditorDAO - getEditorBoardCount()");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "SELECT COUNT(*) FROM board b "
					+ "JOIN board_ed e "
					+ "ON b.board_id = e.board_id "
					+ "JOIN editor ed "
					+ "ON b.user_id = ed.user_id "
					+ "JOIN user u "
					+ "ON b.user_id = u.user_id "
					+ "WHERE ed.editor_status = 1 ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getEditorBoardCount() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return count;
	}
	
	// CHECK ====================================================================================
	
	public int checkEditorInfo (String user_id) { // 에디터 정보가 존재하는지
		System.out.println("EditorDAO - checkEditorInfo()");
		int check = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT COUNT(e.board_id) FROM board_ed e "
					+ "JOIN board b "
					+ "ON b.board_id = e.board_id "
					+ "WHERE b.user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				check = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("checkEditorInfo() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return check;
	}
		
		
	// INSERT ===================================================================================
		
	public int insertEditor(EditorBean editorBean) { // 에디터 게시물 생성
		System.out.println("EditorDAO - insertEditor()");
		int insertCount = 0;
		PreparedStatement pstmt = null;
		try {
			String sql = "INSERT INTO board_ed VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, editorBean.getBoard_id());
			pstmt.setString(2, editorBean.getBoard_ed_program());
			pstmt.setString(3, editorBean.getBoard_ed_solution());
			pstmt.setString(4, editorBean.getBoard_ed_inventory());
			pstmt.setInt(5, editorBean.getBoard_ed_upload());
			pstmt.setInt(6, editorBean.getBoard_ed_work());
			pstmt.setInt(7, editorBean.getBoard_ed_sample());
			pstmt.setInt(8, editorBean.getBoard_ed_fort());
			pstmt.setInt(9, editorBean.getBoard_ed_min_price());
			pstmt.setInt(10, editorBean.getBoard_ed_max_price());
			pstmt.setInt(11, editorBean.getBoard_ed_meeting());
			pstmt.setString(12, editorBean.getBoard_ed_content_detail());
			pstmt.setString(13, editorBean.getBoard_ed_address());
			insertCount = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("insertEditor() 오류! - " + e.getMessage());
		} finally {
			close(pstmt);
		}
		return insertCount;
	}
		
	// UPDATE ===================================================================================
		
	public int updateEditor(EditorBean editorBean) { // 에디터 게시물 수정
		System.out.println("EditorDAO - updateEditor()");
		int updateCount = 0;
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE board_ed SET board_ed_content_detail = ? "
					+ "WHERE board_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, editorBean.getBoard_ed_content_detail());
			pstmt.setInt(2, editorBean.getBoard_id());
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateEditor() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}
	
	public int updateEditor(com.hanjum.user.vo.EditorBean editorBean) { // 회원정보 수정으로 인한 에디터 게시물 수정
		System.out.println("EditorDAO - updateEditor()");
		int updateCount = 0;
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE board_ed e JOIN board b ON e.board_id = b.board_id "
					+ "SET e.board_ed_program = ?, e.board_ed_solution = ?, e.board_ed_inventory = ?, "
					+ "e.board_ed_upload = ?, e.board_ed_work = ?, e.board_ed_sample = ?, e.board_ed_fort = ?, e.board_ed_min_price = ?, "
					+ "e.board_ed_max_price = ?, e.board_ed_meeting = ?, e.board_ed_address = ? "
					+ "WHERE b.user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, editorBean.getEditor_program());
			pstmt.setString(2, editorBean.getEditor_solution());
			pstmt.setString(3, editorBean.getEditor_inventory());
			pstmt.setInt(4, editorBean.getEditor_upload());
			pstmt.setInt(5, editorBean.getEditor_work());
			pstmt.setInt(6, editorBean.getEditor_sample());
			pstmt.setInt(7, editorBean.getEditor_fort());
			pstmt.setInt(8, editorBean.getEditor_ed_min_price());
			pstmt.setInt(9, editorBean.getEditor_ed_max_price());
			pstmt.setInt(10, editorBean.getEditor_meeting());
			pstmt.setString(11, editorBean.getEditor_address());
			pstmt.setString(12, editorBean.getUser_id());
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateEditor() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}
		
	// DELETE ===================================================================================
		
	public int deleteEditor(String user_id) { // 에디터 게시글 삭제
		System.out.println("EditorDAO - deleteEditor()");
		int deleteEditor = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM board_ed WHERE user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			deleteEditor = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("deleteEditor() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteEditor;
	}
		
	// LIST =====================================================================================
		
	public ArrayList<EditorBean> selectListEditor(int page){ // 에디터 게시글 리스트
		System.out.println("EditorDAO - selectListEditor()");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int limit = Constant.BOARD_PAGE_SIZE;
		int startRow = (page - 1) * limit;
		ArrayList<EditorBean> list = null;
		
		try {
			String sql = "SELECT * FROM board b "
					+ "JOIN board_ed e "
					+ "ON b.board_id = e.board_id "
					+ "JOIN editor ed "
					+ "ON b.user_id = ed.user_id "
					+ "JOIN user u "
					+ "ON b.user_id = u.user_id "
					+ "WHERE ed.editor_status = 1 "
					+ "ORDER BY u.user_score DESC, u.user_level DESC "
					+ "LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			list = new ArrayList<EditorBean>();
			
			while(rs.next()) {
				EditorBean editorBean = new EditorBean();
				editorBean.setBoard_id(rs.getInt("board_id"));
				editorBean.setBoard_subject(rs.getString("board_subject"));
				editorBean.setBoard_content(rs.getString("board_content"));
				editorBean.setBoard_date(rs.getTimestamp("board_date"));
				editorBean.setBoard_type(rs.getInt("board_type"));
				editorBean.setUser_id(rs.getString("user_id"));
				editorBean.setUser_name(rs.getString("user_name"));
				editorBean.setUser_level(rs.getInt("user_level"));
				editorBean.setBoard_ed_address(rs.getString("board_ed_address"));
				editorBean.setBoard_ed_content_detail(rs.getString("board_ed_content_detail"));
				editorBean.setBoard_ed_fort(rs.getInt("board_ed_fort"));
				editorBean.setBoard_ed_inventory(rs.getString("board_ed_inventory"));
				editorBean.setBoard_ed_max_price(rs.getInt("board_ed_max_price"));
				editorBean.setBoard_ed_meeting(rs.getInt("board_ed_meeting"));
				editorBean.setBoard_ed_min_price(rs.getInt("board_ed_min_price"));
				editorBean.setBoard_ed_program(rs.getString("board_ed_program"));
				editorBean.setBoard_ed_sample(rs.getInt("board_ed_sample"));
				editorBean.setBoard_ed_solution(rs.getString("board_ed_solution"));
				editorBean.setBoard_ed_upload(rs.getInt("board_ed_upload"));
				editorBean.setBoard_ed_work(rs.getInt("board_ed_work"));
				editorBean.setBoard_ed_photo(rs.getString("editor_photo"));
				editorBean.setUser_score(rs.getInt("user_score"));
				list.add(editorBean);
			}
			
		} catch (Exception e) {
			System.out.println("selectListEditor() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return list;
	}
	
	public ArrayList<EditorBean> selectListSearchEditor(int startRow, HashMap<String, String> search){ // 에디터 검색
		System.out.println("EditorDAO - selectListSearchEditor()");
		ArrayList<EditorBean> list = null;
		
		return list;
	}
		
		
	// OTHER ====================================================================================
	
	
}
