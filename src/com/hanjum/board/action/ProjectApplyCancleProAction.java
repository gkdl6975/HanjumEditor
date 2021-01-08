package com.hanjum.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanjum.action.Action;
import com.hanjum.board.service.BoardProService;
import com.hanjum.board.service.ProjectProService;
import com.hanjum.board.vo.WaitingBean;
import com.hanjum.contract.service.ContractDeleteService;
import com.hanjum.notice.service.NoticeProService;
import com.hanjum.notice.vo.NoticeBean;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;
import com.hanjum.vo.Constant;

public class ProjectApplyCancleProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProjectApplyCancleProAction!");
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean)session.getAttribute("userBean");
		
		String page = request.getParameter("page");
		String waiting_editor = userBean.getUser_id();
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		
		BoardProService boardProService = new BoardProService();
		String writer_id = boardProService.getUser_id(board_id);
		
		// 대기자 리스트에서 삭제
		ProjectProService projectProService = new ProjectProService();
		boolean isSuccess = projectProService.dropWaiting(board_id, waiting_editor); //지원 취소
		if(isSuccess) {
			// 각자에게 알림
			NoticeBean noticeBean = new NoticeBean();
			noticeBean.setBoard_id(board_id);
			noticeBean.setNotice_content(7);
			noticeBean.setNotice_from_id(userBean.getUser_id());
			noticeBean.setNotice_url("Project.bo?board_id="+board_id);
			noticeBean.setUser_id(userBean.getUser_id());
			
			NoticeProService noticeProService = new NoticeProService();
			noticeProService.insertNotice(noticeBean);
			
			noticeBean.setNotice_content(8);
			noticeBean.setNotice_from_id(userBean.getUser_id());
			noticeBean.setUser_id(writer_id);
			
			noticeProService = new NoticeProService();
			noticeProService.insertNotice(noticeBean);
			
			// 계약전 Contract 삭제
			ContractDeleteService contractDeleteService = new ContractDeleteService();
			isSuccess = contractDeleteService.deleteContract(board_id, userBean.getUser_id());
			
			if(isSuccess) {
				forward = new ActionForward();
				forward.setPath("Project.bo?board_id="+board_id+"&page="+page);
				forward.setRedirect(true);
			}
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(Constant.arrMsg);
		}
		
		return forward;
	}

}
