package com.hanjum.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanjum.action.Action;
import com.hanjum.board.service.BoardProService;
import com.hanjum.board.service.ProjectProService;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;
import com.hanjum.vo.Constant;

public class ProjectDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProjectDeleteProAction!");
		ActionForward forward = null;
		
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		HttpSession session = request.getSession();
		UserBean userSession = (UserBean)session.getAttribute("userBean");
		String user_id = userSession.getUser_id();
		BoardProService boardProService = new BoardProService();
		boolean isWriter = boardProService.checkBoardWriter(board_id, user_id);

		if(isWriter) {
			ProjectProService projectProService = new ProjectProService();
			
			boolean isSuccess = projectProService.dropProject(board_id);
			
			if(!isSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println(Constant.arrMsg);
				
			} else {
				forward = new ActionForward();
				forward.setPath("ProjectList.bo");
				forward.setRedirect(true);
			}
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(Constant.isNotWriter);
		}
		return forward;
	}
	
}
