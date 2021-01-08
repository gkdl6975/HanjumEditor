package com.hanjum.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanjum.action.Action;
import com.hanjum.board.service.EditorProService;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;
import com.hanjum.vo.Constant;

public class EditorDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("EditorDeleteProAction!");
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		UserBean userSession = (UserBean)session.getAttribute("userBean");
		String user_id = userSession.getUser_id();
		
		EditorProService editorProService = new EditorProService();
		boolean isSuccess = editorProService.dropEditor(user_id);
		
		if(!isSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(Constant.arrMsg);
		} else {
			forward = new ActionForward();
			forward.setPath("EditorList.bo");
			forward.setRedirect(false);
		}
		return forward;
	}
	
}
