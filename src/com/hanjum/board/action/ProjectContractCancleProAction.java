package com.hanjum.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanjum.action.Action;
import com.hanjum.board.service.BoardProService;
import com.hanjum.board.service.ProjectProService;
import com.hanjum.board.vo.WaitingBean;
import com.hanjum.chat.service.ChatDeleteProService;
import com.hanjum.contract.service.ContractGetInfoService;
import com.hanjum.contract.service.ContractUpdateStatusService;
import com.hanjum.contract.vo.ContractBean;
import com.hanjum.notice.service.NoticeProService;
import com.hanjum.notice.vo.NoticeBean;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;
import com.hanjum.vo.Constant;

public class ProjectContractCancleProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProjectContractCancleProAction!");
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean)session.getAttribute("userBean");
		
		String page = request.getParameter("page");
		
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		
		BoardProService boardProService = new BoardProService();
		String writer_id = boardProService.getUser_id(board_id);
		boolean isWriter = false;
		if(writer_id.equals(userBean.getUser_id())) { // 취소를 누른사람이 에디터인지 크리에디터인지 구분
			isWriter = true;
		}
		
		ProjectProService projectProService = new ProjectProService();
		
		boolean isSuccess = false;
		
		isSuccess = projectProService.updateStatus(board_id, 0); // 프로젝트 상태 계약전으로 변경
		if(isSuccess) {
			// Contract 상태 변경
			ContractUpdateStatusService contractUpdateStatusService = new ContractUpdateStatusService();
			isSuccess = contractUpdateStatusService.updateContractStatus(board_id, 4);
			
			// Chat 채팅방 삭제
			ChatDeleteProService chatDeleteProService = new ChatDeleteProService();
			isSuccess = chatDeleteProService.deleteChat(board_id);
			
			if(isSuccess) {
				ContractGetInfoService contractGetInfoService = new ContractGetInfoService();
				ContractBean contractBean = contractGetInfoService.getContractInfo(board_id);
				
				// 취소 알림 보내기
				NoticeBean noticeBean = new NoticeBean();
				noticeBean.setBoard_id(board_id);
				noticeBean.setNotice_url("Project.bo?board_id="+board_id);
				if(!isWriter) { // 에디터가 계약을 취소
					noticeBean.setNotice_content(9);
					noticeBean.setNotice_from_id(contractBean.getContract_creator());
					noticeBean.setUser_id(contractBean.getContract_editor());
					
					NoticeProService noticeProService = new NoticeProService();
					noticeProService.insertNotice(noticeBean);
					
					noticeBean.setNotice_content(10);
					noticeBean.setNotice_from_id(contractBean.getContract_editor());
					noticeBean.setUser_id(contractBean.getContract_creator());
					
					noticeProService = new NoticeProService();
					noticeProService.insertNotice(noticeBean);
				} else { // 크리에이터가 계약을 취소
					noticeBean.setNotice_content(12);
					noticeBean.setNotice_from_id(contractBean.getContract_editor());
					noticeBean.setUser_id(contractBean.getContract_creator());
					
					NoticeProService noticeProService = new NoticeProService();
					noticeProService.insertNotice(noticeBean);
					
					noticeBean.setNotice_content(11);
					noticeBean.setNotice_from_id(contractBean.getContract_creator());
					noticeBean.setUser_id(contractBean.getContract_editor());
					
					noticeProService = new NoticeProService();
					noticeProService.insertNotice(noticeBean);
				}
			
				if(isSuccess) {
					forward = new ActionForward();
					forward.setPath("Project.bo?board_id="+board_id+"&page="+page);
					forward.setRedirect(true);
				} else {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println(Constant.arrMsg);
				}
			}
		}
		
		
		
		return forward;
	}

}
