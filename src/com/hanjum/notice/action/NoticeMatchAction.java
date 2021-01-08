package com.hanjum.notice.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.board.service.ProjectProService;
import com.hanjum.chat.service.ChatDeleteProService;
import com.hanjum.chat.service.ChatWriteProService;
import com.hanjum.chat.vo.ChatBean;
import com.hanjum.contract.service.ContractDeleteService;
import com.hanjum.contract.service.ContractUpdateStatusService;
import com.hanjum.notice.service.NoticeProService;
import com.hanjum.notice.vo.NoticeBean;
import com.hanjum.user.service.UserProService;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;
import com.hanjum.vo.Constant;

public class NoticeMatchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("NoticeMatchAction.java");

		ActionForward forward = null;

		// 해당 notice_id 를 읽음으로 바꿈
		int notice_id = Integer.parseInt(request.getParameter("notice_id"));

		NoticeProService service = new NoticeProService();
		NoticeBean noticeInfo = service.getNotice(notice_id);
		// user_id = creator(수락한사람), notice_from_id = editor(수락당한?사람)
		// notice_content == 3 : user_id 님이  notice_from_id 님의 프로젝트 제안을 수락했습니다
		NoticeBean noticeBean = new NoticeBean();
		noticeBean.setBoard_id(noticeInfo.getBoard_id());
		noticeBean.setNotice_content(4);
		noticeBean.setNotice_from_id(noticeInfo.getUser_id());
		noticeBean.setNotice_url(noticeInfo.getNotice_url());
		noticeBean.setUser_id(noticeInfo.getNotice_from_id());

		service = new NoticeProService();
		service.insertNotice(noticeBean);

		service = new NoticeProService();
		service.deleteNotice(notice_id);// 해당 notice_삭제; 같은 변수명쓰면 Connection is null error ! 

		// ======================================================================
		// user_id = editor(신청한사람), notice_from_id = creator(프로젝트하는사람)
		// user_from_id 님께서 user_id님의 제안을 받아들임. 프로젝트를 진행합니다?
		noticeBean.setNotice_content(3);
		noticeBean.setNotice_from_id(noticeInfo.getNotice_from_id());
		noticeBean.setUser_id(noticeInfo.getUser_id());

		service = new NoticeProService();
		service.insertNotice(noticeBean);

		// Waiting 당첨자 외 다른 지원자들 알림보내고 삭제
		ProjectProService projectProService = new ProjectProService();
		ArrayList<String> WaitingList = projectProService.getWaitingDeclineList(noticeInfo.getBoard_id(), noticeInfo.getNotice_from_id());
		boolean isSuccess = false;
		int count = 0;
		
		if(WaitingList != null) {
			for (int i = 0; i < WaitingList.size(); i++) {
				noticeBean.setNotice_content(6);
				noticeBean.setNotice_from_id(noticeInfo.getUser_id());
				noticeBean.setUser_id(WaitingList.get(i));
				service = new NoticeProService();
				service.insertNotice(noticeBean); // 각 지원자들 취소알림 생성
				
				count++;
			}
			if (count == WaitingList.size()) {
				projectProService = new ProjectProService();
				isSuccess = projectProService.dropWaiting(noticeInfo.getBoard_id()); // 대기열 다 삭제
			}
		} else {
			isSuccess = true;
		}
		if (isSuccess) {
			UserProService userProService = new UserProService();
			UserBean editorInfo = userProService.getUserInfo(noticeInfo.getNotice_from_id());

			// 채팅방 개설
			ChatBean chatBean = new ChatBean();
			chatBean.setChat_to_id(noticeInfo.getNotice_from_id());
			chatBean.setChat_from_id(noticeInfo.getUser_id());
			chatBean.setChat_content("안녕하세요 이번 프로젝트를 맡게된 " + editorInfo.getUser_name() + "이라고 합니다.");
			chatBean.setBoard_id(noticeInfo.getBoard_id());

			ChatWriteProService chatWriteProService = new ChatWriteProService();
			isSuccess = chatWriteProService.registChat(chatBean);

			if (isSuccess) {
				// 신청자 외 계약대기중 삭제
				ContractDeleteService contractDeleteService = new ContractDeleteService();
				contractDeleteService.deleteContractOther(noticeInfo.getBoard_id(), noticeInfo.getNotice_from_id());

				// 계약 진행중으로 상태 변경
				ContractUpdateStatusService contractUpdateStatusService = new ContractUpdateStatusService();
				System.out.println("board_id = " + noticeInfo.getBoard_id());
				isSuccess = contractUpdateStatusService.updateContractStatus(noticeInfo.getBoard_id(), 2);
				if (isSuccess) {
					// 프로젝트 진행중으로 상태 변경
					projectProService = new ProjectProService();
					isSuccess = projectProService.updateStatus(noticeInfo.getBoard_id(), 1);
					// 수락시 채팅창으로 이동
					if (isSuccess) {
						forward = new ActionForward();
						forward.setPath("My.uo?fr=chat");
						forward.setRedirect(true);
					} else {
						response.setContentType("text/html; charset=UTF-8");
						PrintWriter out = response.getWriter();
						out.println(Constant.arrMsg);
					}
				}

			}
		}

		return forward;
	}

}
