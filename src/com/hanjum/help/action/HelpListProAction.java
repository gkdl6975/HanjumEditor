package com.hanjum.help.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.help.service.HelpListService;
import com.hanjum.vo.ActionForward;
import com.hanjum.help.vo.HelpBean;
import com.hanjum.vo.PageInfo;

public class HelpListProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		System.out.println("HelPListProAction!");
		ActionForward forward = null;
		
		int page = 1; 
		int limit = 10;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			
		}

		
		ArrayList<HelpBean> list = new ArrayList<HelpBean>();
		
		HelpListService helpListService = new HelpListService();
		list = helpListService.getHelpList(page,limit);
		int listCount = helpListService.getListCount();

		
		int maxPage = (int)((double)listCount / limit + 0.95);
		
		int startPage = ((int)((double)page / 10 + 0.9) - 1) * 10 + 1;
		
		int endPage = startPage + 10 - 1;		
		
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
		request.setAttribute("list", list);
		request.setAttribute("pageInfo", pageInfo);
		
		forward = new ActionForward();
		forward.setPath("/admin/admin_help.jsp");
		
		return forward;
	}

}
