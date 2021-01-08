package com.hanjum.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.board.service.BoardProService;
import com.hanjum.board.service.EditorProService;
import com.hanjum.board.vo.EditorBean;
import com.hanjum.vo.ActionForward;
import com.hanjum.vo.Constant;
import com.hanjum.vo.PageInfo;

public class EditorListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("EditorListAction!");
		ActionForward forward = null;
		
		int page = 1;
		int limit = Constant.BOARD_PAGE_SIZE;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int board_type = 1;
		EditorProService editorProService = new EditorProService();
		int listCount = editorProService.getEditorBoardCount();
		
		
		editorProService = new EditorProService();
		ArrayList<EditorBean> editorList = editorProService.getListEditor(page);
		
		int maxPage = (int)((double)listCount / limit + 0.95);
		
		int startPage = ((int)(page / 10.0 + 0.9)-1)*10+1;
		
		int endPage = startPage + 10 - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("editorList", editorList);
		
		forward = new ActionForward();
		forward.setPath("/editor/editorList.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
