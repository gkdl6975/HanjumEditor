package com.hanjum.help.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.help.service.HelpListService;
import com.hanjum.vo.ActionForward;
import com.hanjum.help.vo.HelpBean;

public class HelpUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("HelpUpdateAction!");
		
		ActionForward forward = null;
		
		
		int help_id = Integer.parseInt(request.getParameter("help_id"));
		HelpBean helpBean = new HelpBean();
		helpBean.setHelp_id(help_id);
		helpBean.setHelp_question(request.getParameter("help_question"));
		helpBean.setHelp_answer(request.getParameter("help_answer"));
		
		HelpListService helpListService = new HelpListService();
		boolean isUpdateSuccess= helpListService.updateHelp(helpBean);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(!isUpdateSuccess) {
			out.println("<script>");
			out.println("alert('글 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('글 수정 성공')");
			out.println("</script>");
			forward = new ActionForward();
			forward.setPath("helpList.hp");
			forward.setRedirect(true);
		}
		
		
		return forward;
	}


}
