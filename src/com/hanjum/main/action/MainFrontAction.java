package com.hanjum.main.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.board.service.EditorProService;
import com.hanjum.board.service.ProjectProService;
import com.hanjum.board.vo.EditorBean;
import com.hanjum.board.vo.ProjectBean;
import com.hanjum.vo.ActionForward;

public class MainFrontAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProjectGenreCountAction!");
		ActionForward forward = null;
		
		ProjectProService projectProService = new ProjectProService();
		HashMap<String, Integer> countList = projectProService.getProjectGenreCount();
		request.setAttribute("projectGenreCount", countList);
		
		projectProService = new ProjectProService();
		HashMap<Integer, Integer> statusCount = projectProService.getStatusCount();
		request.setAttribute("projectStatusCount", statusCount);
		
		projectProService = new ProjectProService();
		ArrayList<ProjectBean> projectList = projectProService.getListProject(1);
		request.setAttribute("projectList", projectList);
		
		EditorProService editorProService = new EditorProService();
		ArrayList<EditorBean> editorList = editorProService.getListEditor(1);
		request.setAttribute("editorList", editorList);
		
		forward = new ActionForward();
		forward.setPath("index.jsp");
		return forward;
	}

}
