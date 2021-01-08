package com.hanjum.board.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.hanjum.board.dao.BoardDAO;
import com.hanjum.board.dao.ProjectDAO;
import com.hanjum.board.vo.BoardBean;
import com.hanjum.board.vo.ProjectBean;
import com.hanjum.board.vo.WaitingBean;

import static com.hanjum.db.JdbcUtil.*;

public class ProjectProService {
	private ProjectDAO projectDAO;
	private Connection con;
	public ProjectProService() {
		con = getConnection(); // 1단계
		projectDAO = ProjectDAO.getInstance(); // 2단계
		projectDAO.setConnection(con); // 3단계
		// 다수의 메서드를 활용하기 위해 1단계, 2단계를 생성자로 호출
		// 3단계는 각자의 메서드에서 호출 ( 여러 DAO를 활용해야해서 여기서 하기 애매한거같음)
	}
	
	// GET ======================================================================================
	
	public ProjectBean getProject(BoardBean boardBean) { // 프로젝트 조회 서비스
		System.out.println("ProjectProService - getProject()");
		ProjectBean projectBean = projectDAO.selectProjectInfo(boardBean);
		
		close(con);
		return projectBean; // ProjectBean
	}
	
	public int getProjectSearchListCount(HashMap<String, String> search) { // 프로젝트 검색 갯수
		System.out.println("ProjectProService - getListSearchProjectCount()");
		int count = projectDAO.selectProjectSearchCount(search);
		close(con);
		return count;
	}
	
	public HashMap<String, Integer> getProjectGenreCount() { // 프로젝트 분야별 갯수
		System.out.println("ProjectProService - getListSearchProjectCount()");
		HashMap<String, Integer> countList = projectDAO.selectGenreCount();
		
		close(con);
		return countList;
	}
	
	public HashMap<Integer, Integer> getStatusCount(){
		System.out.println("ProjectProService - getStatusCount()");
		HashMap<Integer, Integer> statusCount = projectDAO.selectStatusCount();
		
		close(con);
		return statusCount;
	}
	// CHECK ===================================================================================
	public int CheckApplyProject(String user_id, int board_id) {
		System.out.println("ProjectProService - getStatusCount()");
		
		int check = projectDAO.checkApplyProject(user_id, board_id);
		close(con);
		return check;
	}
	
	public int CheckProjectStatus(int board_id) {
		System.out.println("ProjectProService - getStatusCount()");
		int check = projectDAO.checkProjectStatus(board_id);
		
		close(con);
		return check;
		
	}
	// INSERT ===================================================================================
	
	public boolean writeProject(ProjectBean projectBean) { // 프로젝트 작성 서비스
		System.out.println("ProjectProService - writeProject()");
		BoardBean boardBean = projectBean;
		BoardDAO boardDAO = BoardDAO.getInstance(); // 2단계
		boardDAO.setConnection(con); // 3단계
		
		boolean isSuccess = false;
		int count = boardDAO.insertBoard(boardBean);
		if(count > 0) {
			count = projectDAO.insertProject(projectBean);
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
	
	public boolean insertWaiting(WaitingBean waitingBean) {
		System.out.println("ProjectProService - insertWaiting()");
		boolean isSuccess = false;
		
		int insertCount = projectDAO.insertWaiting(waitingBean);
		if(insertCount > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	
	// UPDATE ===================================================================================
	
	public boolean modifyProject(ProjectBean projectBean) { // 프로젝트 수정 서비스
		System.out.println("ProjectProService - modifyProject()");
		boolean isSuccess = false;
		int count = projectDAO.updateProject(projectBean);
		if(count > 0) {
			BoardBean boardBean = projectBean;
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
	
	public boolean updateStatus(int board_id, int board_creator_status) {
		System.out.println("ProjectProService - updateStatus()");
		boolean isSuccess = false;
		int count = projectDAO.updateStatus(board_id, board_creator_status);
		if(count>0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	// DELETE ===================================================================================
	
	public boolean dropProject(int board_id) { // 프로젝트 삭제 서비스
		System.out.println("ProjectProService - dropProject()");
		boolean isSuccess = false;
		int count = projectDAO.deleteProject(board_id);
		if(count > 0) {
			BoardDAO boardDAO = BoardDAO.getInstance(); // 2단계
			boardDAO.setConnection(con); // 3단계
			count = boardDAO.deleteBoard(board_id);
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
	
	public boolean dropWaiting(int board_id, String waiting_editor) { // 대기자 삭제 overloading
		System.out.println("ProjectProService - dropWaiting()");
		boolean isSuccess = false;
		int count = projectDAO.deleteWaiting(board_id, waiting_editor);
		if(count > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	
	public boolean dropWaiting(int board_id) { // 대기자 전체 삭제 overloading
		System.out.println("ProjectProService - dropWaiting()");
		boolean isSuccess = false;
		int count = projectDAO.deleteWaiting(board_id);
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

	public ArrayList<ProjectBean> getListProject(int startRow){ // 프로젝트 리스트 서비스
		System.out.println("ProjectProService - listProject()");
		ArrayList<ProjectBean> list = projectDAO.selectListProject(startRow);
		close(con);
		return list;
	}
	
	public ArrayList<ProjectBean> getListSearchProject(int page, HashMap<String, String> search){ // 프로젝트 검색 서비스
		System.out.println("ProjectProService - listProject()");
		ArrayList<ProjectBean> list = projectDAO.selectListSearchProject(page, search);
		close(con);
		return list;
	}
	
	public ArrayList<String> getWaitingDeclineList(int board_id, String waiting_editor){ // 선택받지 못한 자들의 리스트
		System.out.println("ProjectProService - getWaitingDeclineList()");
		ArrayList<String> list = projectDAO.selectWaitingDeclineList(board_id, waiting_editor);
		close(con);
		return list;
	}

	
	
}
