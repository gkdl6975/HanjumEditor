package com.hanjum.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.review.service.ReviewService;
import com.hanjum.review.vo.ReviewBean;
import com.hanjum.vo.ActionForward;


public class ReviewUpdateForm implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			ActionForward forward = null;
			
			int review_id = Integer.parseInt(request.getParameter("review_id"));
			
			ReviewService reviewService = new ReviewService();
			ReviewBean article = reviewService.getArticle(review_id);
		
			request.setAttribute("article", article);
			
			forward = new ActionForward();
			forward.setPath("/review/reviewupdate.jsp");
			
			return forward;
	}

}
