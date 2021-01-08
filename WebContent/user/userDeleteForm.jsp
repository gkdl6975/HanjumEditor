<%@page import="com.hanjum.user.vo.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
UserBean userBean = new UserBean();
userBean = (UserBean)session.getAttribute("userBean");
String user_id = userBean.getUser_id();
	
%>
<div class="login-wrap p-4 p-md-5" style="margin-top:0px;">
	<h3 class="mb-4">회원탈퇴</h3>
	<hr>
	<form action="UserDeletePro.uo" method="post" name="fr_write" id="WriteForm" >
	<p class="mb-4">정말로 탈퇴하시려면 비밀번호를 입력해주세요.</p>
	<div class="form-group">
    	<label id="id_label" class="label has-focus" for="user_id">아이디</label>
        <input type="text" id="user_id" class="form-control" name="user_id" value="<%=user_id %>" readonly="readonly"/>
    </div>
    <div class="form-group">
        <label id="pass_label" class="label" for="password">비밀번호</label>
        <input id="password" type="password" class="form-control" name="user_pass" required="required">
    </div>
	<div class="form-group">
		<span class="form-group-content">
		<span class="float-r m-r-10">
			<input class="btn btn-primary" type="submit" value="회원탈퇴">
		</span>
		</span>             
	</div>    
	</form>
</div>