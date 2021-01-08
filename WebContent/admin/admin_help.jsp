<%@page import="com.hanjum.vo.PageInfo"%>
<%@page import="com.hanjum.help.vo.HelpBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<HelpBean> list = 
		(ArrayList<HelpBean>)request.getAttribute("list");

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
<link href="css/help.css?ver=1" rel="stylesheet" type="text/css" />
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link href="css/themes.css" rel="stylesheet" type="text/css" id="style_color" />
<link href="css/manager.css" rel="stylesheet" type="text/css" />

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
	<!-- 제이쿼리로 content 를 수정폼으로 바꾸기 -->
<script>
	$(document).ready(function(){
		$(document).on('click','.update_btn',function(){
			alert("원하시는 부분을 수정해주세요.");
			var updateId = $(this).attr('id');
			var updateQ = "#update_question"+updateId.replace('update_btn','');
			var updateA = "#update_answer"+updateId.replace('update_btn','');
			var btnArea = "#btn-area"+updateId.replace('update_btn','');
			var Qcontent = $(updateQ).html();
			var Acontent = $(updateA).html();
			$(updateQ).html('<input type="text" name="help_question" value="'+Qcontent+'">');
// 			textarea 로 해도 됨
			$(updateA).html('<input type="text" name="help_answer" value="'+Acontent+'">');
			$(btnArea).html('<input type="hidden" name="help_id" value="'+updateId.replace('update_btn','')+'"><input type="submit" value="수정완료" class="btn" onclick="update_alt()">');
			
		});//2
		
		$(document).on('click','.delete_btn',function(){
			alert("글을 삭제합니다.");
 			var deleteId = $(this).attr('id');
 			var btnArea = "#btn-area"+deleteId.replace('delete_btn','');
//  			HelpDelect.hp
			$('#delete_fr').attr('action','HelpDelete.hp');
			$(btnArea).html('<input type="hidden" name="help_id" value="'+deleteId.replace('delete_btn','')+'">');
			$('#delete_fr').submit();
// 			$(btnArea).html('<input type="hidden" name="help_id" value="'+deleteId.replace('delete_btn','')+'"><input type="submit" value="삭제완료">');
			
		});//2
		
		});//1
		
function update_alt() {
	alert("글 수정 완료")
}
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
					<li class="nav-item start"><a href="UserManage.uo" class="nav-link "> 
						<span class="title">회원 관리</span> 
					</a></li>
					<li class="nav-item start"><a href="ContractList.ct" class="nav-link ">
						<span class="title">계약 관리 </span>
					</a></li>
					<li class="nav-item start"><a href="CategoryList.cg" class="nav-link">
						<span class="title">Category 관리</span>
						</a></li>
					<li class="nav-item start active"><a href="helpList.hp" class="nav-link ">
						<span class="title">Help 관리</span>
						<span class="selected"></span>
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
							<li><span>Q&A 관리</span></li>
						</ul>

					</div>
					<!-- END PAGE BAR -->
					<!-- BEGIN PAGE TITLE-->
					<h1 class="page-title">
						Q&A 관리 <small> 자주 묻는 질문 답변</small>
					</h1>
					<!-- END PAGE TITLE-->
					<!-- END PAGE HEADER-->

<!-- 여기서 부터 작업하면 됨!!!!!!!!!!!!!!!!!!!!!!!1 -->

		
		<section >
		<h2>Q&A 자주묻는질문 </h2>
		
			<section id="buttonArea">
		<input type="button" value="글쓰기" onclick="location.href='HelpWriteForm.hp'" class="btn"/>
	</section>
		<form action="HelpUpdate.hp" method="post" id="delete_fr">
		<table style="word-break:break-all">
				<colgroup>
					<col width="5%"/>
				    <col width="40%"/>
				    <col width="40%"/>
				    <col width="15%"/>
				</colgroup>
				<tr id="tr_top">
					<td>번호</td>
					<td>질문</td>
					<td>답변</td>
					<td></td>
				</tr>
				<%
				if(list != null && listCount > 0) {
				%>
				<%
				for(int i=0;i<list.size();i++){
				%>
				
				<tr>
					<td align="center"><%=list.get(i).getHelp_id() %></td>
					<td align="center"><span id="update_question<%=list.get(i).getHelp_id() %>"><%=list.get(i).getHelp_question()%></span></td>
					<td align="center"><span id="update_answer<%=list.get(i).getHelp_id() %>"><%=list.get(i).getHelp_answer() %></span></td>
					<td align="center">
					<span id="btn-area<%=list.get(i).getHelp_id() %>" class="btn-area">
					<button class="update_btn" id="update_btn<%=list.get(i).getHelp_id() %>" type="button">수정</button> |
					<button class="delete_btn" id="delete_btn<%=list.get(i).getHelp_id() %>" type="button">삭제</button>
					</span>
					</td>
				</tr>
				
				<%}%>
		</table>
		</form>	
	</section>
	
	<section style="text-align: center;">
	<%if(nowPage <= 1) {%>
		<input type="button" value="이전" class="btn" >&nbsp;
	<%} else {%>
		<input type="button" value="이전" onclick="location.href='helpList.hp?page=<%=nowPage -1 %>'" class="btn">&nbsp;
		
	<%} %>
	<%for(int i = startPage; i <= endPage; i++) {
		if(i == nowPage) {%>
			[<%=i %>]&nbsp;
			<%}else{ %>
				<a href="helpList.hp?page=<%=i %>">[<%=i %>]</a>
			<%}  %>
	<%}  %>
	
	<%if(nowPage >= maxPage){ %>
		<input type ="button" value="다음" class="btn">
	<%} else {%>
		<input type ="button" value="다음 " onclick="location.href='helpList.hp?page=<%=nowPage + 1 %>'" class="btn">
	<%} %>
	</section>
	<%
	} else {
	%>
	<section id = "emptyArea">등록된 글이 없습니다 </section>
	<%}%>

</div>
</div>
		
		
		
		



					<hr>




		</div>
	</div>
</div>


					<div class="row">

						<div class="portlet light portlet-fit bordered">



							<!-- END CONTENT -->

						</div>
						<!-- END CONTAINER -->

					</div>

	
</body>


</html>