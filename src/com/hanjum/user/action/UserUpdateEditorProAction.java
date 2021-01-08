package com.hanjum.user.action;

import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.board.service.EditorProService;
import com.hanjum.user.service.UserProService;
import com.hanjum.user.vo.EditorBean;
import com.hanjum.vo.ActionForward;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UserUpdateEditorProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UserUpdateEditorProAction!");
		ActionForward forward = null;
		String user_id = request.getParameter("user_id");
		String content = request.getParameter("content");;
		String target = request.getParameter("target");
		boolean isUpdateSuccess = false;
		
		if(target.equals("editor_price")) {
			target = "editor_ed_min_price";
			content = request.getParameter("editor_ed_min_price");
			UserProService userProService = new UserProService();
			isUpdateSuccess = userProService.updateEditor(user_id, content, target);
			
			if(isUpdateSuccess) {
				target = "editor_ed_max_price";
				content = request.getParameter("editor_ed_max_price");
				userProService = new UserProService();
				isUpdateSuccess = userProService.updateEditor(user_id, content, target);
			}
			
		} else {
//			if(target.equals("editor_program")) {
//				String[] programArr = request.getParameterValues("content");
//				content = Arrays.toString(programArr).replaceAll("[\\[\\] ]", "");
//			} else if(target.equals("editor_solution")) {
//				String[] solutionArr = request.getParameterValues("content");
//				content = Arrays.toString(solutionArr).replaceAll("[\\[\\] ]", "");
//			} else if(target.equals("editor_inventory")) {
//				String[] inventoryArr = request.getParameterValues("content");
//				content = Arrays.toString(inventoryArr).replaceAll("[\\[\\] ]", "");
//			} else {
//				content = request.getParameter("content");
//			}
			
			
			UserProService userProService = new UserProService();
			isUpdateSuccess = userProService.updateEditor(user_id, content, target);
		}
		
		if(isUpdateSuccess) {
			UserProService userProService = new UserProService();
			EditorBean editorBean = userProService.getEditorInfo(user_id);
			if(editorBean != null) {
				EditorProService editorProService = new EditorProService();
				editorProService.updateEditor(editorBean);
			}
		}
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter(); 
		out.print(isUpdateSuccess);
		
		return forward;
	}

}
