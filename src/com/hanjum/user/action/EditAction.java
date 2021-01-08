package com.hanjum.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.vo.ActionForward;

public class EditAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String target = request.getParameter("target");
		String content = request.getParameter("content");

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(target.equals("editor_program")) {
			String program_content = content.replace("프리미어", "1").replace("파이널컷", "2")
					.replace("베가스", "3").replace("에프터 이펙트", "4").replace("기타", "5");
			String checked1 = "", checked2 = "", checked3 = "", checked4 = "", checked5 = "";
			if(program_content.contains("1")) {checked1 = "checked";}
			if (program_content.contains("2")) {checked2 = "checked";}
			if (program_content.contains("3")) {checked3 = "checked";}
			if (program_content.contains("4")) {checked4 = "checked";}
			if (program_content.contains("5")) {checked5 = "checked";}
			out.println("<div class=\"form-group\">\r\n" + 
					"	                <div class=\"check_list\"><input type=\"checkbox\" id=\"program1\" name=\"editor_program\" value=\"1\" "+checked1+"><span class=\"checkbox\">프리미어</span></div>\r\n" + 
					"					<div class=\"check_list\"><input type=\"checkbox\" id=\"program2\" name=\"editor_program\" value=\"2\" "+checked2+"><span class=\"checkbox\">파이널컷</span></div>\r\n" + 
					"					<div class=\"check_list\"><input type=\"checkbox\" id=\"program3\" name=\"editor_program\" value=\"3\" "+checked3+"><span class=\"checkbox\">베가스</span></div>\r\n" + 
					"					<div class=\"check_list\"><input type=\"checkbox\" id=\"program4\" name=\"editor_program\" value=\"4\" "+checked4+"><span class=\"checkbox\">에프터 이펙트</span></div>\r\n" + 
					"					<div class=\"check_list\"><input type=\"checkbox\" id=\"program5\" name=\"editor_program\" value=\"5\" "+checked5+"><span class=\"checkbox\">기타</span></div>\r\n" + 
					"                </div>");
		} else if(target.equals("editor_solution")) {
			
			String solution_content = content.replace("HD", "1").replace("FHD", "2")
					.replace("UHD", "3").replace("맞춤 가능", "4");
			String checked1 = "", checked2 = "", checked3 = "", checked4 = "";
			if(solution_content.contains("1")) {checked1 = "checked";}
			if (solution_content.contains("2")) {checked2 = "checked";}
			if (solution_content.contains("3")) {checked3 = "checked";}
			if (solution_content.contains("4")) {checked4 = "checked";}
			out.println("<div class=\"form-group\">\r\n" + 
					"	                <div class=\"check_list\"><input type=\"checkbox\" id=\"solution1\" name=\"editor_solution\" value=\"1\" "+checked1+"><span class=\"checkbox\">HD</span></div>\r\n" + 
					"					<div class=\"check_list\"><input type=\"checkbox\" id=\"solution2\" name=\"editor_solution\" value=\"2\" "+checked2+"><span class=\"checkbox\">FHD</span></div>\r\n" + 
					"					<div class=\"check_list\"><input type=\"checkbox\" id=\"solution3\" name=\"editor_solution\" value=\"3\" "+checked3+"><span class=\"checkbox\">UHD</span></div>\r\n" + 
					"					<div class=\"check_list\"><input type=\"checkbox\" id=\"solution4\" name=\"editor_solution\" value=\"4\" "+checked4+"><span class=\"checkbox\">맞춤 가능</span></div>\r\n" + 
					"                </div>");
		} else if(target.equals("editor_inventory")) {
			
			String inventory_content = content.replace("컷 편집", "1").replace("오디오 싱크", "2")
					.replace("BGM 삽입", "3").replace("효과음 삽입", "4").replace("모션 그래픽", "5").replace("템플릿 작업", "6");
			String checked1 = "", checked2 = "", checked3 = "", checked4 = "", checked5 = "", checked6 = "";
			if(inventory_content.contains("1")) {checked1 = "checked";} 
			if (inventory_content.contains("2")) {checked2 = "checked";} 
			if (inventory_content.contains("3")) {checked3 = "checked";}
			if (inventory_content.contains("4")) {checked4 = "checked";} 
			if (inventory_content.contains("5")) {checked4 = "checked";}
			if (inventory_content.contains("6")) {checked4 = "checked";}
			out.println("<div class=\"form-group\">\r\n" + 
					"	                <div class=\"check_list\"><input type=\"checkbox\" id=\"inventory1\" name=\"editor_inventory\" value=\"1\" "+checked1+"><span class=\"checkbox\">컷 편집</span></div>\r\n" + 
					"					<div class=\"check_list\"><input type=\"checkbox\" id=\"inventory2\" name=\"editor_inventory\" value=\"2\" "+checked2+"><span class=\"checkbox\">오디오 싱크</span></div>\r\n" + 
					"					<div class=\"check_list\"><input type=\"checkbox\" id=\"inventory3\" name=\"editor_inventory\" value=\"3\" "+checked3+"><span class=\"checkbox\">BGM 삽입</span></div>\r\n" + 
					"					<div class=\"check_list\"><input type=\"checkbox\" id=\"inventory4\" name=\"editor_inventory\" value=\"4\" "+checked4+"><span class=\"checkbox\">효과음 삽입</span></div>\r\n" + 
					"					<div class=\"check_list\"><input type=\"checkbox\" id=\"inventory5\" name=\"editor_inventory\" value=\"5\" "+checked5+"><span class=\"checkbox\">모션 그래픽</span></div>\r\n" + 
					"					<div class=\"check_list\"><input type=\"checkbox\" id=\"inventory6\" name=\"editor_inventory\" value=\"6\" "+checked6+"><span class=\"checkbox\">템플릿 작업</span></div>\r\n" + 
					"                </div>");
		} else if(target.equals("editor_upload")) {
			String checked1 = "", checked2 = "", checked3 = "", checked4 = "";
			switch (content) {
			case "이메일": checked1 = "checked"; break;
			case "NAS": checked2 = "checked"; break;
			case "웹하드": checked3 = "checked"; break;
			case "모두 가능": checked4 = "checked"; break;
			}
			out.println("<div class=\"form-group\">\r\n" + 
					"	                <div class=\"check_list\"><input type=\"radio\" id=\"upload1\" name=\"editor_upload\" value=\"1\" "+checked1+"><span class=\"checkbox\">이메일</span></div>\r\n" + 
					"					<div class=\"check_list\"><input type=\"radio\" id=\"upload2\" name=\"editor_upload\" value=\"2\" "+checked2+"><span class=\"checkbox\">NAS</span></div>\r\n" + 
					"					<div class=\"check_list\"><input type=\"radio\" id=\"upload3\" name=\"editor_upload\" value=\"3\" "+checked3+"><span class=\"checkbox\">웹하드</span></div>\r\n" + 
					"					<div class=\"check_list\"><input type=\"radio\" id=\"upload4\" name=\"editor_upload\" value=\"4\" "+checked4+"><span class=\"checkbox\">모두 가능</span></div>\r\n" + 
					"                </div>");
		} else if(target.equals("editor_work")) {
			String checked1 = "", checked2 = "", checked3 = "", checked4 = "";
			switch (content) {
			case "1캠": checked1 = "checked"; break;
			case "2캠": checked2 = "checked"; break;
			case "3캠": checked3 = "checked"; break;
			case "4캠 이상": checked4 = "checked"; break;
			}
			out.println("<div class=\"form-group\">\r\n" + 
					"                	<div class=\"check_list\"><input type=\"radio\" id=\"work1\" name=\"editor_work\" value=\"1\" "+checked1+"><span class=\"checkbox\">1캠</span></div>\r\n" + 
					"					<div class=\"check_list\"><input type=\"radio\" id=\"work2\" name=\"editor_work\" value=\"2\" "+checked2+"><span class=\"checkbox\">2캠</span></div>\r\n" + 
					"					<div class=\"check_list\"><input type=\"radio\" id=\"work3\" name=\"editor_work\" value=\"3\" "+checked3+"><span class=\"checkbox\">3캠</span></div>\r\n" + 
					"					<div class=\"check_list\"><input type=\"radio\" id=\"work4\" name=\"editor_work\" value=\"4\" "+checked4+"><span class=\"checkbox\">4캠 이상</span></div>\r\n" + 
					"                </div>");
		} else if(target.equals("editor_meeting")) {
			String checked1 = "", checked2 = "";
			switch (content) {
			case "가능": checked1 = "checked"; break;
			case "불가능": checked2 = "checked"; break;
			}
			out.println("<div class=\"form-group\">\r\n" + 
					"	                <div class=\"check_list\"><input type=\"radio\" id=\"meeting1\" name=\"editor_meeting\" value=\"1\" "+checked1+"><span class=\"checkbox\">가능</span></div>\r\n" + 
					"					<div class=\"check_list\"><input type=\"radio\" id=\"meeting2\" name=\"editor_meeting\" value=\"2\" "+checked2+"><span class=\"checkbox\">불가능</span></div>\r\n" + 
					"                </div>");
		} else if(target.equals("editor_fort")) {
			String checked1 = "", checked2 = "", checked3 = "";
			switch (content) {
			case "건당 계약": checked1 = "checked"; break;
			case "단기 계약": checked2 = "checked"; break;
			case "장기 계약": checked3 = "checked"; break;
			}
			out.println("<div class=\"form-group\">\r\n" + 
					"	                <div class=\"check_list\"><input type=\"radio\" id=\"fort1\" name=\"editor_fort\" value=\"1\" "+checked1+"><span class=\"checkbox\">건당 계약</span></div>\r\n" + 
					"					<div class=\"check_list\"><input type=\"radio\" id=\"fort2\" name=\"editor_fort\" value=\"2\" "+checked2+"><span class=\"checkbox\">단기 계약</span></div>\r\n" + 
					"					<div class=\"check_list\"><input type=\"radio\" id=\"fort3\" name=\"editor_fort\" value=\"3\" "+checked3+"><span class=\"checkbox\">장기 계약</span></div>\r\n" + 
					"                </div>");
		} else if(target.equals("editor_sample")) {
			String checked1 = "", checked2 = "";
			switch (content) {
			case "가능": checked1 = "checked"; break;
			case "불가능": checked2 = "checked"; break;
			}
			out.println("<div class=\"form-group\">\r\n" + 
					"	                <div class=\"check_list\"><input type=\"radio\" id=\"sample1\" name=\"editor_sample\" value=\"1\" "+checked1+"><span class=\"checkbox\">가능</span></div>\r\n" + 
					"					<div class=\"check_list\"><input type=\"radio\" id=\"sample2\" name=\"editor_sample\" value=\"2\" "+checked2+"><span class=\"checkbox\">불가능</span></div>\r\n" + 
					"                </div>");
		} else if(target.equals("editor_price")) {
			String min_price = request.getParameter("editor_ed_min_price");
			String max_price = request.getParameter("editor_ed_max_price");
			out.println("<div class=\"form-group\"  style=\"max-width:300px; margin-bottom:0px;\">\r\n" + 
					"              	  <input class=\"form-control\" style=\"width:40%; display: inline-block;\" type=\"number\" id=\"MinPrice\" step=\"1000\" min=\"0\" name=\"editor_ed_min_price\" value=\""+min_price+"\"> 원 ~ <input class=\"form-control\" style=\"width:40%; display: inline-block;\" type=\"number\" id=\"MaxPrice\" step=\"1000\" min=\"0\" name=\"editor_ed_max_price\" value=\""+max_price+"\"> 원\r\n" + 
					"                </div>");
		} else if(target.equals("editor_address")) {
			String selected1 = "",selected2 = "",selected3 = "",selected4 = "",selected5 = "",selected6 = "",selected7 = "",selected8 = "",selected9 = "",selected10 = "",
					selected11 = "",selected12 = "",selected13 = "",selected14 = "",selected15 = "",selected16 = "",selected17 = "";
			switch (content) {
			case "서울": selected1="selected";break;
			case "부산": selected2="selected";break;
			case "대구": selected3="selected";break;
			case "대전": selected4="selected";break;
			case "인천": selected5="selected";break;
			case "광주": selected6="selected";break;
			case "울산": selected7="selected";break;
			case "세종": selected8="selected";break;
			case "경기": selected9="selected";break;
			case "강원": selected10="selected";break;
			case "충북": selected11="selected";break;
			case "충남": selected12="selected";break;
			case "경북": selected13="selected";break;
			case "경남": selected14="selected";break;
			case "전북": selected15="selected";break;
			case "전남": selected16="selected";break;
			case "제주": selected17="selected";break;
			}
			out.println("<div class=\"form-group\">\r\n" + 
					"	                <select class=\"form-control\"  name=\"editor_address\">\r\n" + 
					"	                <option "+selected1+" value='서울'>서울</option>\r\n" + 
					"	                <option "+selected2+" value='부산'>부산</option>\r\n" + 
					"	                <option "+selected3+" value='대구'>대구</option>\r\n" + 
					"	                <option "+selected4+" value='대전'>대전</option>\r\n" + 
					"	                <option "+selected5+" value='인천'>인천</option>\r\n" + 
					"	                <option "+selected6+" value='광주'>광주</option>\r\n" + 
					"					<option "+selected7+" value='울산'>울산</option>\r\n" + 
					"					<option "+selected8+" value='세종'>세종</option>\r\n" + 
					"					<option "+selected9+" value='경기'>경기</option>\r\n" + 
					"					<option "+selected10+" value='강원'>강원</option>\r\n" + 
					"					<option "+selected11+" value='충북'>충북</option>\r\n" + 
					"					<option "+selected12+" value='충남'>충남</option>\r\n" + 
					"					<option "+selected13+" value='경북'>경북</option>\r\n" + 
					"					<option "+selected14+" value='경남'>경남</option>\r\n" + 
					"					<option "+selected15+" value='전북'>전북</option>\r\n" + 
					"					<option "+selected16+" value='전남'>전남</option>\r\n" + 
					"					<option "+selected17+" value='제주'>제주</option>\r\n" + 
					"					</select>\r\n" + 
					"                </div>");
		} else if(target.equals("editor_photo")) {
			String photo = content.substring(content.lastIndexOf("/")+1);
			if(content.equals("no-profile.png")){
				photo = "";
			}
			out.println("<form method=\"post\" name=\"fr_photo\" id=\"WriteForm\" style=\"display:inline-block; margin-top:10px; vertical-align: bottom;\" enctype=\"multipart/form-data\" onsubmit=\"return false\">\r\n" + 
					"<div class=\"form-group\" style=\"margin:0px;\">\r\n" + 
					"                <input class=\"form-control\" id=\"photo_content\" type=\"text\" readonly=\"readonly\" style=\"min-width:100px; width:60%; display: inline-block;\" value=\""+photo+"\"> \r\n" + 
					"                <input type=\"button\" class=\"btn btn-primary\" id=\"editor_photo_btn\" value=\"프로필 업로드\"> \r\n" + 
					"                <input type=\"file\" id=\"editor_photo_form\" name=\"editor_photo\"/>\r\n" + 
					"                </div>"
					+ "</form>");
		}
		
		
		return null;
	}

}
