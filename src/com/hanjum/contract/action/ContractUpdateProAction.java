package com.hanjum.contract.action;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.contract.service.ContractUpdateProService;
import com.hanjum.contract.vo.ContractBean;
import com.hanjum.vo.ActionForward;





public class ContractUpdateProAction implements Action{
	


	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 특정 게시물 수정을 요청하는 Action 클래스
		System.out.println("ContractUpdateProAction");
		
		ActionForward forward = null;
		
		// 게시물 수정에 필요한 글번호(board_num) 가져오기
		int contract_id = Integer.parseInt(request.getParameter("contract_id"));
		
		// BoardModifyProService 클래스 인스턴스 생성 후
		// isArticleWriter() 메서드를 호출하여 적합한 사용자인지 판별
		// => 적합한 사용자 : 패스워드가 DB 에 저장된 패스워드와 일치하는 경우
		// => 파라미터 : 글번호(board_num), 패스워드(board_pass)
		//    리턴타입 : boolean(isRightUser)
		ContractUpdateProService contractUpdateProService = new ContractUpdateProService();

		
		
		// 임시확인용 isRightUser 출력
//		System.out.println(isRightUser);
		ContractBean cBean = new ContractBean();
		cBean.setContract_id(contract_id);
		cBean.setContract_price(Integer.parseInt(request.getParameter("contract_price")));
		cBean.setContract_status(Integer.parseInt(request.getParameter("contract_status")));

//		System.out.println("계약 번호" + contract_id);
//		System.out.println("수정 가격 : " + Integer.parseInt(request.getParameter("contract_price")) );
//		System.out.println("수정 가격 : " + request.getParameter("contract_price") );

//		System.out.println("계약 상태 : " + Integer.parseInt(request.getParameter("contract_status")) );
		

//			// 패스워드가 일치할 경우(= 적합한 사용자일 경우)
//			// BoardBean 객체 생성하여 수정폼으로부터 전달받은 항목을 저장
//			// => 글번호, 작성자, 제목, 내용
//			
//			// BoardModifyProService 클래스의 modifyArticle() 메서드를 호출하여
//			// 글 수정 작업 요청
//			// => 파라미터 : BoardBean, 리턴타입 : boolean(isModifySuccess)
			boolean isModifySuccess = 
					contractUpdateProService.modifyArticle(cBean);
//			
//			// 수정 결과에 따른 처리
//			// => 수정 실패(isModifySuccess 가 false)일 경우 
//			//    자바스크립트를 사용하여 "글 수정 실패!" 출력 후 
//			//    이전페이지로 이동
//			// => 아니면 ActionForward 객체 생성 후 BoardDetail.bo 로 포워딩
//			//    새 요청이 발생하므로 Redirect 방식으로 이동
//			//    파라미터로 글번호(board_num)와 페이지번호(page) 전달 필요
			if(!isModifySuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('계약 수정 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else {

				forward = new ActionForward();
				forward.setPath("ContractUpdate.ct");
				forward.setRedirect(true);
			}
			


		return forward;
	}

}
