package com.hanjum.board.service;

import java.sql.Connection;

import com.hanjum.board.dao.BoardDAO;
import com.hanjum.board.vo.BoardBean;
import static com.hanjum.db.JdbcUtil.*;

public class BoardProService {
	private BoardDAO boardDAO;
	private Connection con;
	
	public BoardProService() {
		boardDAO = BoardDAO.getInstance();// 1단계
		con = getConnection(); // 2단계
		boardDAO.setConnection(con);// 3단계
	}
	// GET =========================================================================
	public BoardBean getBoard(int board_id) { // board 정보 호출
		System.out.println("BoardProService - getBoard()");
		BoardBean boardBean = boardDAO.selectBoardInfo(board_id);
		if(boardBean != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return boardBean;
	}
	
	public int getBoardLastId() { // board_id 새 번호 호출
		System.out.println("BoardProService - getBoardLastId()");
		int board_id = boardDAO.selectBoardLastId();
		close(con);
		return board_id;
	}
	
	public int getBoardCount(int board_type) { // board 행 갯수 출력 (타입별로)
		System.out.println("BoardProService - getBoardCount()");
		int count = boardDAO.selectListCount(board_type);
		close(con);
		return count;
	}
	
	public boolean checkBoardWriter(int board_id, String user_id) {
		System.out.println("BoardProService - checkBoardWriter()");
		boolean isWriter = false;
		int check = boardDAO.checkBoardWriter(board_id, user_id);
		if(check == 1) {
			isWriter = true;
		}
		close(con);
		return isWriter;
	}
	public String getUser_id(int board_id) {
		System.out.println("BoardProService - checkBoardWriter()");
		String user_id = boardDAO.selectUserId(board_id);
		close(con);
		return user_id;
	}
}
