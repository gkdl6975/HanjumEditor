<%@page import="com.hanjum.user.vo.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
UserBean userBean = (UserBean)session.getAttribute("userBean");
String user_id = "";
if(userBean != null){
	user_id = userBean.getUser_id();
%>
<style>
.no-border { border: 0;}
.no-border:focus {outline: none;}
.report_form_1 {display: flex;}
.report_form_1 select {flex-grow: 1; height: 100%; width: 100%; max-width: 100px;}
.report_form_1 input {flex-grow: 3; height: 100%; width: 100%;}
</style>
	<div class="login-wrap p-4 p-md-5" style="width:100%;">
	<h3>유저 신고</h3>
	<hr>
	<form action="UserReportPro.uo" method="post">
	<input type="hidden" name="report_from_user" value="<%=user_id%>">
	<div class="form-group">
	<label class="label has-focus">유저신고</label>
	<div class="form-control report_form_1">
	<select class="no-border" name="report_type">
	<option value="1">욕설</option>
	<option value="2">불건전한 아이디</option>
	<option value="3">사기</option>
	<option value="4">기타</option>
	</select>
	<input class="no-border" type="text" name="user_id" placeholder="신고대상유저">
	</div>
	</div>
	<div class="form-group">
		<label class="label" for="report_content">신고내용</label>
        <input type="text" id="report_content" class="form-control" name="report_content" required="required"/>
	</div>
	<div class="form-group d-flex justify-content-end mt-4">
    <input class="btn btn-primary submit" type="submit" value="신고하기">
    </div>
	</form>
	</div>
<%
} else {
	%>
	<script>
	alert("로그인 후 이용가능한 페이지 입니다.");
	history.back();
	</script>
	<%	
}

%>