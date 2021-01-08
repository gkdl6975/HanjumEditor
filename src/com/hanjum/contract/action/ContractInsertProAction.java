package com.hanjum.contract.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.contract.service.ContractInsertProService;
import com.hanjum.contract.vo.ContractBean;
import com.hanjum.vo.ActionForward;



public class ContractInsertProAction implements Action{
	
	



		@Override
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			System.out.println("ContractInsertProAction!");
			
			ActionForward forward = null;
			
			ContractBean cBean = new ContractBean();
			
//			------------------------실제로 불릴때 여기 다 고쳐야함--------------------------------------
			
//			cBean.setBoard_id(board_id);  //없어도 Contract 테이블 속성으로 Autoincrement 설정해서 자동으로 부여됨
			cBean.setBoard_subject("게시판 명1");
			cBean.setContract_creator("크리에이터 테스터");
			cBean.setContract_editor("에디터 테스터");
			cBean.setContract_price(500);
			cBean.setContract_status(1);
			cBean.setBoard_id(1);  // 외래키  받아와야함
			cBean.setBoard_ed_address("부산");
//			------------------------실제로 불릴때 여기 다 고쳐야함--------------------------------------			
			
			ContractInsertProService ciProService = new ContractInsertProService();
		
			boolean isInsertSuccess = ciProService.registArticle(cBean);
			
			if(!isInsertSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('contract 테이블 등록 실패')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				System.out.println("여기로 왓음");
				forward = new ActionForward();
				forward.setPath("ContractList.ct"); // 프로젝트 루트로 경로 설정 // 슬래시 붙이지 말것!
				forward.setRedirect(true);
			}



			return forward;
		}

		
		
		

}
