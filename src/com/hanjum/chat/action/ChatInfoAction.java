package com.hanjum.chat.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.chat.service.ChatListService;
import com.hanjum.chat.vo.ChatBean;
import com.hanjum.contract.service.ContractGetInfoService;
import com.hanjum.contract.vo.ContractBean;
import com.hanjum.vo.ActionForward;

public class ChatInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ChatInfoAction");
		ActionForward forward = null;
		
		ChatListService chatListService = new ChatListService();
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		
		ArrayList<ChatBean> list = chatListService.getListCount(board_id);
		ContractGetInfoService contractGetInfoService = new ContractGetInfoService();
		ContractBean contractBean = contractGetInfoService.getContractInfo(board_id);
		request.setAttribute("list", list);
		request.setAttribute("contractBean", contractBean);

		forward = new ActionForward();
		forward.setPath("chat/ChatContent.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
