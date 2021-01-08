package com.hanjum.category.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.category.service.CategoryProService;
import com.hanjum.category.vo.CategoryBean;
import com.hanjum.vo.ActionForward;

public class CategoryListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CategoryListAction");
		ActionForward forward = null;
		int listCount=0;
		CategoryProService categoryProService = new CategoryProService();
		listCount=categoryProService.getListCount();
		System.out.println(listCount);
		
		ArrayList<CategoryBean> categoryList = new ArrayList<CategoryBean>();
		categoryProService = new CategoryProService();
		categoryList=categoryProService.getCategoryList();
		
		
		request.setAttribute("categoryList", categoryList);
		
		
		forward = new ActionForward();
		forward.setPath("/admin/admin_category.jsp");
		System.out.println("리다이렉트 : " + forward.isRedirect());
		return forward;
	}

}
