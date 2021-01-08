package com.hanjum.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.user.service.UserProService;
import com.hanjum.vo.ActionForward;

public class UserUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UserUpdateProAction!");
		
		ActionForward forward = null; 
		
		String user_id = request.getParameter("user_id");
		String content = request.getParameter("content");
		String target = request.getParameter("target");
		
		UserProService userProService = new UserProService();
		boolean isUpdateSuccess = userProService.updateUser(user_id, content, target);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter(); 
		out.print(isUpdateSuccess);

		return forward;
	}

}
