package com.hanjum.notice.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.hanjum.action.Action;
import com.hanjum.notice.service.NoticeProService;
import com.hanjum.notice.vo.NoticeBean;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;

public class NoticeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean)session.getAttribute("userBean");
		
		// NoticeProService에 수행할 작업 요청
		NoticeProService service = new NoticeProService();
		ArrayList<NoticeBean> noticeList = service.getNoticeList(userBean.getUser_id());
		ArrayList<NoticeBean> noticeNewList = new ArrayList<NoticeBean>();
		ArrayList<NoticeBean> noticeOldList = new ArrayList<NoticeBean>();
		for (int i = 0; i < noticeList.size(); i++) {
			if(noticeList.get(i).getNotice_read() == 0) {
				NoticeBean noticeBean = new NoticeBean();
				noticeBean.setBoard_id(noticeList.get(i).getBoard_id());
				noticeBean.setUser_id(noticeList.get(i).getUser_id());
				noticeBean.setNotice_content(noticeList.get(i).getNotice_content());
				noticeBean.setNotice_date(noticeList.get(i).getNotice_date());
				noticeBean.setNotice_from_id(noticeList.get(i).getNotice_from_id());
				noticeBean.setNotice_id(noticeList.get(i).getNotice_id());
				noticeBean.setNotice_read(noticeList.get(i).getNotice_read());
				noticeBean.setNotice_url(noticeList.get(i).getNotice_url());
				noticeNewList.add(noticeBean);
			} else {
				NoticeBean noticeBean = new NoticeBean();
				noticeBean.setBoard_id(noticeList.get(i).getBoard_id());
				noticeBean.setUser_id(noticeList.get(i).getUser_id());
				noticeBean.setNotice_content(noticeList.get(i).getNotice_content());
				noticeBean.setNotice_date(noticeList.get(i).getNotice_date());
				noticeBean.setNotice_from_id(noticeList.get(i).getNotice_from_id());
				noticeBean.setNotice_id(noticeList.get(i).getNotice_id());
				noticeBean.setNotice_read(noticeList.get(i).getNotice_read());
				noticeBean.setNotice_url(noticeList.get(i).getNotice_url());
				noticeOldList.add(noticeBean);
			}
		}
		request.setAttribute("noticeNewList", noticeNewList);
		request.setAttribute("noticeOldList", noticeOldList);
		
		forward = new ActionForward();
		forward.setPath("/notice/userNoticeList.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
