package com.hanjum.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.notice.service.NoticeProService;
import com.hanjum.vo.ActionForward;

public class NoticeDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int notice_id = Integer.parseInt(request.getParameter("notice_id"));
		
		NoticeProService service = new NoticeProService();
		service.deleteNotice(notice_id);
		
		return null;
	}

}
