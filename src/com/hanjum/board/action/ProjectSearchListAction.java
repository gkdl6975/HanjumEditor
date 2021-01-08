package com.hanjum.board.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.board.service.ProjectProService;
import com.hanjum.board.vo.ProjectBean;
import com.hanjum.vo.ActionForward;
import com.hanjum.vo.Constant;
import com.hanjum.vo.PageInfo;

public class ProjectSearchListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProjectSearchListAction!");
		ActionForward forward = null;
		
		HashMap<String, String> search = new HashMap<String, String>();
		
		int page = 1;
		int limit = Constant.BOARD_PAGE_SIZE;
		
		String genre = Arrays.toString(request.getParameterValues("board_creator_genre")).replaceAll("[\\[\\] ]", "").replaceAll(",", "|");
		String price_n = request.getParameter("board_creator_cre_min_price");
		String price_x = request.getParameter("board_creator_cre_max_price");
		String keyword = request.getParameter("keyword");
		String recording = Arrays.toString(request.getParameterValues("board_creator_recording")).replaceAll("[\\[\\] ]", "");;
		String camnum = Arrays.toString(request.getParameterValues("board_creator_cam_num")).replaceAll("[\\[\\] ]", "");;
		String clipnum = Arrays.toString(request.getParameterValues("board_creator_ori_clip_num")).replaceAll("[\\[\\] ]", "");;
		String oriLength = Arrays.toString(request.getParameterValues("board_creator_ori_length")).replaceAll("[\\[\\] ]", "");;
		String editLength = Arrays.toString(request.getParameterValues("board_creator_edit_length")).replaceAll("[\\[\\] ]", "");;
		String transfer = Arrays.toString(request.getParameterValues("board_creator_ori_transfer")).replaceAll("[\\[\\] ]", "");;
		if(genre != "null" && genre != "" && genre != null) {search.put("genre",genre);}
		if(price_n != "null" && price_n != "" && price_n != null) {search.put("price_n",price_n);}
		if(price_x != "null" && price_x != "" && price_x != null) {search.put("price_x",price_x);}
		if(keyword != "null" && keyword != "" && keyword != null) {search.put("keyword",keyword);}
		if(recording != "null" && recording != "" && recording != null) {search.put("recording",recording);}
		if(camnum != "null" && camnum != "" && camnum != null) {search.put("camnum",camnum);}
		if(clipnum != "null" && clipnum != "" && clipnum != null) {search.put("clipnum",clipnum);}
		if(oriLength != "null" && oriLength != "" && oriLength != null) {search.put("oriLength",oriLength);}
		if(editLength != "null" && editLength != "" && editLength != null) {search.put("editLength",editLength);}
		if(transfer != "null" && transfer != "" && transfer != null) {search.put("transfer",transfer);}
		ProjectProService projectProService = new ProjectProService();
		ArrayList<ProjectBean> list = new ArrayList<ProjectBean>();
		int listCount = projectProService.getProjectSearchListCount(search);
		projectProService = new ProjectProService();
		list = projectProService.getListSearchProject(page, search);
		
		
		int maxPage = (int)((double)listCount / limit + 0.95);
		
		int startPage = ((int)(page / 10.0 + 0.9)-1)*10+1;
		
		int endPage = startPage + 10 - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("projectList", list);
		request.setAttribute("search", search);
		forward = new ActionForward();
		forward.setPath("/project/projectList.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
