<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String frUrl = "";
    String fr = "";
    String pageSubject = "";
    String search = request.getParameter("search");
    frUrl = "CenterHelp.hp";
    fr = "help";
    pageSubject = "자주 묻는 질문";
    if(request.getParameter("fr") != null){
    	fr = request.getParameter("fr");
    	switch(fr){
    	case "help": frUrl="CenterHelp.hp"; pageSubject="자주 묻는 질문"; break;
    	case "report": frUrl="CenterReport.hp"; pageSubject="유저 신고"; break;
    	case "searchHelp": frUrl="CenterSearchHelp.hp?search="+search; pageSubject="자주 묻는 질문 검색"; break;
    	}
    }
    String prefPage = "";
	if(request.getHeader("referer") != null){
		String prefStr = request.getHeader("referer");
		prefPage = prefStr.substring(prefStr.lastIndexOf("/")+1);
	}
    %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>한줌에디터</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

	<link rel="stylesheet" href="css/animate.css">
	
	<link rel="stylesheet" href="css/owl.carousel.min.css">
	<link rel="stylesheet" href="css/owl.theme.default.min.css">
	<link rel="stylesheet" href="css/magnific-popup.css">

	<link rel="stylesheet" href="css/bootstrap-datepicker.css">
	<link rel="stylesheet" href="css/jquery.timepicker.css">

	
	<link rel="stylesheet" href="css/flaticon.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="../inc/top.jsp"/>
	<section class="hero-wrap hero-wrap-2" style="background-image: url('images/bg_2.jpg');">
		<div class="overlay"></div>
		<div class="container">
			<div class="row no-gutters slider-text align-items-end justify-content-center">
				<div class="col-md-9 ftco-animate pb-5 text-center">
					<p class="breadcrumbs"><span class="mr-2"><a href="home">Home <i class="fa fa-chevron-right"></i></a></span> <span>Center <i class="fa fa-chevron-right"></i></span></p>
					<h1 class="mb-0 bread"><%=pageSubject %></h1>
				</div>
			</div>
		</div>
	</section>
	
	<section class="ftco-section bg-light">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 sidebar">
				<form name="search_form" action="Center.hp?fr=searchHelp" method="POST">
					<div class="sidebar-box bg-white ftco-animate">
							<div class="form-group">
								<span class="icon fa fa-search" onclick="document.search_form.submit()"></span>
								<input type="text" class="form-control" name="search" placeholder="검색어를 입력해주세요." >
							</div>
					</div>
					</form>
				<div class="sidebar-box bg-white ftco-animate nav-tabs">
				<h3 class="heading-sidebar">메뉴</h3>
				<div class="col-md-9 d-flex align-items-stretch ftco-animate member_menu" id="page_help"><a href="Center.hp?fr=help" class="member-menu nav-link <%if(fr.equals("help")){%>active<%}%>">자주묻는질문</a></div>
				<div class="col-md-9 d-flex align-items-stretch ftco-animate member_menu" id="page_report"><a href="Center.hp?fr=report" class="member-menu nav-link <%if(fr.equals("report")){%>active<%}%>">신고관리</a></div>
				</div>
				</div>
				
				<div class="col-md-9 d-flex align-items-stretch ftco-animate" id="help-container">
<!-- 					컨텐츠 위치 -->
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="../inc/bottom.jsp"/>
			<!-- loader -->
	<div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>
	<jsp:include page="../inc/script.jsp"/>
	<script type="text/javascript">
			$(document).ready(function(){
				var url = "<%=frUrl%>";
				if(url.indexOf("Login.uo") != -1){
					$("#help-container").load(url+"?prefPage=<%=prefPage%>");
				} else {
					$("#help-container").load(url);
				}
				
				$(document).on('click','.page-move',function(){
					var href = $(this).attr("data-href");
					$("#help-container").load(href);

				});
			});
			</script>			
</body>
</html>