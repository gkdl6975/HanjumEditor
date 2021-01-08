package com.hanjum.intro.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.board.service.ProjectProService;
import com.hanjum.vo.ActionForward;

public class IntroFrontAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProjectGenreCountAction!");
		ActionForward forward = null;
		
		ProjectProService projectProService = new ProjectProService();
		HashMap<Integer, Integer> statusCount = projectProService.getStatusCount();
		request.setAttribute("projectStatusCount", statusCount);
		
		forward = new ActionForward();
		forward.setPath("/intro/intro.jsp");
		return forward;
	}

}
