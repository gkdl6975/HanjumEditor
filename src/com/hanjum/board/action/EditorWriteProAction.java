package com.hanjum.board.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.board.service.BoardProService;
import com.hanjum.board.service.EditorProService;
import com.hanjum.board.vo.EditorBean;
import com.hanjum.user.service.UserProService;
import com.hanjum.vo.ActionForward;
import com.hanjum.vo.Constant;

public class EditorWriteProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("EditorWriteProAction!");
		
		ActionForward forward = null;
		boolean isSuccess = false;
		
		String user_id = request.getParameter("user_id");
		
		UserProService userProService = new UserProService();
		com.hanjum.user.vo.EditorBean EditorInfo = userProService.getEditorInfo(user_id);
		
		EditorBean editorBean = new EditorBean();
		editorBean.setBoard_subject(request.getParameter("board_subject"));
		editorBean.setUser_id(user_id);
		editorBean.setBoard_date(new Timestamp(System.currentTimeMillis()));
		editorBean.setBoard_type(2);
		editorBean.setBoard_content(EditorInfo.getEditor_des());
		BoardProService boardProService = new BoardProService();
		editorBean.setBoard_id(boardProService.getBoardLastId());
		
		editorBean.setBoard_ed_address(EditorInfo.getEditor_address());
//		editorBean.setBoard_ed_category();
		editorBean.setBoard_ed_content_detail(EditorInfo.getEditor_profile());
		editorBean.setBoard_ed_fort(EditorInfo.getEditor_fort());
		editorBean.setBoard_ed_inventory(EditorInfo.getEditor_inventory());
//		editorBean.setBoard_ed_link();
		editorBean.setBoard_ed_max_price(EditorInfo.getEditor_ed_max_price());
		editorBean.setBoard_ed_meeting(EditorInfo.getEditor_meeting());
		editorBean.setBoard_ed_min_price(EditorInfo.getEditor_ed_min_price());
		editorBean.setBoard_ed_program(EditorInfo.getEditor_program());
		editorBean.setBoard_ed_sample(EditorInfo.getEditor_sample());
		editorBean.setBoard_ed_solution(EditorInfo.getEditor_solution());
//		editorBean.setBoard_ed_subject();
		editorBean.setBoard_ed_upload(EditorInfo.getEditor_upload());
		editorBean.setBoard_ed_work(EditorInfo.getEditor_work());
		
		EditorProService editorProService = new EditorProService();
		
		isSuccess = editorProService.writeEditor(editorBean);
		
		if(isSuccess) {
			forward = new ActionForward();
			forward.setPath("My.uo?fr=info");
			forward.setRedirect(true);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(Constant.arrMsg);
		}
		
		return forward;
	}
	
}
