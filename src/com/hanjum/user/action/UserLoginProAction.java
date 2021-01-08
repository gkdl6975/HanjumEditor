package com.hanjum.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanjum.action.Action;
import com.hanjum.user.exception.LoginException;
import com.hanjum.user.service.UserProService;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;

public class UserLoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UserLoginProAction!");
		String prefPage = "home";
		if(request.getParameter("prefPage") != null && !request.getParameter("prefPage").equals("null")) {
			prefPage = request.getParameter("prefPage");
			if(prefPage.contains("My") || prefPage.contains("Logout")){
				prefPage = "My.uo";
			}
			if(prefPage.contains("Project")) {
				prefPage = "ProjectList.bo";
			}
		}
		ActionForward forward = null;
		
		String user_id = request.getParameter("user_id");
		String user_pass = request.getParameter("user_pass");
		
		UserProService userLoginProService = new UserProService();
		
		try {
			UserBean userBean = userLoginProService.loginUser(user_id, user_pass);
			System.out.println("서비스 성공!" + userBean);
			
		if(userBean != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userBean", userBean);
			System.out.println(userBean.getUser_type());
			System.out.println(userBean.getUser_id());
			// 1. ActionForward 객체 생성
			forward = new ActionForward();
			// 2. 포워딩 경로(URL) 지정 (주의! 경로명 앞에 슬래시(/) 기호를 붙이지 말 것!)
			forward.setPath(prefPage);
			// 3. 포워딩 방식(Redirect 방식) 지정
			forward.setRedirect(true);
			} 
		} catch (LoginException e) {
			e.printStackTrace();
			// LoginException 예외가 발생하여 외부로 위임되고
			// 현재 위치에서 catch 문을 통해 예외를 전달받게 됨
			// => 전달받은 예외 객체의 메세지를 자바스크립트로 출력하면
			//    로그인 실패 원인에 대한 결과 메세지 출력 구분 가능
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + e.getMessage() + "')"); // 실패 메세지 출력
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}

