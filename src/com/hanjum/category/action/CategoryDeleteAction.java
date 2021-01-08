package com.hanjum.category.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.category.service.CategoryProService;
import com.hanjum.category.vo.CategoryBean;
import com.hanjum.vo.ActionForward;

public class CategoryDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CategoryDeleteAction");
		ActionForward forward = null;
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		System.out.println(category_id);
		
		CategoryBean cb = new CategoryBean();
		cb.setCategory_id(category_id);
		
		CategoryProService service = new CategoryProService();
		boolean isDeleteSuccess=service.deleteCategory(cb);
		
		if(!isDeleteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글삭제실패')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			forward = new ActionForward();
			forward.setPath("CategoryList.cg");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
