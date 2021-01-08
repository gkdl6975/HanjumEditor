package com.hanjum.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.user.service.UserProService;
import com.hanjum.user.vo.EditorBean;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;

public class UserUpdateFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UserUpdateFormAction!");
		ActionForward forward = null;
		String user_id = request.getParameter("user_id");
		
		UserProService userUpdateFormService = new UserProService();
		UserBean userBean = userUpdateFormService.getUserInfo(user_id);
		
		request.setAttribute("userBean", userBean);
		if (userBean.getUser_type()==2) {
		EditorBean editorBean = userUpdateFormService.getEditorInfo(user_id);	
		request.setAttribute("EditorBean", editorBean);
		forward = new ActionForward();
		forward.setPath("/user/userUpdateEditorForm.jsp");
		} else {
			forward = new ActionForward();
			forward.setPath("/user/userUpdateForm.jsp");
		}
		
		return forward;
	}

}
