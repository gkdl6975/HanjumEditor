package com.hanjum.board.action;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.board.service.BoardProService;
import com.hanjum.board.service.ProjectProService;
import com.hanjum.board.vo.ProjectBean;
import com.hanjum.vo.ActionForward;
import com.hanjum.vo.Constant;

public class ProjectUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProjectUpdateProAction!");
		ActionForward forward = null;
		
		boolean isSuccess = false;
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		int nowPage = Integer.parseInt(request.getParameter("page"));
		ProjectBean projectBean = new ProjectBean();
		projectBean.setBoard_subject(request.getParameter("board_subject"));
		projectBean.setBoard_content(request.getParameter("board_content"));
		projectBean.setBoard_date(new Timestamp(System.currentTimeMillis()));
		projectBean.setBoard_type(1);
		projectBean.setBoard_id(board_id);
		
		String[] genreOriArr = request.getParameterValues("board_creator_genre");
		ArrayList<String> genreArr = new ArrayList<String>();
		for(String genreStr : genreOriArr) {
			if(!genreStr.equals("")) {
				genreArr.add(genreStr);
			}
		}
		String genre = genreArr.toString().replaceAll("[\\[\\] ]", "");
		
		String[] refOriArr = request.getParameterValues("board_creator_cre_ref");
		ArrayList<String> refArr = new ArrayList<String>();
		for(String refStr : refOriArr) {
			if(!refStr.equals("")) {
				refArr.add(refStr);
			}
		}
		String ref = refArr.toString().replaceAll("[\\[\\] ]", "");
		
		projectBean.setBoard_creator_content_detail(request.getParameter("board_creator_content_detail"));
		projectBean.setBoard_creator_genre(genre);
		projectBean.setBoard_creator_recording(Integer.parseInt(request.getParameter("board_creator_recording")));
		projectBean.setBoard_creator_cam_num(Integer.parseInt(request.getParameter("board_creator_cam_num")));
		projectBean.setBoard_creator_ori_clip_num(Integer.parseInt(request.getParameter("board_creator_ori_clip_num")));
		projectBean.setBoard_creator_ori_length(Integer.parseInt(request.getParameter("board_creator_ori_length")));
		projectBean.setBoard_creator_edit_length(Integer.parseInt(request.getParameter("board_creator_edit_length")));
		projectBean.setBoard_creator_ori_transfer(Integer.parseInt(request.getParameter("board_creator_ori_transfer")));
		projectBean.setBoard_creator_cre_ref(ref);
		projectBean.setBoard_creator_cre_min_price(Integer.parseInt(request.getParameter("board_creator_cre_min_price")));
		projectBean.setBoard_creator_cre_max_price(Integer.parseInt(request.getParameter("board_creator_cre_max_price")));
		projectBean.setBoard_creator_status(0);
		
		ProjectProService projectProService = new ProjectProService();
		isSuccess = projectProService.modifyProject(projectBean);
		
		if(!isSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(Constant.arrMsg);
			
		} else {
			forward = new ActionForward();
			forward.setPath("Project.bo?page="+nowPage+"&board_id="+projectBean.getBoard_id());
			forward.setRedirect(true);
		}
		return forward;
	}
	
}
