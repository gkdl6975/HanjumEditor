package com.hanjum.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.notice.service.NoticeProService;
import com.hanjum.notice.vo.NoticeBean;
import com.hanjum.vo.ActionForward;

public class CancelContractByEditorAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// "editor"가 "계약"을 취소
		
		// notice_content == 9
		//  user_id(에디터)님께서 board_id 프로젝트 계약을 취소하였습니다.
		NoticeBean noticeBean = new NoticeBean();
		noticeBean.setBoard_id(Integer.parseInt(request.getParameter("board_id")));
		noticeBean.setNotice_content(9);
		noticeBean.setNotice_from_id(request.getParameter("notice_from_id"));
//		noticeBean.setNotice_id(Integer.parseInt(request.getParameter("notice_id")));
		// noticeBean.setNotice_read(Integer.parseInt(request.getParameter("notice_read")));
//		noticeBean.setNotice_url(request.getParameter("notice_url"));
		noticeBean.setUser_id(request.getParameter("user_id"));
		
		NoticeProService service = new NoticeProService();
		service.insertNotice(noticeBean);
		
		// notice_content == 10
		//user_id(크리에이터)님, notice_from_id 님께서 board_id 계약을 취소하였습니다.
		NoticeBean noticeBean2 = new NoticeBean();
		noticeBean2.setBoard_id(Integer.parseInt(request.getParameter("board_id")));
		noticeBean2.setNotice_content(10);
		noticeBean2.setNotice_from_id(request.getParameter("user_id"));
		noticeBean2.setNotice_id(Integer.parseInt(request.getParameter("notice_id")));
		// noticeBean2.setNotice_read(Integer.parseInt(request.getParameter("notice_read")));
		noticeBean2.setNotice_url(request.getParameter("notice_url"));
		noticeBean2.setUser_id(request.getParameter("notice_from_id"));
					
		NoticeProService service2 = new NoticeProService();
		service2.insertNotice(noticeBean2);
		
		return null;
	}

}
