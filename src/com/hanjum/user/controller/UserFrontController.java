package com.hanjum.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanjum.action.Action;
import com.hanjum.user.action.UserLoginProAction;
import com.hanjum.user.action.UserLogoutAction;
import com.hanjum.user.action.UserManageAction;
import com.hanjum.user.action.UserPhotoUploadAction;
import com.hanjum.user.action.UserPortfolioDeleteAction;
import com.hanjum.user.action.UserPortfolioInfoAction;
import com.hanjum.user.action.UserPortfolioInsertAction;
import com.hanjum.user.action.UserPortfolioListAction;
import com.hanjum.user.action.UserPortfolioUpdateAction;
import com.hanjum.user.action.UserReportManageAction;
import com.hanjum.user.action.UserReportProAction;
import com.hanjum.user.action.UserSearchManageAction;
import com.hanjum.user.action.UserSearchReportManageAction;
import com.hanjum.user.action.UserUpdateEditorProAction;
import com.hanjum.user.action.UserUpdateFormAction;
import com.hanjum.user.action.UserUpdateProAction;
import com.hanjum.user.service.UserProService;
import com.hanjum.user.action.EditAction;
import com.hanjum.user.action.UserCheckIdAction;
import com.hanjum.user.action.UserContractListAction;
import com.hanjum.user.action.UserDeleteProAction;
import com.hanjum.user.action.UserInfoAction;
import com.hanjum.user.action.UserInfoMyAction;
import com.hanjum.user.action.UserInsertEditorProAction;
import com.hanjum.user.action.UserInsertProAction;
import com.hanjum.user.action.UserLikeAction;
import com.hanjum.user.action.UserLikeListAction;
import com.hanjum.vo.ActionForward;

@WebServlet("*.uo")
public class UserFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 한글처리

		String command = request.getServletPath();
		System.out.println("요청 서블릿 주소 : " + command); // 서블릿주소 가져오기

		Action action = null;
		ActionForward forward = null; // 요청에 필요한 변수 선언
		
		if (command.equals("/Join.uo")) { // command 주소 검사
			// 바로 View로 포워딩 실행
			forward = new ActionForward(); // 포워드 객체 생성
			forward.setPath("/user/userInsertForm.jsp"); // 포워드경로 지정 , 디스패쳐 방식으로 해야되니 redirect값은 안줌
			
		} else if (command.equals("/JoinEditor.uo")) {
			
			forward = new ActionForward(); // 포워드 객체 생성
			forward.setPath("/user/userInsertEditorForm.jsp"); // 포워드경로 지정 , 디스패쳐 방식으로 해야되니 redirect값은 안줌
			
			
		} else if (command.equals("/JoinPro.uo")) {
			
			action = new UserInsertProAction();
			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/JoinEditorPro.uo")) {
			
			action = new UserInsertEditorProAction();
			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		else if (command.equals("/Login.uo")) {
			request.setAttribute("prefPage", request.getAttribute("prefPage"));
			forward = new ActionForward(); // 포워드 객체 생성
			forward.setPath("/user/userLoginForm.jsp"); // 포워드경로 지정 , 디스패쳐 방식으로 해야되니 redirect값은 안줌
			
		} else if (command.equals("/LoginPass.uo")) {
			request.setAttribute("prefPage", request.getAttribute("prefPage"));
			forward = new ActionForward(); // 포워드 객체 생성
			forward.setPath("/user/userLoginPassForm.jsp"); // 포워드경로 지정 , 디스패쳐 방식으로 해야되니 redirect값은 안줌
			
		} else if (command.equals("/LoginPro.uo")) {
			
			action = new UserLoginProAction();
			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/Home.uo")) {
			forward = new ActionForward(); // 포워드 객체 생성
			forward.setPath("home"); // 포워드경로 지정 , 디스패쳐 방식으로 해야되니 redirect값은 안줌
			forward.setRedirect(true);
			
		} else if (command.equals("/UserUpdateForm.uo")) {
			action = new UserUpdateFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/UserUpdatePro.uo")) {
			
			action = new UserUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/UserUpdateEditorPro.uo")) {
			
			action = new UserUpdateEditorProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else if (command.equals("/UserDeletePro.uo")) {
			
			action = new UserDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/UserManage.uo")) {
			action = new UserManageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/UserInfo.uo")) {
			action = new UserInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/UserMyInfo.uo")) {
			action = new UserInfoMyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/UserSearchManage.uo")) {
			action = new UserSearchManageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/UserLike.uo")) {
			action = new UserLikeAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/Logout.uo")) {
			action = new UserLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		
		
		else if (command.equals("/UserCheck.uo")) {
			System.out.println("에이젝스로왔따!!");
			action = new UserCheckIdAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}  else if (command.equals("/My.uo")) {
			forward = new ActionForward();
			forward.setPath("/user/userMain.jsp");
		} else if (command.equals("/ChangePass.uo")) {
			forward = new ActionForward(); 
			forward.setPath("/user/userChangePassForm.jsp"); 
		} 
		
		else if (command.equals("/CodeCheck.uo")) {   // 메일발송한거 코드체크
			String email = request.getParameter("email");
			String code = request.getParameter("code");
			UserProService codeCheckService = new UserProService();
			boolean success = codeCheckService.codeCheck(email,code);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(success) {
				System.out.println(1);
				out.println("1");
				out.close();
			}else {
				System.out.println(0);
				out.println("0");
				out.close();
			}
		} else if (command.equals("/changePass.uo")) { // 비밀번호 변경
			String user_id = request.getParameter("user_id");
			String user_pass = request.getParameter("user_pass");
			String user_changePass = request.getParameter("user_changePass");
			UserProService userChangePass = new UserProService();
			boolean success = false;
			System.out.println(user_pass);
			System.out.println("id : " +user_id);
			System.out.println("바꿀비번 : " +user_changePass);
			if(user_pass == null) { // 유저 패스가 없으면 비밀번호 찾기로 온거
				success = userChangePass.changePass(user_id, user_changePass);
			} else { // 유저 패스가 있으면 정상 비밀번호 변경
				success = userChangePass.changePass(user_id,user_pass,user_changePass);
			}
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(success) {
				HttpSession session = request.getSession();
				session.invalidate();
				out.println("<script>"); // 자바스크립트 시작 태그
				out.println("alert('비밀번호가 변경되었습니다. 다시 로그인해주세요.')"); // 다이얼로그 메세지 출력
				out.println("location.href='home'");
				out.println("</script>"); // 자바스크립트 끝 태그
			}else {
				out.println("<script>"); // 자바스크립트 시작 태그
				out.println("alert('변경실패! - 비밀번호가 맞지 않습니다. 다시 시도해주세요.')"); // 다이얼로그 메세지 출력
				out.println("history.back()");
				out.println("</script>"); 
			}
		} else if (command.equals("/PfWrite.uo")) {
			
			forward = new ActionForward(); // 포워드 객체 생성
			forward.setPath("/user/userPortfolio.jsp"); // 포워드경로 지정 , 디스패쳐 방식으로 해야되니 redirect값은 안줌
		
		} else if (command.equals("/UserPortfolioInsert.uo")) {
			action = new UserPortfolioInsertAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/UserPortfolioList.uo")) {
			action = new UserPortfolioListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/UserPortfolioUpdate.uo")) {
			action = new UserPortfolioUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/UserPortfolioDelete.uo")) {
			action = new UserPortfolioDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/UserPortfolioInfo.uo")) {
			action = new UserPortfolioInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 		
		// 리포트 폼
		else if (command.equals("/UserReportForm.uo")) { // command 주소 검사
			// 바로 View로 포워딩 실행
			forward = new ActionForward(); // 포워드 객체 생성
			forward.setPath("/user/userReportForm.jsp"); // 포워드경로 지정 , 디스패쳐 방식으로 해야되니 redirect값은 안줌
		}
		
		// 리포트 pro
		else if (command.equals("/UserReportPro.uo")) { // command 주소 검사
			action = new UserReportProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		// 구인상태 변경 - Action 없음 ajax
		else if (command.equals("/ChangeStatus.uo")) {
			String user_id = request.getParameter("user_id");
			int editor_status = Integer.parseInt(request.getParameter("editor_status"));
			boolean success = false;
			
			UserProService userProService = new UserProService();
			success = userProService.changeStatus(user_id,editor_status);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(success) {
				out.println(success);
				out.close();
			}else {
				out.println(success);
				out.close();
			}
		} else if (command.equals("/editEditor.uo")) {
			action = new EditAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/UserPhotoUpdate.uo")) {
			action = new UserPhotoUploadAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/UserConstract.uo")) {
			action = new UserContractListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/UserDeleteForm.uo")) { //회원탈퇴
			// 바로 View로 포워딩 실행
			forward = new ActionForward(); 
			forward.setPath("/user/userDeleteForm.jsp"); 
			
		}
		else if (command.equals("/UserDeletePro.uo")) { //회원탈퇴
			action = new UserDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/UserReportManage.uo")) {
			action = new UserReportManageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/UserSearchReportManage.uo")) {
			action = new UserSearchReportManageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/UserLikeList.uo")) {
			action = new UserLikeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// -------------------------------------------------------------------------------------------------------------------
		// 기본 작업 후 공통 작업 수행
		if (forward != null) { // 포워드가 값이 있다 = 포워드 객체가 생성 되었다 -> 실행
			if (forward.isRedirect()) { // Redirect방식 이라면 실행
				response.sendRedirect(forward.getPath()); // Redirect 방식으로 포워딩
			} else { // Redirect방식이 아닐때 = 디스패쳐 방식
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath()); // 디스패쳐 객체생성
				dispatcher.forward(request, response); // Dispatcher방식으로 포워딩
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response); // doProcess로

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response); // doProcess로
	}

}
