package com.hanjum.notice.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanjum.action.Action;
import com.hanjum.notice.service.NoticeProService;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;

public class NoticeUpdateStatusAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("NoticeUpdateStatusAction");
		
		// 새 알람을 읽은알람으로 전환
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean)session.getAttribute("userBean");
		
		NoticeProService service = new NoticeProService();
		service.updateStatus(userBean.getUser_id());
		
		return null;
	}

}
