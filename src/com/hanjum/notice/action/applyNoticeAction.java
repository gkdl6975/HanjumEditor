package com.hanjum.notice.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.hanjum.action.Action;
import com.hanjum.notice.service.NoticeProService;
import com.hanjum.notice.vo.NoticeBean;
import com.hanjum.vo.ActionForward;

public class applyNoticeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("action - applyNoticeAction");
		
		String user_id = request.getParameter("user_id");
		
		// user_id = editor(신청자), notice_from_id = creator(프로젝트하는사람)
		// notice_content == 1 : user_id 님이  notice_from_id 님의 프로젝트 board_id번을 신청하였습니다
		NoticeBean noticeBean = new NoticeBean();
		noticeBean.setBoard_id(Integer.parseInt(request.getParameter("board_id")));
		noticeBean.setNotice_content(1);
		noticeBean.setNotice_from_id(request.getParameter("notice_from_id"));
		noticeBean.setNotice_id(Integer.parseInt(request.getParameter("notice_id")));
		// noticeBean1.setNotice_read(Integer.parseInt(request.getParameter("notice_read")));
		noticeBean.setNotice_url(request.getParameter("notice_url"));
		noticeBean.setUser_id(request.getParameter("user_id"));
		
		
		NoticeProService service = new NoticeProService();
		service.insertNotice(noticeBean);

		// ======================================================================
		
		// user_id = creator(프로젝트진행하는사람), notice_from_id = editor(프로젝트하는사람)
		NoticeBean noticeBean2 = new NoticeBean();
		noticeBean2.setBoard_id(Integer.parseInt(request.getParameter("board_id")));
		noticeBean2.setNotice_content(2);
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
