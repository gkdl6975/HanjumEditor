package com.hanjum.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hanjum.board.vo.BoardBean;
import com.hanjum.board.vo.EditorBean;
import com.hanjum.board.vo.ProjectBean;
import static com.hanjum.db.JdbcUtil.*;

public class BoardDAO {
	private BoardDAO() {}
	
	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}
	
	Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
	// GET ======================================================================================
	
	public int selectBoardLastId() { // board 게시물 마지막 id 값
		System.out.println("BoardDAO - getBoardLastId()");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int board_id = 0;
		try {
			String sql = "SELECT MAX(board_id) FROM board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board_id = rs.getInt(1)+1;
			}
		} catch (Exception e) {
			System.out.println("getBoardLastId() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return board_id;
	}
	
	public BoardBean selectBoardInfo(int board_id) { // board 게시물 정보 조회
		System.out.println("BoardDAO - selectBoardInfo()");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardBean boardBean = null;
		try {
			String sql = "SELECT * FROM board WHERE board_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				
				switch (rs.getInt("board_type")) {
				case 1: boardBean = new ProjectBean();break;
				case 2: boardBean = new EditorBean();break;
				default: boardBean = new BoardBean();
					break;
				}
				
				boardBean.setBoard_id(board_id);
				boardBean.setBoard_subject(rs.getString("board_subject"));
				boardBean.setBoard_content(rs.getString("board_content"));
				boardBean.setBoard_date(rs.getTimestamp("board_date"));
				boardBean.setBoard_readcount(rs.getInt("board_readcount"));
				boardBean.setBoard_type(rs.getInt("board_type"));
				boardBean.setBoard_report(rs.getInt("board_report"));
				boardBean.setUser_id(rs.getString("user_id"));
				
				sql = "UPDATE board SET board_readcount = board_readcount+1 WHERE board_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, board_id);
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println("selectBoardInfo() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return boardBean;
	}
	
	public int selectListCount(int board_type) {
		System.out.println("BoardDAO - selectListCount()");
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(board_id) FROM board WHERE board_type = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_type);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("selectListCount() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return listCount;
	}
	
	public String selectUserId(int board_id) {
		System.out.println("BoardDAO - selectUserId()");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String user_id = "";
		try {
			String sql = "SELECT user_id FROM board WHERE board_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user_id = rs.getString(1);
			}
		} catch (Exception e) {
			System.out.println("selectUserId() 오류! - " + e.getMessage());
			e.printStackTrace();
		}
		return user_id;
	}
	// CHECK ====================================================================================
	public int checkBoardWriter(int board_id, String user_id) {
		System.out.println("BoardDAO - checkBoardWriter()");
		int check = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT user_id FROM board WHERE board_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("user_id").equals(user_id)) {
					check = 1;
				}
			}
		} catch (SQLException e) {
			System.out.println("checkBoardWriter() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return check;
	}
	// INSERT ===================================================================================
	
	public int insertBoard(BoardBean boardBean) { // board 게시물 작성
		System.out.println("BoardDAO - insertBoard()");
		int insertCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "INSERT INTO board (board_id, board_subject, board_content, board_type, user_id)"
					+ " VALUES (?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardBean.getBoard_id());
			pstmt.setString(2, boardBean.getBoard_subject());
			pstmt.setString(3, boardBean.getBoard_content());
			pstmt.setInt(4, boardBean.getBoard_type());
			pstmt.setString(5, boardBean.getUser_id());
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insertBoard() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return insertCount;
	}
	
	// UPDATE ===================================================================================
	
	public int updateBoard(BoardBean boardBean) { // board 게시물 수정
		System.out.println("BoardDAO - updateBoard()");
		int updateCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE board SET board_subject = ?, board_content = ? WHERE board_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardBean.getBoard_subject());
			pstmt.setString(2, boardBean.getBoard_content());
			pstmt.setInt(3, boardBean.getBoard_id());
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateBoard() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateCount;
	}
	// DELETE ===================================================================================
	
	public int deleteBoard(int board_id) { // board 게시물 삭제
		System.out.println("BoardDAO - deleteBoard()");
		int deleteBoard = 0;
		PreparedStatement pstmt = null;
		try {
			String sql = "DELETE FROM board WHERE board_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			deleteBoard = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("deleteBoard() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteBoard;
	}
	
	
	
}
