package com.hanjum.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.user.service.UserProService;
import com.hanjum.user.vo.PortfolioBean;
import com.hanjum.vo.ActionForward;

public class UserPortfolioUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UserPortfolioUpdateAction");
		
		PortfolioBean portfolioBean = new PortfolioBean();
		portfolioBean.setEditor_pf_category(request.getParameter("editor_pf_category"));
		portfolioBean.setEditor_pf_link(request.getParameter("editor_pf_link"));
		portfolioBean.setEditor_pf_subject(request.getParameter("editor_pf_subject"));
		portfolioBean.setEditor_pf_id(Integer.parseInt(request.getParameter("editor_pf_id")));
		UserProService userProService = new UserProService();
		boolean isUpdateSuccess = userProService.updatePortfolio(portfolioBean);
		if(isUpdateSuccess) {
			System.out.println("update Success");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정에 성공하였습니다.')");
//			out.println("history.back()");
			out.println("</script>");
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정에 실패하였습니다.')");
//			out.println("history.back()");
			out.println("</script>");
		}
		return null;
	}

}
