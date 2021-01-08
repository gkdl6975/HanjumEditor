package com.hanjum.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanjum.action.Action;
import com.hanjum.board.service.BoardProService;
import com.hanjum.board.service.EditorProService;
import com.hanjum.board.service.ProjectProService;
import com.hanjum.board.vo.BoardBean;
import com.hanjum.board.vo.ProjectBean;
import com.hanjum.board.vo.WaitingBean;
import com.hanjum.contract.service.ContractInsertProService;
import com.hanjum.contract.vo.ContractBean;
import com.hanjum.notice.service.NoticeProService;
import com.hanjum.notice.vo.NoticeBean;
import com.hanjum.user.service.UserProService;
import com.hanjum.user.vo.EditorBean;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;
import com.hanjum.vo.Constant;

public class ProjectApplyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProjectApplyProAction!");
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean)session.getAttribute("userBean");
		
		String page = request.getParameter("page");
		String user_id = userBean.getUser_id();
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		int waiting_price = Integer.parseInt(request.getParameter("contract_price"));
		
		BoardProService boardProService = new BoardProService();
		String writer_id = boardProService.getUser_id(board_id);

		EditorProService editorProService = new EditorProService();
		com.hanjum.board.vo.EditorBean editorBoard = editorProService.getEditor(user_id);
		
		WaitingBean waitingBean = new WaitingBean();
		waitingBean.setBoard_id(board_id);
		waitingBean.setUser_id(writer_id);
		waitingBean.setWaiting_editor(user_id);
		waitingBean.setWaiting_price(waiting_price);
		ProjectProService projectProService = new ProjectProService();
		boolean isSuccess = projectProService.insertWaiting(waitingBean);
		if(isSuccess) {
			NoticeBean noticeBean = new NoticeBean();
			noticeBean.setBoard_id(board_id);
			noticeBean.setNotice_content(1);
			noticeBean.setNotice_from_id(writer_id);
			noticeBean.setNotice_url("Project.bo?board_id="+board_id);
			noticeBean.setUser_id(user_id);
			
			NoticeProService noticeProService = new NoticeProService();
			noticeProService.insertNotice(noticeBean);
			noticeBean.setNotice_content(2);
			noticeBean.setNotice_from_id(user_id);
			noticeBean.setNotice_url("Editor.bo?board_id="+editorBoard.getBoard_id());
			noticeBean.setUser_id(writer_id);
			
			noticeProService = new NoticeProService();
			noticeProService.insertNotice(noticeBean);
			
			boardProService = new BoardProService();
			BoardBean boardBean = boardProService.getBoard(board_id);
			
			projectProService = new ProjectProService();
			ProjectBean projectBean = projectProService.getProject(boardBean);
			
			UserProService userProService = new UserProService();
			EditorBean editorBean = userProService.getEditorInfo(user_id);
			
			ContractBean contractBean = new ContractBean();
			
//			cBean.setBoard_id(board_id);  //없어도 Contract 테이블 속성으로 Autoincrement 설정해서 자동으로 부여됨
			contractBean.setBoard_subject(projectBean.getBoard_subject());
			contractBean.setContract_creator(projectBean.getUser_id());
			contractBean.setContract_editor(user_id);
			contractBean.setContract_price(waiting_price);
			contractBean.setContract_status(1);
			contractBean.setBoard_id(projectBean.getBoard_id());  // 외래키  받아와야함
			contractBean.setBoard_ed_address(editorBean.getEditor_address());
			
			ContractInsertProService contractInsertProService = new ContractInsertProService();
			isSuccess = contractInsertProService.registArticle(contractBean);
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
