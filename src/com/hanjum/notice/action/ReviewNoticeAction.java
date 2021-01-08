package com.hanjum.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.notice.service.NoticeProService;
import com.hanjum.notice.vo.NoticeBean;
import com.hanjum.vo.ActionForward;

public class ReviewNoticeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// notice_content == 15
		// user_id(에디터)님, notice_from_id(크리에이터)님이 작성한 리뷰를 확인해보세요 !
		NoticeBean noticeBean = new NoticeBean();
		noticeBean.setBoard_id(Integer.parseInt(request.getParameter("board_id")));
		noticeBean.setNotice_content(15);
		noticeBean.setNotice_from_id(request.getParameter("user_id"));
		noticeBean.setNotice_id(Integer.parseInt(request.getParameter("notice_id")));
		// noticeBean2.setNotice_read(Integer.parseInt(request.getParameter("notice_read")));
		noticeBean.setNotice_url(request.getParameter("notice_url"));
		noticeBean.setUser_id(request.getParameter("notice_from_id"));
					
		NoticeProService service2 = new NoticeProService();
		service2.insertNotice(noticeBean);
		return null;
	}

}
