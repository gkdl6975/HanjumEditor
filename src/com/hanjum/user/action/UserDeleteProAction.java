package com.hanjum.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanjum.action.Action;
import com.hanjum.user.exception.LoginException;
import com.hanjum.user.service.UserProService;
import com.hanjum.vo.ActionForward;

public class UserDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UserDeleteProAction!");
		
		ActionForward forward = null;
		
		String user_id = request.getParameter("user_id");
		String user_pass=request.getParameter("user_pass");
		
		UserProService userDeleteProService = new UserProService();
		boolean success = false;
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			success = userDeleteProService.deleteUser(user_id, user_pass);
			if(success) {
				HttpSession session = request.getSession();
				session.invalidate(); // 초기화
				out.println("<script>");
				out.println("alert('정상적으로 탈퇴 되었습니다.')"); // 탈퇴 메세지 출력
				out.println("location.href='./'");
				out.println("</script>");
			}
		} catch (LoginException e) {
			e.printStackTrace();
			out.println("<script>");
			out.println("alert('" + e.getMessage() + "')"); // 실패 메세지 출력
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}
	
}
