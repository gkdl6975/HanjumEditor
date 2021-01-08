package com.hanjum.user.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.user.service.UserProService;
import com.hanjum.vo.PageInfo;
import com.hanjum.user.vo.ReportBean;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;

public class UserSearchReportManageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String search = request.getParameter("search");
		String searchType = request.getParameter("searchType");
		
		// 검색항목을 컬럼명에 맞게 변환
		if(searchType.equals("신고 번호")) {
			searchType="report_id";
		} else if (searchType.equals("피해 유저")) {
			searchType="report_from_user";
		} else if (searchType.equals("가해 유저")) {
			searchType="user_id";
		} else if (searchType.equals("신고 타입")){
			searchType="report_type";
		} else {
			System.out.println("널임");
		}
		
		int page = 1; // 현재 페이지 번호를 저장할 변수
		int limit = 10; // 페이지 당 표시할 게시물 수를 결정하는 변수
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		UserProService userReportManageService = new UserProService();
		int listCount = userReportManageService.getReportListCount(search,searchType);
		System.out.println("전체 게시물 수 : " + listCount);
		
		ArrayList<ReportBean> reportList = new ArrayList<ReportBean>();
		reportList = userReportManageService.getReportList(page,limit,search,searchType);
		
		int maxPage = (int)((double)listCount / limit + 0.95);
		
		int startPage = ((int)((double)page / 10 + 0.9) - 1) * 10 + 1;
		
		int endPage = startPage + 9;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
		
		request.setAttribute("reportList", reportList);
		request.setAttribute("pageInfo", pageInfo);
		
		forward = new ActionForward();
		forward.setPath("/admin/admin_user_report.jsp");
		
		return forward;
	}

}
