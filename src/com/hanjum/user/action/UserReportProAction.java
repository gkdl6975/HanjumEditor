package com.hanjum.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.user.service.UserProService;
import com.hanjum.user.vo.ReportBean;
import com.hanjum.vo.ActionForward;

public class UserReportProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String user_id = request.getParameter("user_id");
		String report_from_user = request.getParameter("report_from_user");
		int report_type = Integer.parseInt(request.getParameter("report_type"));
		String report_content = request.getParameter("report_content");
		ReportBean reportBean = new ReportBean();
		reportBean.setReport_content(report_content);
		reportBean.setReport_type(report_type);
		reportBean.setReport_userId(report_from_user);
		reportBean.setUser_id(user_id);
		if(user_id.equals(report_from_user)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter(); 
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('자수는 불가능합니다.')"); // 다이얼로그 메세지 출력
			out.println("history.back()"); // 이전 페이지로 이동
			out.println("</script>"); // 자바스크립트 끝 태그
		} else {
			UserProService userReportService = new UserProService();
			boolean reportSuccess = userReportService.userReport(reportBean);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter(); 
			if(!reportSuccess) {
				out.println("<script>"); // 자바스크립트 시작 태그
				out.println("alert('신고 실패!')"); // 다이얼로그 메세지 출력
				out.println("history.back()"); // 이전 페이지로 이동
				out.println("</script>"); // 자바스크립트 끝 태그
			} else {
				out.println("<script>"); // 자바스크립트 시작 태그
				out.println("alert('성공적으로 신고하였습니다.')"); // 다이얼로그 메세지 출력
				out.println("location.href='Home.uo'"); // 이전 페이지로 이동
				out.println("</script>"); // 자바스크립트 끝 태그
			}
		}
		
		
		return forward;
	}

}
