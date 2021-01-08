package com.hanjum.help.action;

import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.help.service.HelpWriteProService;
import com.hanjum.vo.ActionForward;
import com.hanjum.help.vo.HelpBean;

public class HelpWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("HelpWriteProAction!");
		
		ActionForward forward =  null;

		String help_question = request.getParameter("help_question");
		String help_answer = request.getParameter("help_answer");

		HelpBean helpBean = new HelpBean();
		helpBean.setHelp_question(help_question);
		helpBean.setHelp_answer(help_answer);
		
		HelpWriteProService helpWriteProService = new HelpWriteProService();
		boolean isWriteSuccess = helpWriteProService.registArticle(helpBean);
		
		if(!isWriteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert('글 등록 실패!')"); 
			out.println("history.back()"); 
			out.println("</script>"); 
		} else {
			forward = new ActionForward();
			forward.setPath("helpList.hp");
			forward.setRedirect(true);
		}
		return forward;
		
	}
	
}
