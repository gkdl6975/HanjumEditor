package com.hanjum.review.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanjum.action.Action;
import com.hanjum.contract.service.ContractCheckSuccessService;
import com.hanjum.review.service.ReviewService;
import com.hanjum.review.vo.ReviewBean;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;
import com.hanjum.vo.Constant;
import com.hanjum.vo.PageInfo;


public class ReviewListAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReviewListAction");
		
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean)session.getAttribute("userBean");
		
		int page = 1;
		int limit = Constant.REVIEW_PAGE_SIZE;
		System.out.println(request.getParameter("page"));
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		String user_id = request.getParameter("user_id");
		
		ReviewService reviewService = new ReviewService();
		int listCount = reviewService.getListCount(user_id);
		System.out.println("전체 리뷰 수:"+listCount);
		
		ArrayList<ReviewBean> articleList = new ArrayList<ReviewBean>();
		articleList = reviewService.getArticleList(page, limit, user_id);
		
		int maxPage = (int)((double)listCount / limit + 0.95);
		int startPage = ((int)((double)page / 10 + 0.9) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}

		
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
		request.setAttribute("writer_id", user_id);
		request.setAttribute("articleList", articleList);
		request.setAttribute("pageInfo", pageInfo);
		
		ContractCheckSuccessService checkSuccessService = new ContractCheckSuccessService();
		int SuccessCount = checkSuccessService.checkContractStatus(user_id, userBean.getUser_id());
		request.setAttribute("SuccessCount", SuccessCount); // 0이면 완료안한거 1이상 완료한거
		
		reviewService = new ReviewService();
		int reviewWriteCount = reviewService.checkWriteReview(user_id, userBean.getUser_id());
		request.setAttribute("reviewWriteCount", reviewWriteCount); // 1이상이면 이미 리뷰 쓴거 0이면 리뷰 안쓴거
		
		
		forward = new ActionForward();
		forward.setPath("/review/reviewList.jsp");
		return forward;	
	}
	
}
