package com.hanjum.notice.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.board.service.ProjectProService;
import com.hanjum.contract.service.ContractDeleteService;
import com.hanjum.notice.service.NoticeProService;
import com.hanjum.notice.vo.NoticeBean;
import com.hanjum.vo.ActionForward;
import com.hanjum.vo.Constant;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

public class DeclineNoticeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// 해당 notice_id 를 읽음으로 바꿈
	 	int notice_id = Integer.parseInt( request.getParameter("notice_id")); 
	 	
	 	NoticeProService service = new NoticeProService();
		NoticeBean noticeInfo = service.getNotice(notice_id);
	// user_id = creator(거절한사람), notice_from_id = editor(거절당한사람)
		// notice_content == 5 :   notice_from_id 님의 프로젝트 제안을 거절했습니당ㅋㅋㅋ
		NoticeBean noticeBean = new NoticeBean();
		noticeBean.setBoard_id(noticeInfo.getBoard_id());
		noticeBean.setNotice_content(5);
		noticeBean.setNotice_from_id(noticeInfo.getNotice_from_id());
//		noticeBean.setNotice_id(Integer.parseInt(request.getParameter("notice_id")));
		// noticeBean1.setNotice_read(Integer.parseInt(request.getParameter("notice_read")));
		noticeBean.setNotice_url(noticeInfo.getNotice_url());
		noticeBean.setUser_id(noticeInfo.getUser_id());
		
		service = new NoticeProService();
		service.insertNotice(noticeBean);
		
		service = new NoticeProService();
		service.deleteNotice(notice_id); // 해당 notice_읽음; 같은 변수명쓰면 Connection is null error ! 
		
			// ======================================================================
		
		// user_id = editor(거절당한사람), notice_from_id = creator(거절하는 사람)
		// user_from_id 님께서 user_id님의 제안을 거절하였습니당 
		noticeBean.setNotice_content(6);
		noticeBean.setNotice_from_id(noticeInfo.getUser_id());
		// noticeBean2.setNotice_read(Integer.parseInt(request.getParameter("notice_read")));
		noticeBean.setUser_id(noticeInfo.getNotice_from_id());
					
		service = new NoticeProService();
		service.insertNotice(noticeBean);
		
		// Waiting 대기자 삭제
		ProjectProService projectProService = new ProjectProService();
		boolean isSuccess = projectProService.dropWaiting(noticeInfo.getBoard_id(), noticeInfo.getNotice_from_id());
		if(isSuccess) {
			ContractDeleteService contractDeleteService = new ContractDeleteService();
			isSuccess = contractDeleteService.deleteContract(noticeInfo.getBoard_id(), noticeInfo.getNotice_from_id());
			if(isSuccess) {
				forward = new ActionForward();
				forward.setPath("My.uo?fr=notice");
				forward.setRedirect(true);
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println(Constant.arrMsg);
			}
		}
		
		return forward;
	}

}
