package com.hanjum.user.action;

import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.user.service.UserProService;
import com.hanjum.user.vo.EditorBean;
import com.hanjum.vo.ActionForward;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UserInsertEditorProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UserInsertProAction!");
		ActionForward forward = null; 
		
		String[] programArr = request.getParameterValues("editor_program");
		String program = Arrays.toString(programArr).replaceAll("[\\[\\] ]", "");
		String[] solutionArr = request.getParameterValues("editor_solution");
		String solution = Arrays.toString(solutionArr).replaceAll("[\\[\\] ]", "");
		String[] inventoryArr = request.getParameterValues("editor_inventory");
		String inventory = Arrays.toString(inventoryArr).replaceAll("[\\[\\] ]", "");
		EditorBean editorBean = new EditorBean();
		
		editorBean.setUser_id(request.getParameter("user_id"));
		editorBean.setUser_pass(request.getParameter("user_pass"));
		editorBean.setUser_name(request.getParameter("user_name"));
		editorBean.setUser_email(request.getParameter("user_email")+"@"+request.getParameter("user_email2"));
		editorBean.setUser_phone(request.getParameter("user_phone"));
		editorBean.setEditor_photo(request.getParameter("editor_photo"));
		editorBean.setEditor_des(request.getParameter("editor_des"));
		editorBean.setEditor_profile(request.getParameter("editor_profile"));
		editorBean.setEditor_program(program);
		editorBean.setEditor_solution(solution);
		editorBean.setEditor_inventory(inventory);
		editorBean.setEditor_upload(Integer.parseInt(request.getParameter("editor_upload")));
		editorBean.setEditor_work(Integer.parseInt(request.getParameter("editor_work")));
		editorBean.setEditor_meeting(Integer.parseInt(request.getParameter("editor_meeting")));
		editorBean.setEditor_fort(Integer.parseInt(request.getParameter("editor_fort")));
		editorBean.setEditor_sample(Integer.parseInt(request.getParameter("editor_sample")));
		editorBean.setEditor_ed_min_price(Integer.parseInt(request.getParameter("editor_ed_min_price")));
		editorBean.setEditor_ed_max_price(Integer.parseInt(request.getParameter("editor_ed_max_price")));
		editorBean.setEditor_address(request.getParameter("editor_address"));
		editorBean.setUser_type(2);
		
		UserProService userInsertProServic = new UserProService();
		boolean isSuccess = userInsertProServic.insertUser(editorBean);
		System.out.println("서비스 성공!");
		if(!isSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter(); 
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('회원 가입 실패!')"); // 다이얼로그 메세지 출력
			out.println("history.back()"); // 이전 페이지로 이동
			out.println("</script>"); // 자바스크립트 끝 태그
		} else {
			// 1. ActionForward 객체 생성
			forward = new ActionForward();
			// 2. 포워딩 경로(URL) 지정 (주의! 경로명 앞에 슬래시(/) 기호를 붙이지 말 것!)
			forward.setPath("My.uo?fr=login");
			// 3. 포워딩 방식(Redirect 방식) 지정
			forward.setRedirect(true);
		}
		// 4. ActionForward 객체 리턴 => BoardFrontController 클래스로 전달
		return forward;
	}

}
