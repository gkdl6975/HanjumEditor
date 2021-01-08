package com.hanjum.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.user.service.UserProService;
import com.hanjum.vo.ActionForward;

public class UserCheckIdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String user_id = request.getParameter("user_id");
		System.out.println(user_id);
		UserProService userIdCheckService = new UserProService();
		int data = userIdCheckService.UserCheckId(user_id);
		System.out.println("서비스 성공!" + data);
		
		if(data>0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter(); 
			System.out.println(1);
			out.println("1");
			out.close();
		} 
		
		// 이것도됨 ↓ 코드저장용 보류 삭제 금지
//		else {
			// 1. ActionForward 객체 생성
//			forward = new ActionForward();
			// 2. 포워딩 경로(URL) 지정 (주의! 경로명 앞에 슬래시(/) 기호를 붙이지 말 것!)
//			forward.setPath("UserLoginForm.uo");
			// 3. 포워딩 방식(Redirect 방식) 지정
//			forward.setRedirect(true);
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = response.getWriter(); 
//			out.println("<script>"); // 자바스크립트 시작 태그
//			out.println("document.getElementById('checkIdResult').innerHTML = '아이디를 입력해주세요'");
//			out.println("</script>"); // 자바스크립트 끝 태그
			
			
//		}
		
		return forward;
	}

}
