package com.hanjum.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanjum.action.Action;
import com.hanjum.notice.service.NoticeProService;
import com.hanjum.user.service.UserProService;
import com.hanjum.user.vo.EditorBean;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;

public class UserInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		UserBean userSession = (UserBean)session.getAttribute("userBean");
		String user_id = userSession.getUser_id();
		UserProService userInfoService = new UserProService();
		UserBean userBean = userInfoService.getUserInfo(user_id);
		request.setAttribute("userBean", userBean);

		NoticeProService noticeProService = new NoticeProService();
		int noticeCount = noticeProService.getNoticeCount(user_id);
		request.setAttribute("noticeCount", noticeCount);
		if(userBean != null) {
			forward = new ActionForward();
			forward.setPath("user/userInfo.jsp");
		}
		return forward;
	}

}
