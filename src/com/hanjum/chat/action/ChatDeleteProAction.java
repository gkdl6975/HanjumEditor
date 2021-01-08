package com.hanjum.chat.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.chat.service.ChatDeleteProService;
import com.hanjum.vo.ActionForward;



public class ChatDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("ChatDeleteProAction");
		ActionForward forward=null;
		
		int chat_id = Integer.parseInt(request.getParameter("chat_id"));
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		String chat_editor_id = request.getParameter("chat_editor_id");
		String chat_creator_id = request.getParameter("chat_creator_id");
		
		boolean isArticleWriter= ChatDeleteProService.isArticleWriter(request.getParameter(chat_editor_id),request.getParameter("chat_creator_id"));
		if(!isArticleWriter) {
			response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<script>");
	    out.print("alert('삭제권한이 없습니다.!)");
	    out.print("history.back()");
	    out.print("</script>");
		}else {
		
			forward = new ActionForward();	
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
