package com.hanjum.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.notice.action.CancelApplyNoticeAction;
import com.hanjum.notice.action.CancelContractByCreatorAction;
import com.hanjum.notice.action.CancelContractByEditorAction;
import com.hanjum.notice.action.DeclineNoticeAction;
import com.hanjum.notice.action.DoneContractNoticeAction;
import com.hanjum.notice.action.NoticeDeleteAction;
import com.hanjum.notice.action.NoticeListAction;
import com.hanjum.notice.action.NoticeMatchAction;
import com.hanjum.notice.action.NoticeNewAlarmAction;
import com.hanjum.notice.action.NoticeOldAlarmAction;
import com.hanjum.notice.action.NoticeUpdateStatusAction;
import com.hanjum.notice.action.ReviewNoticeAction;
import com.hanjum.notice.action.applyNoticeAction;
import com.hanjum.vo.ActionForward;



@WebServlet("*.nt")
public class NoticeFrontController extends HttpServlet {
	 // get/post 방식에 맞게 처리하는 메서드. doGet(), doPost()로부터 호출됨
       
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8"); // POST 방식 요청
		
		// 서블릿 주소 가져옴
		String command = request.getServletPath();
		System.out.println("Servlet Path : " + command);		
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/getNewNotice.nt")) { 
			// 새로운 알람 불러오기
			System.out.println("getNewNotice.nt");
			
			action = new NoticeNewAlarmAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/getNoticeList.nt")) {
			// 알람 전체
			System.out.println("getNoticeList.nt");
			action = new NoticeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}else if(command.equals("/getOldNotice.nt")) {
			// 읽은 알람
			System.out.println("getOldNotice.nt");
			
			action = new NoticeOldAlarmAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/updateStatus.nt")) {
			// 안읽은알람 -> 읽은알람상태로 바꿔줌
			System.out.println("updateStatus.nt");
			action = new NoticeUpdateStatusAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/matchNotice.nt")) {
			// match한다고(수락버튼 아마도?) 했을 경우
			System.out.println("matchNotice.nt");
			action = new  NoticeMatchAction();
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/noticeDelete.nt")) {
			// 알람 삭제버튼 누르면 삭제
			System.out.println("noticeDelete.nt");
			
			action = new NoticeDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/applyNotice.nt")) {
			// 프로젝트 "신청"버튼
			System.out.println("applyNotice");
			
			action = new applyNoticeAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}else if(command.equals("/declineNotice.nt")) {
			// 거절버튼
			System.out.println("declineNotice");
			
			action = new DeclineNoticeAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}else if(command.equals("/deleteNotice.nt")) {
			// 해당알람 삭제
			System.out.println("deletenotice");
			
			action = new NoticeDeleteAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}else if(command.equals("/cancelApplyNotice.nt")) {
			// 에디터가 지원을 취소
			action = new CancelApplyNoticeAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/cancelContractByEditor.nt")) {
			// 에디터가 계약을 취소
			action = new CancelContractByEditorAction();
					try {
						forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
		}else if (command.equals("/cancelContractByCreator.nt")) {
			// 크리에이터가 계약을 취소
			action = new CancelContractByCreatorAction();
					try {
						forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
		}else if (command.equals("/doneContractNotice.nt")) {
			// 크리에이터가 계약을 완료
			action = new DoneContractNoticeAction();
					try {
						forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
		}else if (command.equals("/reviewNotice.nt")) {
			// 크리에이터가 리뷰를 작성함
			action = new ReviewNoticeAction();
					try {
						forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
		}

		
		
		
		
		
		// forward 존재여부판별해서 : dispatcher or redirect
		if(forward != null) {
			if(forward.isRedirect()) {
				// redirect - true
				response.sendRedirect(forward.getPath());			
			}else {
				// dispatcher - false
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
				
			}
			
		} // forward 객체 있는지?
		
		
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
