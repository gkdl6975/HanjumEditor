package com.hanjum.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanjum.action.Action;
import com.hanjum.board.service.BoardProService;
import com.hanjum.board.service.ProjectProService;
import com.hanjum.board.vo.BoardBean;
import com.hanjum.board.vo.ProjectBean;
import com.hanjum.contract.service.ContractCheckStatusService;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;
import com.mysql.jdbc.PreparedStatement.ParseInfo;

public class ProjectInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProjectInfoAction!");
		ActionForward forward = null;
		int board_id = 0;
		
		HttpSession session = request.getSession();
		UserBean userBean = null;
		if(session.getAttribute("userBean") != null) {
			userBean = (UserBean)session.getAttribute("userBean");
		}
		
		if(request.getParameter("board_id") != null) {
			board_id = Integer.parseInt(request.getParameter("board_id"));
		}
		
		BoardProService boardProService = new BoardProService();
		BoardBean boardBean = boardProService.getBoard(board_id);
		
		ProjectProService projectProService = new ProjectProService();
		ProjectBean project = projectProService.getProject(boardBean);
		
//		ContractCheckStatusService checkStatusService = new ContractCheckStatusService();
//		int contract_status = checkStatusService.checkContractStatus(board_id);
		projectProService = new ProjectProService();
		int checkProject = projectProService.CheckProjectStatus(board_id);
		
		projectProService = new ProjectProService();
		int check = projectProService.CheckApplyProject(userBean.getUser_id(), board_id);
		
		
		String page = "1";
		if(request.getParameter("page") != null) {
			page = request.getParameter("page");
		}
		request.setAttribute("page", page);
		request.setAttribute("project", project);
		request.setAttribute("checkProject", checkProject);
		request.setAttribute("checkApply", check);
		
		forward = new ActionForward();
		forward.setPath("/project/projectContent.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
