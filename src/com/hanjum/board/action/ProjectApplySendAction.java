package com.hanjum.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.board.service.EditorProService;
import com.hanjum.notice.service.NoticeProService;
import com.hanjum.notice.vo.NoticeBean;
import com.hanjum.vo.ActionForward;

public class ProjectApplySendAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		String writer_id = request.getParameter("writer_id");
		String user_id = request.getParameter("user_id");
		String page = request.getParameter("page");

		NoticeBean noticeBean = new NoticeBean();
		noticeBean.setBoard_id(board_id);
		noticeBean.setNotice_content(16);
		noticeBean.setNotice_from_id(writer_id);
		noticeBean.setNotice_url("Project.bo?board_id="+board_id);
		noticeBean.setUser_id(user_id);
		
		NoticeProService noticeProService = new NoticeProService();
		noticeProService.insertNotice(noticeBean);
		
		EditorProService editorProService = new EditorProService();
		com.hanjum.board.vo.EditorBean editorBoard = editorProService.getEditor(user_id);
		
		noticeBean.setNotice_content(17);
		noticeBean.setNotice_from_id(user_id);
		noticeBean.setNotice_url("Editor.bo?board_id="+editorBoard.getBoard_id());
		noticeBean.setUser_id(writer_id);
		
		noticeProService = new NoticeProService();
		noticeProService.insertNotice(noticeBean);
		
		forward = new ActionForward();
		forward.setPath("Project.bo?board_id="+board_id+"&page="+page);
		forward.setRedirect(true);
		return forward;
	}

	
	
}
