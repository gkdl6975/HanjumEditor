package com.hanjum.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.board.service.EditorProService;
import com.hanjum.board.vo.EditorBean;
import com.hanjum.vo.ActionForward;
import com.hanjum.vo.Constant;

public class EditorUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("EditorUpdateProAction!");
		ActionForward forward = null;
		
		int nowPage = Integer.parseInt(request.getParameter("page"));

		int board_id = Integer.parseInt(request.getParameter("board_id"));
		String board_subject = request.getParameter("board_subject");
		String board_content = request.getParameter("board_content");
		String board_ed_content_detail = request.getParameter("board_ed_content_detail");
		
		EditorBean editorBean = new EditorBean();
		editorBean.setBoard_id(board_id);
		editorBean.setBoard_subject(board_subject);
		editorBean.setBoard_content(board_content);
		editorBean.setBoard_ed_content_detail(board_ed_content_detail);
		
		EditorProService editorProService = new EditorProService();
		boolean isUpdateSuccess = editorProService.modifyEditor(editorBean);
		
		if(!isUpdateSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(Constant.arrMsg);
			
		} else {
			forward = new ActionForward();
			forward.setPath("Editor.bo?page="+nowPage+"&board_id="+board_id);
			forward.setRedirect(true);
		}
		return forward;
	}
	
}
