<%@page import="com.hanjum.user.vo.UserBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hanjum.notice.vo.NoticeBean"%>
<%@page import="com.hanjum.notice.service.NoticeProService"%>
<%@page import="com.hanjum.notice.dao.NoticeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- bootstrap -->
<link href="css/notice.css" rel="stylesheet" type="text/css">
<script src="js/jquery-3.5.1.js"></script>
<script src="js/rollingJob.js"></script>
<script src="js/notice.js"></script>
</head>

<body>

<%
UserBean userBean = (UserBean)session.getAttribute("userBean");
String user_id = userBean.getUser_id();
NoticeProService svc = new NoticeProService();
int count = svc.getNoticeCount(user_id);

 if(user_id != null){
%>
	<div id="notice_table">
		<btn id="btn" name="user_id" value="<%=user_id%>">
			<%if(count > 0) {%>
			<div id="notice_count"><%=count%></div>
			<%} %>
			<form action="getNewNotice.nt" method="post">
				<input type="hidden" name="user_id" value="<%=user_id %>"  id="user_id"/>
				<input type="submit" id="BtnIcon" value="."/>
			</form>

		</btn>
		
		<ul id="list">
			<li>	
				<!-- 읽지않은 알람 리스트 뿌림 -->
				<div id="new_notice">
					<table id="new_notice_list">
							<!--  여기에 읽지않은 알람 -->
					</table>
				</div>
			</li>
			<li>
				<!--지난 알람 리스트 뿌림 -->
				<div id="old_notice">
					<table id="old_notice_list">
						<!--  읽은알람  -->
					</table>
				</div>
			</li>	
			<li id="li_btn">
			
			<form action="getOldNotice.nt" method="post">
				<input type="hidden" name="user_id" value="<%=user_id %>"  id="user_id"/>
				<input type="submit" id="get_notice_btn" value="+"/>
			</form>
			
			</li>
		</ul>	
	</div>
<%
 }
%>
	
	
</body>
</html>





