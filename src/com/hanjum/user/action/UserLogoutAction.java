package com.hanjum.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanjum.action.Action;
import com.hanjum.vo.ActionForward;

public class UserLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Logout Action!");
		ActionForward forward = null;
		
		// request 객체로부터 HttpSession 객체 가져와서 세션 초기화
		HttpSession session = request.getSession();
		session.invalidate(); // 초기화
//		session.removeAttribute("user_id"); // 아이디만 초기화
		// 만약, id에 해당하는 세션값만 삭제해야할 경우
		forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./");
		// 메인페이지로 포워딩
		
		
		
		return forward;
	}

}
