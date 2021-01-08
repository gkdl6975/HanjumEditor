package com.hanjum.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.board.action.EditorInfoAction;
import com.hanjum.board.action.EditorListAction;
import com.hanjum.board.action.EditorSearchListAction;
import com.hanjum.board.action.EditorUpdateAction;
import com.hanjum.board.action.EditorUpdateProAction;
import com.hanjum.board.action.EditorWriteProAction;
import com.hanjum.board.action.ProjectApplyCancleProAction;
import com.hanjum.board.action.ProjectApplyProAction;
import com.hanjum.board.action.ProjectApplySendAction;
import com.hanjum.board.action.ProjectContractCancleProAction;
import com.hanjum.board.action.ProjectContractSuccessProAction;
import com.hanjum.board.action.ProjectDeleteProAction;
import com.hanjum.board.action.ProjectInfoAction;
import com.hanjum.board.action.ProjectListAction;
import com.hanjum.board.action.ProjectUpdateAction;
import com.hanjum.board.action.ProjectSearchListAction;
import com.hanjum.board.action.ProjectUpdateProAction;
import com.hanjum.board.action.ProjectWriteProAction;
import com.hanjum.vo.ActionForward;
import com.hanjum.vo.Constant;

@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getServletPath();
		System.out.println("요청 서블릿 주소 : " + command);
		
		Action action = null;
		ActionForward forward = null;
		
//============================== FRONT ======================================

		 if(command.equals("/ProjectWrite.bo")) { // 프로젝트 글쓰기
			forward = new ActionForward();
			forward.setPath("/project/projectWrite.jsp");
		}  else if(command.equals("/EditorWrite.bo")) { //편집자 글쓰기
			forward = new ActionForward();
			forward.setPath("/editor/editorWrite.jsp");
		}
//============================== BACK ======================================
		else if(command.equals("/Project.bo")) { // 프로젝트 조회 액션
			action = new ProjectInfoAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if(command.equals("/ProjectWritePro.bo")) { // 프로젝트 작성 액션
			action = new ProjectWriteProAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if(command.equals("/ProjectUpdate.bo")) { // 프로젝트 수정
			action = new ProjectUpdateAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if(command.equals("/ProjectUpdatePro.bo")) { // 프로젝트 수정 액션
			action = new ProjectUpdateProAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if(command.equals("/ProjectDeletePro.bo")) { // 프로젝트 삭제 액션
			action = new ProjectDeleteProAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if(command.equals("/ProjectList.bo")) { // 프로젝트 리스트 액션
			action = new ProjectListAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if(command.equals("/ProjectListSearch.bo")) { // 프로젝트 검색 리스트 액션
			action = new ProjectSearchListAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if(command.equals("/Editor.bo")) { // 편집자 조회 액션
			action = new EditorInfoAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if(command.equals("/EditorWritePro.bo")) { // 편집자 작성 액션
			action = new EditorWriteProAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		}  else if(command.equals("/EditorUpdate.bo")) { //편집자 수정
			action = new EditorUpdateAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		}  else if(command.equals("/EditorUpdatePro.bo")) { // 편집자 수정 액션
			action = new EditorUpdateProAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if(command.equals("/EditorList.bo")) { // 편집자 리스트 액션
			action = new EditorListAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if(command.equals("/EditorListSearch.bo")) { // 편집자 검색 리스트 액션
			action = new EditorSearchListAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if(command.equals("/ProjectApplyPro.bo")) { // 편집자 검색 리스트 액션
			action = new ProjectApplyProAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if(command.equals("/ProjectApplyCanclePro.bo")) { // 편집자 검색 리스트 액션
			action = new ProjectApplyCancleProAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if(command.equals("/ProjectContractCanclePro.bo")) { // 편집자 검색 리스트 액션
			action = new ProjectContractCancleProAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if(command.equals("/ProjectContractSuccessPro.bo")) { // 편집자 검색 리스트 액션
			action = new ProjectContractSuccessProAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if(command.equals("/ProjectApplySend.bo")) { // 편집자 검색 리스트 액션
			action = new ProjectApplySendAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		}
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
