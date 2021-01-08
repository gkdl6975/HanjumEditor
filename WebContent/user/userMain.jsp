<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String frUrl = "";
    String fr = "";
    String pageSubject = "";
    
    
    if(session.getAttribute("userBean") != null){
    	frUrl = "UserMyInfo.uo";
    	fr = "info";
    	pageSubject = "내 정보";
    	if(request.getParameter("fr") != null){
        	fr = request.getParameter("fr");
        	switch(fr){
        	case "notice": frUrl="getNoticeList.nt"; pageSubject="내 알림"; break;
        	case "chat": frUrl="ChatListPro.ch"; pageSubject="채팅"; break;
        	case "info": frUrl="UserMyInfo.uo"; pageSubject="내 정보"; break;
        	case "changePass": frUrl="ChangePass.uo"; pageSubject="내 정보"; break;
        	case "constract": frUrl="UserConstract.uo"; pageSubject="계약 관리"; break;
        	case "leave": frUrl="UserDeleteForm.uo"; pageSubject="회원 탈퇴"; break;
        	}
        }
    } else {
    	frUrl = "Login.uo";
    	fr = "login";
    	pageSubject = "로그인";
    	if(request.getParameter("fr") != null){
        	fr = request.getParameter("fr");
        	switch(fr){
        	case "login": frUrl="Login.uo"; pageSubject="로그인"; break;
        	case "pass": frUrl="LoginPass.uo"; pageSubject="암호찾기"; break;
        	case "join": frUrl="Join.uo"; pageSubject="회원등록"; break;
        	case "joinEditor": frUrl="JoinEditor.uo"; pageSubject="편집자등록"; break;
        	}
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
	<link rel="stylesheet" href="css/member.css">
	<link rel="stylesheet" href="css/util.css">
	<style type="text/css">
		.form-group-content:hover > .edit {display: inline-block;}
	</style>
	
</head>
<body>
	<jsp:include page="../inc/top.jsp"/>
	<section class="hero-wrap hero-wrap-2" style="background-image: url('images/bg_2.jpg');">
		<div class="overlay"></div>
		<div class="container">
			<div class="row no-gutters slider-text align-items-end justify-content-center">
				<div class="col-md-9 ftco-animate pb-5 text-center">
					<p class="breadcrumbs"><span class="mr-2"><a href="home">Home <i class="fa fa-chevron-right"></i></a></span> <span>My <i class="fa fa-chevron-right"></i></span></p>
					<h1 class="mb-0 bread"><%=pageSubject %></h1>
				</div>
			</div>
		</div>
	</section>
	
	<section class="ftco-section bg-light">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 sidebar">
				<div class="sidebar-box bg-white ftco-animate nav-tabs">
				<h3 class="heading-sidebar">메뉴</h3>
				<%
				if(session.getAttribute("userBean")==null){
				%>
				<div class="col-md-9 d-flex align-items-stretch ftco-animate member_menu" id="form_login"><a href="My.uo?fr=login" class="member-menu nav-link <%if(fr.equals("login")){%>active<%}%>">로그인</a></div>
				<div class="col-md-9 d-flex align-items-stretch ftco-animate member_menu" id="form_pass"><a href="My.uo?fr=pass" class="member-menu nav-link <%if(fr.equals("pass")){%>active<%}%>">암호찾기</a></div>
				<div class="col-md-9 d-flex align-items-stretch ftco-animate member_menu" id="form_join_user"><a href="My.uo?fr=join" class="member-menu nav-link <%if(fr.equals("join")){%>active<%}%>">회원등록</a></div>
				<div class="col-md-9 d-flex align-items-stretch ftco-animate member_menu" id="form_join_editor"><a href="My.uo?fr=joinEditor" class="member-menu nav-link <%if(fr.equals("joinEditor")){%>active<%}%>">편집자등록</a></div>
				<%
				} else {
				%>
				<div class="col-md-9 d-flex align-items-stretch ftco-animate member_menu" id="form_user_notice"><a href="My.uo?fr=notice" class="member-menu nav-link <%if(fr.equals("notice")){%>active<%}%>">내 알림</a></div>
				<div class="col-md-9 d-flex align-items-stretch ftco-animate member_menu" id="form_user_info"><a href="My.uo?fr=info" class="member-menu nav-link <%if(fr.equals("info")){%>active<%}%>">내 정보</a></div>
				<div class="col-md-9 d-flex align-items-stretch ftco-animate member_menu" id="form_user_changePass"><a href="My.uo?fr=changePass" class="member-menu nav-link <%if(fr.equals("changePass")){%>active<%}%>">암호 변경</a></div>
				<div class="col-md-9 d-flex align-items-stretch ftco-animate member_menu" id="form_user_constrat"><a href="My.uo?fr=constract" class="member-menu nav-link <%if(fr.equals("constract")){%>active<%}%>">계약 관리</a></div>
				<div class="col-md-9 d-flex align-items-stretch ftco-animate member_menu" id="form_user_chat"><a href="My.uo?fr=chat" class="member-menu nav-link <%if(fr.equals("chat")){%>active<%}%>">채팅</a></div>
				<div class="col-md-9 d-flex align-items-stretch ftco-animate member_menu" id="form_join_user"><a href="Logout.uo" class="member-menu nav-link">로그아웃</a></div>
				<div class="col-md-9 d-flex align-items-stretch ftco-animate member_menu" id="form_user_leave"><a href="My.uo?fr=leave" class="member-menu nav-link <%if(fr.equals("leave")){%>active<%}%>" style="color:#FF0000;">회원탈퇴</a></div>
				<%
				}
				%>
				</div>
				</div>
				
				<div class="col-md-9 d-flex align-items-stretch ftco-animate" id="member-container">
					
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
					$("#member-container").load(url+"?prefPage=<%=prefPage%>");
				} else {
					$("#member-container").load(url);
				}
			});
			</script>
			<script src="js/userInfo.js"></script>
		</body>
</html>