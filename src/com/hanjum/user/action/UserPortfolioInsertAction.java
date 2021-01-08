package com.hanjum.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanjum.action.Action;
import com.hanjum.user.service.UserProService;
import com.hanjum.user.vo.PortfolioBean;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;

public class UserPortfolioInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("UserPortfolioInsertAction!");
		
		UserProService userProService = new UserProService();
		PortfolioBean portfolioBean = new PortfolioBean();
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean)session.getAttribute("userBean");
		portfolioBean.setUser_id(userBean.getUser_id());
		portfolioBean.setEditor_pf_category(request.getParameter("editor_pf_category"));
		portfolioBean.setEditor_pf_link(request.getParameter("editor_pf_link"));
		portfolioBean.setEditor_pf_subject(request.getParameter("editor_pf_subject"));
		boolean isInsertSuccess = userProService.insertPortfolio(portfolioBean);
		
		if(isInsertSuccess) {
			System.out.println("Insert Success");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('작성에 성공하였습니다.')");
//			out.println("history.back()");
			out.println("</script>");
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('작성에 실패하였습니다.')"); // 실패 메세지 출력
//			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
