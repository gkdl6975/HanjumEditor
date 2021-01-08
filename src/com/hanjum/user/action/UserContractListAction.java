package com.hanjum.user.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanjum.action.Action;
import com.hanjum.contract.vo.ContractBean;
import com.hanjum.user.service.UserProService;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;

public class UserContractListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean)session.getAttribute("userBean");
		
		UserProService userProService = new UserProService();
		
		ArrayList<ContractBean> contractMatchList = userProService.getUserContractList(userBean.getUser_id(),2);
		request.setAttribute("contractMatchList", contractMatchList);
		
		ArrayList<ContractBean> contractSuccessList = userProService.getUserContractList(userBean.getUser_id(),3);
		request.setAttribute("contractSuccessList", contractSuccessList);

		forward = new ActionForward();
		forward.setPath("/user/userContract.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
