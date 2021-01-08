<%@page import="com.hanjum.user.vo.UserBean"%>
<%@page import="com.hanjum.vo.PageInfo"%>
<%@page import="java.util.ArrayList"%>
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
<!--<![endif]-->
<!-- BEGIN HEAD -->

<head>
<meta charset="utf-8" />
<title>admin</title>
<!-- .write_table -->
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link href="css/admin_user.css?ver=1" rel="stylesheet" type="text/css" />
<link href="css/themes.css" rel="stylesheet" type="text/css"
	id="style_color" />
<link href="css/manager.css" rel="stylesheet" type="text/css" />
	
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="DataTables/datatables.min.css?ver=1"/>
<script type="text/javascript" src="DataTables/datatables.min.js"></script>
<script type="text/javascript">
$(document).ready( function () {
    $('#write_table').DataTable({
    	//표시 건수기능 숨기기
    	lengthChange: false,
    	// 검색 기능 숨기기
    	searching: false,
    	// 정보 표시 숨기기
    	info: false,
    	// 페이징 기능 숨기기
    	paging: false
    });
} );
 
</script>
<!-- END THEME LAYOUT STYLES -->
<link rel="shortcut icon" href="favicon.ico" />
</head>
<!-- END HEAD -->

<body>
	<jsp:include page="../inc/admin_top.jsp" />



		<!-- BEGIN CONTAINER -->
		<div class="page-container">
			<!-- BEGIN SIDEBAR -->
			<div class="page-sidebar-wrapper">
				<!-- BEGIN SIDEBAR -->
				<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
				<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
				<div class="page-sidebar navbar-collapse collapse">
					<!-- BEGIN SIDEBAR MENU -->
					<!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
					<!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
					<!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
					<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
					<!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
					<!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
					<ul class="page-sidebar-menu  page-header-fixed "
					data-keep-expanded="false" data-auto-scroll="true"
					data-slide-speed="200" style="padding-top: 20px">
					<!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<!-- END SIDEBAR TOGGLER BUTTON -->
					<!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
					<li class="sidebar-search-wrapper">
						<!-- BEGIN RESPONSIVE QUICK SEARCH FORM --> <!-- DOC: Apply "sidebar-search-bordered" class the below search form to have bordered search box -->
						<!-- DOC: Apply "sidebar-search-bordered sidebar-search-solid" class the below search form to have bordered & solid search box -->

						<!-- END RESPONSIVE QUICK SEARCH FORM -->
					</li>
					<li class="nav-item start active open">
					<a href="javascript:;" class="nav-link nav-toggle"> 
					<span class="title" style="font-weight: bold;">관리자 페이지</span> 
					</a>
<!-- 							<ul class="sub-menu"> -->
<!-- 									<li class="nav-item start"><a href="UserManage.uo" class="nav-link ">  -->
<!-- 										<span class="title">회원 관리</span>  -->
<!-- 									</a></li> -->
<!-- 									<li class="nav-item start "><a href="ContractList.ct" class="nav-link "> -->
<!-- 										<span class="title">계약 관리 </span> -->
<!-- 									</a></li> -->
<!-- 									<li class="nav-item start"><a href="CategoryList.cg" class="nav-link"> -->
<!-- 										<span class="title">Category 관리</span> -->
<!-- 									</a></li> -->
<!-- 									<li class="nav-item start "><a href="helpList.hp" class="nav-link "> -->
<!-- 										<span class="title">Help 관리</span> -->
<!-- 									</a></li> -->
<!-- 							</ul> -->
					</li>
					<li class="nav-item start active"><a href="UserManage.uo" class="nav-link "> 
						<span class="title">회원 관리</span> 
						<span class="selected"></span>
					</a></li>
					<li class="nav-item start"><a href="ContractList.ct" class="nav-link ">
						<span class="title">계약 관리 </span>
					</a></li>
					<li class="nav-item start"><a href="CategoryList.cg" class="nav-link">
						<span class="title">Category 관리</span>
						</a></li>
					<li class="nav-item start"><a href="helpList.hp" class="nav-link ">
						<span class="title">Help 관리</span>
						</a></li>
					<li class="nav-item start"><a href="UserReportManage.uo" class="nav-link ">
						<span class="title">신고 관리</span>
						</a></li>
				</ul>
				</div>
				<!-- END SIDEBAR -->
			</div>
			<!-- END SIDEBAR -->
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<!-- BEGIN CONTENT BODY -->
				<div class="page-content">
					<!-- BEGIN PAGE HEADER-->

					<!-- BEGIN PAGE BAR -->
					<div class="page-bar">
						<ul class="page-breadcrumb">
							<li><a href="index.html">관리자 페이지</a> <i class="fa fa-circle"></i>
							</li>
							<li><span>회원 관리</span></li>
						</ul>

					</div>
					<!-- END PAGE BAR -->
					<!-- BEGIN PAGE TITLE-->
					<h1 class="page-title">
						회원 관리 <small> 설명 달기..</small>
					</h1>
					<!-- END PAGE TITLE-->
					<!-- END PAGE HEADER-->

<!-- 여기서 부터 작업하면 됨!!!!!!!!!!!!!!!!!!!!!!!1 -->

		
	<div class="write_form">
<div class="write_title"><span onclick="location.href='UserManage.uo'" class="manage_menu">유저 관리</span> / 
<span onclick="location.href='UserReportManage.uo'" class="manage_menu">신고 관리</span></div>
<form action="UserSearchManage.uo" method="post" name="fr_write" id="WriteForm" >
		<table id="write_table" class="tablesorter">
<!-- 				<colgroup> -->
<!-- 					<col width="10%"/> 아이디 -->
<!-- 				    <col width="5%"/>	레벨 -->
<!-- 				    <col width="5%"/>	경험치 -->
<!-- 				    <col width="10%"/>	이름 -->
<!-- 				    <col width="15%"/>	이메일 -->
<!-- 				    <col width="10%"/>	전화번호 -->
<!-- 				    <col width="10%"/>	총 거래 건수 -->
<!-- 				    <col width="5%"/>	평점 -->
<!-- 				    <col width="10%"/>	회원타입 -->
<!-- 				    <col width="10%"/>	접속횟수 -->
				    
<!-- 				</colgroup> -->
<%
			if(userList != null && listCount > 0) {
			%>
			<thead>
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
			</thead>
				
			<tbody>
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
				 	double exp=(double)userList.get(i).getUser_lv_exp()/(userList.get(i).getUser_level()*20);
				 	int intExp=(int)Math.round(exp*100);
				%>
				
				<tr>
					<td class="td_content"><%=userList.get(i).getUser_id() %></td>
					<td class="td_content"><%=userList.get(i).getUser_level()%></td>
					<td class="td_content"><%=intExp%>%</td><!--  (exp / (lv * 20)) * 100-->
					<td class="td_content"><%=userList.get(i).getUser_name() %></td>
					<td class="td_content"><%=userList.get(i).getUser_email() %></td>
					<td class="td_content"><%=userList.get(i).getUser_phone() %></td>
					<td class="td_content"><%=userList.get(i).getUser_project_count() %></td>
					<td class="td_content"><%=userList.get(i).getUser_score() %>
					<td class="td_content"><%=user_type%></td>
					<td class="td_content"><%=userList.get(i).getUser_login_count() %></td>
					
				</tr>
					<%} %>
			</tbody>
		</table>
		<%String search = request.getParameter("search");
		String searchType = request.getParameter("searchType");
		String url = "UserManage.uo?";
		if(search != null){
			url = "UserSearchManage.uo?search="+search+"&searchType="+searchType+"&";
		}%>
		<div id="paging_area">
				<%if(nowPage <= 1) {%>
			<input type="button" value="이전" class="btn">&nbsp;
	<%} else {%>
			<input type="button" value="이전" class="btn" onclick="location.href='<%=url %>page=<%=nowPage - 1 %>'">&nbsp;
	<%} %>
	
	<%for(int i = startPage; i <= endPage; i++) { 
			if(i == nowPage) { %>
				[<%=i %>]&nbsp;
			<%} else { %>
					<a href="<%=url %>page=<%=i %>">[<%=i %>]</a>&nbsp;
			<%} %>
	<%} %>
	
	<%if(nowPage >= maxPage) { %>
			<input type="button" value="다음" class="btn">
	<%} else { %>
			<input type="button" value="다음" class="btn" onclick="location.href='<%=url %>page=<%=nowPage + 1 %>'">
	<%} %>
	<%}
	else {
	%>
	<section id="emptyArea">등록된 글이 없습니다</section>
	<%
	}
	%>
	</div>
	<div id="search_menu_area">
		검색 분류&nbsp;<select name="searchType"><option>ID</option><option>이름</option><option>이메일</option><option>휴대전화</option></select>
		<input type="text" name="search" id="search">
		<input type="submit" value="검색" class="btn">
	</div>


</form>
</div>
</div>
</div>
</div>	

<hr>

					<div class="row">

						<div class="portlet light portlet-fit bordered">



							<!-- END CONTENT -->

						</div>
						<!-- END CONTAINER -->

					</div>

	
</body>


</html>