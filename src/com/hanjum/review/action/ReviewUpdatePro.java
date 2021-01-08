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



public class ReviewUpdatePro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		System.out.println("ReviewUpdateProAction");
		
		ActionForward forward = null;
//		ReviewBean reviewBean = new ReviewBean();
//		int review_id = reviewBean.getReview_id();
		int review_id = Integer.parseInt(request.getParameter("review_id"));
		
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean)session.getAttribute("userBean");
		ReviewService reviewService = new ReviewService();
		boolean isRightUser = reviewService.isArticleWriter(
								review_id, userBean.getUser_id());
		
		
		
		// 적합한 사용자 판별에 따른 처리
		if(!isRightUser) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정 권한이 없습니다!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			// 유저아이디 일치여부에 따라
			ReviewBean article = new ReviewBean();
			article.setReview_id(review_id);
			article.setReview_from_id(userBean.getUser_id());
			article.setReview_speciality(Integer.parseInt(request.getParameter("review_speciality")));
			article.setReview_satisfaction(Integer.parseInt(request.getParameter("review_satisfaction")));
			article.setReview_positivity(Integer.parseInt(request.getParameter("review_positivity")));
			article.setReview_communication(Integer.parseInt(request.getParameter("review_communication")));
			article.setReview_content(request.getParameter("review_content"));
			
			// 리뷰 수정
			boolean isUpdateSuccess = 
					reviewService.uodateArticle(article);
			
			reviewService = new ReviewService();
			ReviewBean reviewBean = reviewService.getArticle(review_id);
			
			reviewService = new ReviewService();
			int avg = reviewService.getReviewAvg(reviewBean.getUser_id());
			
			UserProService userProService = new UserProService();
			userProService.updateScore(reviewBean.getUser_id(), avg);
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(isUpdateSuccess);
			
		}
		
		
		return null;
	}


	
}
