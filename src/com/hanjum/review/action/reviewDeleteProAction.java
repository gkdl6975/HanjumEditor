package com.hanjum.review.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.review.service.ReviewService;
import com.hanjum.review.vo.ReviewBean;
import com.hanjum.user.service.UserProService;
import com.hanjum.vo.ActionForward;


public class reviewDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ActionForward forward = null;
		System.out.println("reviewDeleteAction");
		int review_id = Integer.parseInt(request.getParameter("review_id"));
		String review_form_id = request.getParameter("review_form_id");
		
		ReviewService reviewService = new ReviewService();
		boolean isArticleWriter = 
				reviewService.isArticleWriter(review_id, review_form_id); 
		
		System.out.println("isArticleWriter = " + isArticleWriter);
		
		if(!isArticleWriter) { 
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 권한이 없습니다!')");
			out.println("history.back()");
			out.println("</script>");
		} else { 
			boolean isDeleteSuccess = 
					ReviewService.removeArticle(review_id);
			
			
			if(!isDeleteSuccess) { 
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else { 
				reviewService = new ReviewService();
				ReviewBean reviewBean = reviewService.getArticle(review_id);
				
				reviewService = new ReviewService();
				int avg = reviewService.getReviewAvg(reviewBean.getUser_id());
				
				UserProService userProService = new UserProService();
				userProService.updateScore(reviewBean.getUser_id(), avg);
				
				System.out.println("true");
			}
		}
		
		
		return forward;
	}


	
	
}
