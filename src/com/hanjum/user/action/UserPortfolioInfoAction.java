package com.hanjum.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.user.service.UserProService;
import com.hanjum.user.vo.PortfolioBean;
import com.hanjum.vo.ActionForward;

public class UserPortfolioInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		UserProService userProService = new UserProService();
		int editor_pf_id = Integer.parseInt(request.getParameter("editor_pf_id"));
		PortfolioBean portfolioBean = userProService.getPortfolioInfo(editor_pf_id);
		request.setAttribute("portfolioBean",portfolioBean);
		
		forward = new ActionForward();
		forward.setPath("/user/userPortfolioUpdate.jsp");

		return forward;
	}

}
