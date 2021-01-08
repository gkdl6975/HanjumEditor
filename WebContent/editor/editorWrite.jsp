<%@page import="com.hanjum.user.vo.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
	<link rel="stylesheet" href="css/util.css">
	
	
	<style>
		.check_list { display: inline-block; padding:10px;}
		.check_list span { margin-left: 10px;}
	</style>
	<%
	String user_id = "";
	if(session.getAttribute("userBean")==null){
		response.sendRedirect("My.uo?fr=login");
	} else {
		UserBean userBean = (UserBean)session.getAttribute("userBean");
		user_id = userBean.getUser_id();
	}
	%>
	
</head>
<body>
	<jsp:include page="../inc/top.jsp"/>
	<section class="hero-wrap hero-wrap-2" style="background-image: url('images/bg_2.jpg');">
		<div class="overlay"></div>
		<div class="container">
			<div class="row no-gutters slider-text align-items-end justify-content-center">
				<div class="col-md-9 ftco-animate pb-5 text-center">
					<p class="breadcrumbs"><span class="mr-2"><a href="home">Home <i class="fa fa-chevron-right"></i></a></span> <span>Project <i class="fa fa-chevron-right"></i></span></p>
					<h1 id="pageTitle" class="mb-0 bread">포트폴리오 등록</h1>
				</div>
			</div>
		</div>
	</section>
	
	<section id="pageContent" class="ftco-section bg-light">
	<div class="login-wrap p-4 p-md-5" style="margin:0px auto;; max-width : 800px;">
		<h3 class="mb-4">포트폴리오 등록</h3>
		<form action="EditorWritePro.bo" method="post" name="fr_write" id="WriteForm">
		<input type="hidden" name="user_id" value="<%=user_id%>">
		<div class="form-group">
        	<label class="label" for="Subject">포트폴리오 제목</label>
            <input type="text" id="Subject" class="form-control" name="board_subject" required="required"/>
        </div>
        <div class="form-group d-flex justify-content-end mt-4">
        <input class="btn btn-primary submit" type="submit" id="WriteSubmit" value="등록하기">
        </div>
		</form>
	</div>
	</section>
	<jsp:include page="../inc/bottom.jsp"/>
			<!-- loader -->
<div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>
<jsp:include page="../inc/script.jsp"/>
	
</body>
</html>