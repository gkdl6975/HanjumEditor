package com.hanjum.user.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanjum.action.Action;
import com.hanjum.board.service.BoardProService;
import com.hanjum.board.service.EditorProService;
import com.hanjum.board.vo.BoardBean;
import com.hanjum.board.vo.EditorBean;
import com.hanjum.user.service.UserProService;
import com.hanjum.user.vo.PortfolioBean;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;

public class UserPortfolioListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("UserPortfolioListAction!");
		
		UserProService userProService = new UserProService();
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean)session.getAttribute("userBean");
		ArrayList<PortfolioBean> list = userProService.getPortfolioList(userBean.getUser_id());
		int count = userProService.getCountPortfolio(userBean.getUser_id());
		request.setAttribute("portfolioList", list);
		request.setAttribute("pf_count", count);
		EditorProService editorProService = new EditorProService();
		EditorBean editorBean = editorProService.getEditor(userBean.getUser_id());
		if(editorBean != null) {
			request.setAttribute("editorBoard", editorBean);
		}

		forward = new ActionForward();
		forward.setPath("/user/userPortfolioList.jsp");
		
		return forward;
	}

}
