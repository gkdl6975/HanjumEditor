package com.hanjum.help.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.help.service.HelpListService;
import com.hanjum.help.vo.HelpBean;
import com.hanjum.vo.PageInfo;
import com.hanjum.vo.ActionForward;

public class CenterSearchListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String search = request.getParameter("search");
		
		int page = 1; // 현재 페이지 번호를 저장할 변수
		int limit = 10; // 페이지 당 표시할 게시물 수를 결정하는 변수
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		ArrayList<HelpBean> searchList = new ArrayList<HelpBean>();
		HelpListService helpListService = new HelpListService();
		int listCount = helpListService.getSearchListCount(search);
		
		searchList = helpListService.getSearchHelpList(page,limit,search);
		
		int maxPage = (int)((double)listCount / limit + 0.95);
		
		int startPage = ((int)((double)page / 10 + 0.9) - 1) * 10 + 1;
		
		int endPage = startPage + 9;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
		
		request.setAttribute("list", searchList);
		request.setAttribute("pageInfo", pageInfo);
		System.out.println(listCount);
		System.out.println(startPage);
		System.out.println(endPage);
		forward = new ActionForward();
		forward.setPath("/center/centerHelp.jsp");
		
		return forward;
	}

}
