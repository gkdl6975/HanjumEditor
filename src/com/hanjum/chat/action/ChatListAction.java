package com.hanjum.chat.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanjum.action.Action;
import com.hanjum.chat.service.ChatListService;
import com.hanjum.chat.vo.ChatListBean;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;



public class ChatListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		ChatListService chatListService = new ChatListService();
		
		ArrayList<ChatListBean> list = new ArrayList<ChatListBean>();
		
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean)session.getAttribute("userBean");
		
		list = chatListService.getChatlist(userBean.getUser_id());  
		request.setAttribute("list", list);
		
		
		forward = new ActionForward();
		forward.setPath("/chat/ChatWriteForm.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
