package com.hanjum.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.user.service.UserProService;
import com.hanjum.vo.ActionForward;

public class UserPortfolioDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UserPortfolioDeleteAction");
		int editor_pf_id = Integer.parseInt(request.getParameter("editor_pf_id"));
		UserProService userProService = new UserProService();
		boolean isDeleteSuccess = userProService.deletePortfolio(editor_pf_id);
		if(isDeleteSuccess) {
			System.out.println("delete Success");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제에 성공하였습니다.')");
//			out.println("history.back()");
			out.println("</script>");
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제에 실패하였습니다.')");
//			out.println("history.back()");
			out.println("</script>");
		}
		return null;
	}

}
