<%@page import="com.hanjum.user.vo.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
UserBean userBean = new UserBean();
userBean = (UserBean)session.getAttribute("userBean");
String user_id = userBean.getUser_id();
	
%>
<link rel="stylesheet" href="css/style.css">
<script src="js/jquery.min.js"></script>
    <script src="js/email.js"></script>
<div class="login-wrap p-4 p-md-5" style="margin-top:0px;">
	<h3 class="mb-4">비밀번호 변경</h3>
	<hr>
	<form action="changePass.uo" method="post" name="fr_write" id="WriteForm" onsubmit="return check()">
	<div class="form-group">
    	<label id="id_label" class="label has-focus" for="user_id">아이디</label>
        <input type="text" id="user_id" class="form-control" name="user_id" value="<%=user_id %>" readonly="readonly"/>
    </div>
    <div class="form-group">
        <label id="pass_label" class="label" for="password">현재 비밀번호</label>
        <input id="password" type="password" class="form-control" name="user_pass" required="required">
    </div>
    <div class="form-group">
                 <label id="changePass_label" class="label" for="password-field">변경할 비밀번호</label>
                 <input id="password-field" type="password" class="form-control" name="user_changePass" required="required" onkeyup="checkPasswd()">
                 <div id="checkPasswdResult" class="offset-1"><!-- 자바스크립트에서 메세지 출력 공간 --></div>
                 </div>
                 <div class="form-group">
                 <label id="changePass_label2" class="label" for="password-field2">비밀번호 확인</label>
                 <input id="password-field2" type="password" class="form-control" name="user_changePass2" required="required" onkeyup="checkPasswd()">
             	<div id="checkPasswdResult2" class="offset-1"><!-- 자바스크립트에서 메세지 출력 공간 --></div>
	             </div>
	<div class="form-group">
		<span class="form-group-content">
		<span class="float-r m-r-10">
			<input class="btn btn-primary" type="submit" value="비밀번호 변경">
		</span>
		</span>             
	</div>    
	</form>
</div>