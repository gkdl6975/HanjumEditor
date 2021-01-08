package com.hanjum.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.user.service.UserProService;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;

public class UserInsertProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UserInsertProAction!");
		
		ActionForward forward = null; 
		
		UserBean userBean = new UserBean();
		userBean.setUser_id(request.getParameter("user_id"));
		userBean.setUser_pass(request.getParameter("user_pass"));
		userBean.setUser_name(request.getParameter("user_name"));
		userBean.setUser_email(request.getParameter("user_email")+"@"+request.getParameter("user_email2"));
		userBean.setUser_phone(request.getParameter("user_phone"));
		userBean.setUser_type(1);
		System.out.println(userBean.getUser_id());
		UserProService userInsertProServic = new UserProService();
		boolean isSuccess = userInsertProServic.insertUser(userBean);
		System.out.println("서비스 성공!");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter(); 
		if(!isSuccess) {
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('회원 가입 실패!')"); // 다이얼로그 메세지 출력
			out.println("history.back()"); // 이전 페이지로 이동
			out.println("</script>"); // 자바스크립트 끝 태그
		} else {
			// 1. ActionForward 객체 생성
//			forward = new ActionForward();
			// 2. 포워딩 경로(URL) 지정 (주의! 경로명 앞에 슬래시(/) 기호를 붙이지 말 것!)
//			forward.setPath("UserLoginForm.uo");
			// 3. 포워딩 방식(Redirect 방식) 지정
//			forward.setRedirect(true);
			
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('회원 등록에 성공했습니다.')"); // 다이얼로그 메세지 출력
			out.println("location.href='home'");
			out.println("</script>"); // 자바스크립트 끝 태그
			
			
		}
		// 4. ActionForward 객체 리턴 => BoardFrontController 클래스로 전달
		
		return forward;
	}

}
