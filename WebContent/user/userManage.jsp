  
<%@page import="com.hanjum.vo.PageInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hanjum.user.vo.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	
<%
	ArrayList<UserBean> userList = (ArrayList<UserBean>)request.getAttribute("userList");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
	int listCount = pageInfo.getListCount();
%>
<!DOCTYPE html>
<html>
<head>
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
<div class="write_title"><h1>유저 목록</h1></div>
<form action="UserSearchManage.uo" method="post" name="fr_write" id="WriteForm" >
<table class="write_table">
<%
			if(userList != null && listCount > 0) {
			%>
				<tr id="tr_top" >
					<td class="td_name">ID</td>
					<td class="td_name">Lv</td>
					<td class="td_name">exp</td>
					<td class="td_name">이름</td>
					<td class="td_name">이메일</td>
					<td class="td_name">휴대전화</td>
					<td class="td_name">총 거래 건수</td>
					<td class="td_name">평점</td>
					<td class="td_name">회원타입</td>
					<td class="td_name">접속 횟수</td>
					
				</tr>
				<%for(int i = 0; i < userList.size(); i++) {
				 	String user_type = null;
				 	switch (userList.get(i).getUser_type()){
				 	case 1:
				 		user_type = "일반";
				 		break;
				 	case 2:
				 		user_type = "편집자";
				 		break;
				 	default:
				 		user_type = "관리자";
				 	}
				%>
				<tr onclick="location.href='UserInfo.uo?user_id=<%=userList.get(i).getUser_id()%>'">
					<td class="td_content"><%=userList.get(i).getUser_id() %></td>
					<td class="td_content"><%=userList.get(i).getUser_level()%></td>
					<td class="td_content"><%=userList.get(i).getUser_lv_exp()%></td>
					<td class="td_content"><%=userList.get(i).getUser_name() %></td>
					<td class="td_content"><%=userList.get(i).getUser_email() %></td>
					<td class="td_content"><%=userList.get(i).getUser_phone() %></td>
					<td class="td_content"><%=userList.get(i).getUser_project_count() %></td>
					<td class="td_content"><%=userList.get(i).getUser_score() %>
					<td class="td_content"><%=user_type%></td>
					<td class="td_content"><%=userList.get(i).getUser_login_count() %></td>
					
				</tr>
					<%} %>
				<tr>
				<td colspan="6">
				<%if(nowPage <= 1) {%>
			<input type="button" value="이전">&nbsp;
	<%} else {%>
			<input type="button" value="이전" onclick="location.href='UserManage.uo?page=<%=nowPage - 1 %>'">&nbsp;
	<%} %>
	
	<%for(int i = startPage; i <= endPage; i++) { 
			if(i == nowPage) { %>
				[<%=i %>]&nbsp;
			<%} else { %>
					<a href="UserManage.uo?page=<%=i %>">[<%=i %>]</a>&nbsp;
			<%} %>
	<%} %>
	
	<%if(nowPage >= maxPage) { %>
			<input type="button" value="다음">
	<%} else { %>
			<input type="button" value="다음" onclick="location.href='UserManage.uo?page=<%=nowPage + 1 %>'">
	<%} %>
	<%}
	else {
	%>
	<section id="emptyArea">등록된 글이 없습니다</section>
	<%
	}
	%>
	</td>
	<td colspan="4" class="td_name">
	검색 분류&nbsp;<select name="searchType"><option>ID</option><option>이름</option><option>이메일</option><option>휴대전화</option></select>
	<input type="text" name="search" id="search">
	<input type="submit" value="검색">
	</td>
	
	</tr>
</table>
</form>

</div>

<jsp:include page="../inc/bottom.jsp"/>
</div>
</body>
</html>