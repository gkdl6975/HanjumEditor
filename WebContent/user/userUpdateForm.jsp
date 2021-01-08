  
<%@page import="com.hanjum.user.vo.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	
	UserBean userBean = (UserBean)request.getAttribute("userBean");
	String email = userBean.getUser_email();
	String[] cutEmail = email.split("@");
	String user_type = null;
	switch (userBean.getUser_type()){
	case 1:
		user_type = "일반 회원";
		break;
	case 2:
		user_type = "편집자 회원";
		break;
	default:
		user_type = "관리자";
	}
	
	
%>
<!DOCTYPE html>
<html>
<head>
<script>
function selEmail(email){
	if(email != "직접입력"){
		document.getElementById("user_email2").value = email;
		document.getElementById("user_email2").readOnly = true;
	}else{
		document.getElementById("user_email2").readOnly = false;
		document.getElementById("user_email2").value = "";
                document.getElementById("user_email2").focus();
	}
}
</script>
<meta charset="UTF-8">
<link href="css/default.css" rel="stylesheet">
<link href="css/board.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="js/topMenu.js"></script>
<script type="text/javascript" src="plugin/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="js/smartediter.js"></script>
<title>한줌에디터</title>
</head>
<body>
<div id="wrap">
<jsp:include page="../inc/top.jsp"/>

<div class="write_form">
<div class="write_title"><h1>내 정보</h1></div>
<form action="UserUpdatePro.uo" method="post" name="fr_write" id="WriteForm" >
<table class="write_table">
<tr><td class="td_name"><label for="Subject">아이디</label></td><td class="td_content"><input id="user_id" type="text" name="user_id" value="<%=userBean.getUser_id()%>" readonly="readonly"></td></tr>
<tr><td class="td_name"><label for="Content">비밀번호</label></td><td class="td_content"><input id="user_pass" name="user_pass"/></td></tr>
<tr><td class="td_name"><label for="Content">이름</label></td><td class="td_content"><input id="user_name" type="text" name="user_name" value="<%=userBean.getUser_name()%>" readonly="readonly"/></td></tr>
<tr><td class="td_name"><label for="Content">이메일</label></td><td class="td_content"><input id="user_email" type="text" name="user_email" value="<%=cutEmail[0]%>"/>
 @ <input type="text" name="user_email2" id="user_email2" value="<%=cutEmail[1]%>" onfocus="inInput(this)" onblur="outInput(this)"/>
<select onfocus="inInput(this)" onblur="outInput(this)" onChange="selEmail(this.value)">
	<option onselect="focus">직접입력</option>
	<option value="naver.com" 
	<%if(cutEmail[1].equals("naver.com")){%> selected ="selected" <%} %>>naver.com</option>
	<option value="gmail.com"
	<%if(cutEmail[1].equals("gmail.com")){%> selected ="selected" <%} %>>gmail.com</option>
	<option value="daum.net"
	<%if(cutEmail[1].equals("daum.net")){%> selected ="selected" <%} %>>daum.net</option>
</select></td></tr>
<tr><td class="td_name"><label for="Content">휴대 전화</label></td><td class="td_content"><input id="user_phone" type="text" name="user_phone" value="<%=userBean.getUser_phone()%>"/></td></tr>
<tr><td class="td_name"><label for="Content">레벨</label></td><td class="td_content">Lv <%=userBean.getUser_level() %></td></tr>
<tr><td class="td_name"><label for="Content">경험치</label></td><td class="td_content"><%=userBean.getUser_lv_exp() %> exp</td></tr>
<tr><td class="td_name"><label for="Content">총 거래 건수</label></td><td class="td_content"><%=userBean.getUser_project_count() %> 건</td></tr>
<tr><td class="td_name"><label for="Content">내 평점</label></td><td class="td_content"><%=userBean.getUser_score() %> 점</td></tr>
<tr><td class="td_name"><label for="Content">회원 타입</label></td><td class="td_content"><%=user_type %></td></tr>
<tr><td class="td_name"><label for="Content">로그인 횟수</label></td><td class="td_content"><%=userBean.getUser_login_count() %> 회</td></tr>
</table>
	<div class="write_form_submit"><input type="submit" id="WriteSubmit" value="수정 완료"></div>
</form>
</div>

<jsp:include page="../inc/bottom.jsp"/>
</div>
</body>
</html>