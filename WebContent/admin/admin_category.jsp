<%@page import="java.util.ArrayList"%>
<%@page import="com.hanjum.category.vo.CategoryBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<CategoryBean> categoryList = (ArrayList<CategoryBean>) request.getAttribute("categoryList");
%>
<!DOCTYPE html>

<html>
<!--<![endif]-->
<!-- BEGIN HEAD -->

<head>
<meta charset="utf-8" />
<title>admin</title>
<link href="css/category.css?ver=1" rel="stylesheet" type="text/css" />
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link href="css/themes.css" rel="stylesheet" type="text/css"
	id="style_color" />

<!-- END THEME LAYOUT STYLES -->
<link rel="shortcut icon" href="favicon.ico" />
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
<script type="text/javascript">
	function add_txt() {
		document.getElementById("add_text").innerHTML = "<form action ='CategoryWritePro.cg' method='post'>"
				+ "<input type='text' name='category_content' id='category_add'>"
				+ "<input type='submit' value='추가' class='category_btn'><br>"
				+ "</form>"
	}
	function insert() {
		location.href = "CategoryWritePro.cg";
	}
</script>


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
					<li class="nav-item start active"><a href="CategoryList.cg" class="nav-link">
						<span class="title">Category 관리</span>
						<span class="selected"></span>
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
						<li><span>카테고리 관리</span></li>
					</ul>

				</div>
				<!-- END PAGE BAR -->
				<!-- BEGIN PAGE TITLE-->
				<h1 class="page-title">카테고리 관리</h1>
				<!-- END PAGE TITLE-->
				<!-- END PAGE HEADER-->

				<!-- 여기서 부터 작업하면 됨!!!!!!!!!!!!!!!!!!!!!!!1 -->


				<div class="category_list">
					<%
						for (int i = 0; i < categoryList.size(); i++) {
						CategoryBean categoryBean = categoryList.get(i);
						int category_id = categoryBean.getCategory_id();
						String category_content = categoryBean.getCategory_content();
					%>

					<form method="post">
						<table class="table1">
							<tr>
								<td><input type="text" value="<%=category_content%>"
									id="category_txt" class="category_txt" name="category_content"
									onclick="this.value == this.defaultValue;"
									onblur="if(this.value != this.defaultValue) this.value = this.defaultValue;"></td>
								<td><input type="hidden" name="category_id"
									value="<%=category_id%>"></td>
								<td><input type="submit" value='수정'
									formaction="CategoryUpdate.cg" name="fix" class="category_btn"></td>
								<td><input type='submit' value='삭제'
									formaction="CategoryDelete.cg" name="delete"
									class="category_btn"></td>
							</tr>
						</table>
					</form>
					<%
						}
					%>
				<hr>
					<input type="button" value="새 카테고리 추가" onclick="add_txt()"
						class="category_btn">
					<div id="add_text"></div>
				</div>




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