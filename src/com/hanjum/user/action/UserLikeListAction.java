package com.hanjum.user.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.user.service.UserProService;
import com.hanjum.vo.PageInfo;
import com.hanjum.user.vo.LikeBean;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;

// ㅠ 팔로우한사람 누군지 보는 리스트인데 저장된 데이터가 아이디밖에없어서 만들고나니 쓸데가없음... 우선 보류
public class UserLikeListAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String user_id = request.getParameter("user_id");
		UserProService userLikeListService = new UserProService();
		int listCount = userLikeListService.getLikeListCount(user_id);
		System.out.println("전체 팔로워 수 : " + listCount);
		
		ArrayList<LikeBean> likeList = new ArrayList<LikeBean>();
		likeList = userLikeListService.getLikeList(user_id);
		
		request.setAttribute("likeList", likeList);
		
		forward = new ActionForward();
		forward.setPath("/user/userLikeList.jsp");
		return forward;
	}

}
