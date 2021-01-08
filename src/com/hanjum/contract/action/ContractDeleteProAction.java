package com.hanjum.contract.action;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.contract.service.ContractDeleteProService;
import com.hanjum.contract.service.ContractUpdateProService;
import com.hanjum.contract.vo.ContractBean;
import com.hanjum.vo.ActionForward;





public class ContractDeleteProAction implements Action{
	


	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("ContractDeleteProAction");
		
		ActionForward forward = null;
		

		int contract_id = Integer.parseInt(request.getParameter("contract_id"));
		

		ContractDeleteProService contractDeleteProService = new ContractDeleteProService();

			boolean isModifySuccess = 
					contractDeleteProService.removeContract(contract_id);

			if(!isModifySuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('계약 삭제 실패!')");
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
