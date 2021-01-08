package com.hanjum.review.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanjum.action.Action;
import com.hanjum.review.service.ReviewService;
import com.hanjum.review.vo.ReviewBean;
import com.hanjum.user.service.UserProService;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;


public class ReviewWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("review pro action");
		
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean)session.getAttribute("userBean");
		
		ReviewBean reviewBean = new ReviewBean();
		reviewBean.setReview_content(request.getParameter("review_content"));
		reviewBean.setReview_speciality(Integer.parseInt(request.getParameter("review_speciality")));
		reviewBean.setReview_satisfaction(Integer.parseInt(request.getParameter("review_satisfaction")));
		reviewBean.setReview_positivity(Integer.parseInt(request.getParameter("review_positivity")));
		reviewBean.setReview_communication(Integer.parseInt(request.getParameter("review_communication")));
		reviewBean.setReview_from_id(userBean.getUser_id());
//		reviewBean.setReview_date(new Timestamp(System.currentTimeMillis()));
		reviewBean.setUser_id(request.getParameter("writer_id"));
		
		
		ReviewService reviewService = new ReviewService();
		boolean isWriteSuccess = reviewService.rigisterWriter(reviewBean);
		
		reviewService = new ReviewService();
		int avg = reviewService.getReviewAvg(request.getParameter("writer_id"));
		
		UserProService userProService = new UserProService();
		userProService.updateScore(request.getParameter("writer_id"), avg);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(isWriteSuccess); 
		
		return null;
		
	}

}
