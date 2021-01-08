<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<!--<![endif]-->
<!-- BEGIN HEAD -->

<head>
<meta charset="utf-8" />
<title>admin</title>
<link href="css/help_write.css" rel="stylesheet" type="text/css" />
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link href="css/themes.css" rel="stylesheet" type="text/css"
	id="style_color" />
<!-- <link href="css/manager.css" rel="stylesheet" type="text/css" /> -->

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
						<li class="sidebar-toggler-wrapper hide">
							<div class="sidebar-toggler">
								<span></span>
							</div>
						</li>
						<!-- END SIDEBAR TOGGLER BUTTON -->
						<!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
						<li class="sidebar-search-wrapper">
							<!-- BEGIN RESPONSIVE QUICK SEARCH FORM --> <!-- DOC: Apply "sidebar-search-bordered" class the below search form to have bordered search box -->
							<!-- DOC: Apply "sidebar-search-bordered sidebar-search-solid" class the below search form to have bordered & solid search box -->

							<!-- END RESPONSIVE QUICK SEARCH FORM -->
						</li>
						<li class="nav-item start active open"><a href="javascript:;"
							class="nav-link nav-toggle"> <span class="title">관리자
									페이지</span> <span class="selected"></span>
						</a>
							<ul class="sub-menu">
								<li class="nav-item start"><a href="UserManage.uo"
									class="nav-link "> <span class="title">회원 관리</span> 
								</a></li>
								<li class="nav-item start "><a href="ContractList.ct" class="nav-link ">
										<span class="title">계약 관리 </span>
								</a></li>
								<li class="nav-item start "><a href="CategoryList.cg" class="nav-link ">
										<span class="title">Category 관리</span>
								</a></li>
								<li class="nav-item start "><a href="helpList.hp" class="nav-link ">
										<span class="title">Help 관리</span>
																			<span class="selected"></span>
										
								</a></li>
							</ul>
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

	<!-- 게시판 등록 -->
	<section id="writeForm">
		<h1>Q & A 질문  : 답변</h1>
		<form action="HelpWritePro.hp" method="post" name="boardForm">
			<table>
				<tr>
					<td class="td_left"><label for="help_question">질문</label></td>
					<td class="td_right"><input type="text" name="help_question" id="help_question" required="required"/></td>
				</tr>
				<tr>
					<td class="td_left"><label for="help_answer">답변</label></td>
					<td class="td_right">
						<textarea id="help_answer" name="help_answer" required="required" style="resize: none;"></textarea>
					</td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="등록" class="btn">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기" class="btn">
			</section>
		</form>
	</section>
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