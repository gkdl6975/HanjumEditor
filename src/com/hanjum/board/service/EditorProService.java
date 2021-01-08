package com.hanjum.board.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.hanjum.board.dao.BoardDAO;
import com.hanjum.board.dao.EditorDAO;
import com.hanjum.board.vo.BoardBean;
import com.hanjum.board.vo.EditorBean;
import static com.hanjum.db.JdbcUtil.*;

public class EditorProService {
	private EditorDAO editorDAO;
	private Connection con;
	public EditorProService() {
		editorDAO = EditorDAO.getInstance(); // 1단계
		con = getConnection(); // 2단계
		editorDAO.setConnection(con); // 3단계
	}
	
	// GET ======================================================================================
	
	public EditorBean getEditor(String user_id) { // 에디터 조회 서비스
		System.out.println("EditorProService - getEditor()");
		
		editorDAO.setConnection(con);
		EditorBean editorBean = editorDAO.selectEditorInfo(user_id);
		close(con);
		return editorBean; // EditorBean
		
	}
	
	public int getEditorBoardCount() {
		System.out.println("EditorProService - getEditor()");
		
		editorDAO.setConnection(con);
		int count = editorDAO.getEditorBoardCount();
		close(con);
		return count;

	}
	// CHECK ====================================================================================
	
	public boolean checkEditorInfo(String user_id) {
		System.out.println("EditorProService - checkEditorInfo()");
		boolean isSuccess = false;
		int check = editorDAO.checkEditorInfo(user_id);
		if(check == 1) {
			isSuccess = true;
		}
		close(con);
		return isSuccess;
	}
	
	// INSERT ===================================================================================
	
	public boolean writeEditor(EditorBean editorBean) { // 에디터 작성 서비스
		System.out.println("EditorProService - writeEditor()");
		BoardBean boardBean = editorBean;
		BoardDAO boardDAO = BoardDAO.getInstance(); // 2단계
		boardDAO.setConnection(con); // 3단계
		boolean isSuccess = false;
		int count = boardDAO.insertBoard(boardBean);
		if(count > 0) {
			count = editorDAO.insertEditor(editorBean);
			if(count > 0) {
				commit(con);
				isSuccess = true;
			} else {
				rollback(con);
			}
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	
	// UPDATE ===================================================================================
	
	public boolean modifyEditor(EditorBean editorBean) { // 에디터 수정 서비스
		System.out.println("EditorProService - modifyEditor()");
		boolean isSuccess = false;
		int count = editorDAO.updateEditor(editorBean);
		if(count > 0) {
			BoardBean boardBean = editorBean;
			BoardDAO boardDAO = BoardDAO.getInstance(); // 2단계
			boardDAO.setConnection(con); // 3단계
			count = boardDAO.updateBoard(boardBean);
			if(count > 0) {
				commit(con);
				isSuccess = true;
			} else {
				rollback(con);
			}
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	
	// DELETE ===================================================================================
	
	public boolean dropEditor(String user_id) { // 에디터 삭제 서비스
		System.out.println("EditorProService - dropEditor()");
		boolean isSuccess = false;
		int count = editorDAO.deleteEditor(user_id);
		if(count > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	
	// LIST ===================================================================================

	public ArrayList<EditorBean> getListEditor(int startRow){ // 에디터 리스트 서비스
		System.out.println("EditorProService - getListEditor()");
		ArrayList<EditorBean> list = editorDAO.selectListEditor(startRow);
		close(con);
		return list;
	}
	
	public ArrayList<EditorBean> getListSearchEditor(int startRow, HashMap<String, String> search){ // 에디터 검색 서비스
		System.out.println("EditorProService - getListSearchEditor()");
		close(con);
		return editorDAO.selectListSearchEditor(startRow, search);
	}

	public boolean updateEditor(com.hanjum.user.vo.EditorBean editorBean) { // 회원정보수정으로 인한 이력서 수정
		System.out.println("EditorProService - updateEditor()");
		boolean isSuccess = false;
		int count = editorDAO.updateEditor(editorBean);
		if(count > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	
}
