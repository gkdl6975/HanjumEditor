package com.hanjum.category.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.category.service.CategoryProService;
import com.hanjum.category.vo.CategoryBean;
import com.hanjum.vo.ActionForward;

public class CategoryWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String category_content = request.getParameter("category_content");
		if(!(category_content.equals(""))) {
			CategoryBean cb = new CategoryBean();
			cb.setCategory_content(category_content);
			
			CategoryProService service = new CategoryProService();
			boolean isContent = service.getContent(category_content);
			System.out.println("글이 존재하는가?" + isContent);
			if(!isContent) {
				service = new CategoryProService();
				boolean isWriteSuccess=service.insertCategory(cb);
				if(!isWriteSuccess) {
					out.println("<script>");
					out.println("alert('글등록실패')");
					out.println("history.back()");
					out.println("</script>");
				}else {
					out.println("<script>");
					out.println("alert('새 카테고리가 등록됐습니다')");
					out.println("location.href='CategoryList.cg'");
					out.println("</script>");
				}
			}else {
				out.println("<script>");
				out.println("alert('이미 존재하는 항목입니다')");
				out.println("history.back()");
				out.println("</script>");
			}
		}else {
			out.println("<script>");
			out.println("alert('추가할 항목을 입력해주세요')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return null;
	}

}
