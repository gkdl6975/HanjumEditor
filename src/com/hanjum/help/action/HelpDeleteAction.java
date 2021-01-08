package com.hanjum.help.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.help.service.HelpListService;
import com.hanjum.vo.ActionForward;
import com.hanjum.help.vo.HelpBean;

public class HelpDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("HelpDelectAction!");
		
		ActionForward forward = new ActionForward();
		int help_id = Integer.parseInt(request.getParameter("help_id"));
		
		HelpBean helpBean = new HelpBean();
		helpBean.setHelp_id(help_id);
		
		HelpListService helpListService = new HelpListService();
		boolean isDeleteSuccess = helpListService.deleteHelp(helpBean);
		
		if(!isDeleteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글삭제실패')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			forward = new ActionForward();
			forward.setPath("helpList.hp");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
