package com.hanjum.category.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.category.service.CategoryProService;
import com.hanjum.category.vo.CategoryBean;
import com.hanjum.vo.ActionForward;

public class CategoryUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CategoryUpdateAction");
		ActionForward forward=null;
		int category_id= Integer.parseInt(request.getParameter("category_id"));
		String category_content=request.getParameter("category_content");
		System.out.println(category_id+category_content);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(!(category_content.equals(" "))) {
			CategoryProService service = new CategoryProService();
			boolean isContent = service.getContent(category_content);
			if(!isContent) {
			CategoryBean cb = new CategoryBean();
			cb.setCategory_id(category_id);
			cb.setCategory_content(category_content);
			CategoryProService categoryProService = new CategoryProService();
			boolean isUpdateSuccess=categoryProService.updateCategory(cb);
			
			System.out.println(isUpdateSuccess);
			if(!isUpdateSuccess) {
				out.println("<script> alert('수정실패'); history.back(); </script>");
				
			}else {
				out.println("<script> alert('수정성공'); location.href='CategoryList.cg'; </script>"); 
			}
			}else {
				out.println("<script> alert('이미 존재하는 항목입니다'); history.back(); </script>");
			}
		}else {
			out.println("<script> alert('수정할 내용을 입력해주세요'); history.back(); </script>");
		}
		
		return null;
	}

}
