<%@page import="com.hanjum.board.vo.EditorBean"%>
<%@page import="com.hanjum.board.vo.ProjectBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
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
	<link rel="stylesheet" href="css/util.css">
	
	</head>
<body>
	<jsp:include page="../inc/top.jsp"/>
    <section class="hero-wrap hero-wrap-2" style="background-image: url('images/bg_2.jpg');">
		<div class="overlay"></div>
		<div class="container">
			<div class="row no-gutters slider-text align-items-end justify-content-center">
				<div class="col-md-9 ftco-animate pb-5 text-center">
					<p class="breadcrumbs"><span class="mr-2"><a href="home">Home <i class="fa fa-chevron-right"></i></a></span> <span>Editor <i class="fa fa-chevron-right"></i></span></p>
					<h1 id="pageTitle" class="mb-0 bread">에디터 수정</h1>
				</div>
			</div>
		</div>
	</section>
	
	<%
EditorBean editor = (EditorBean)request.getAttribute("editor");
String nowPageStr = (String)request.getParameter("page");
int nowPage = Integer.parseInt(nowPageStr);
if(editor != null){

%>
<section id="pageContent" class="ftco-section bg-light">
<div class="login-wrap p-4 p-md-5" style="margin:0px auto; max-width : 800px;">
	<h3 class="mb-4">에디터 수정</h3>
	<form action="EditorUpdatePro.bo" method="post" name="fr_write" id="UpdateForm">
		<input type="hidden" name="board_id" value="<%=editor.getBoard_id()%>">
		<input type="hidden" name="page" value="<%=nowPage%>">
		<div class="form-group">
        	<label class="label has-focus" for="Subject">이력서 제목</label>
            <input type="text" id="Subject" class="form-control" name="board_subject" required="required" value="<%=editor.getBoard_subject()%>"/>
        </div>
		<div class="form-group">
        	<label class="label has-focus" for="Content">이력서 소개</label>
            <input type="text" id="Content" class="form-control" name="board_content" value="<%=editor.getBoard_content()%>" required="required"/>
        </div>
		<div class="form-group">
            <label  class="label has-focus" for="DetailContent">편집자 소개</label>
            <div class="form-control" style="height: auto;">
                <div style="padding-top:10px;"></div>
                	<textarea id="DetailContent" name="board_ed_content_detail" style="padding: 20px; width:100%; min-width:260px; "><%=editor.getBoard_ed_content_detail()%></textarea>
            </div>
        </div>
        <div class="form-group d-flex justify-content-end mt-4">
        <input class="btn btn-primary submit" type="submit" id="WriteSubmit" name="update-submit" value="수정하기">
        </div>
	</form>
</div>
</section>
<%
} else {
	%>
	<script>
		alert("잘못된 접근입니다.");
		history.back();
	</script>
	<%
}
%>	

<jsp:include page="../inc/bottom.jsp"/>
			
			<!-- loader -->
<div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>
			
<jsp:include page="../inc/script.jsp"/>
<script type="text/javascript" src="js/smartediter.js"></script>
<script type="text/javascript" src="plugin/js/service/HuskyEZCreator.js" charset="utf-8"></script>
</body>
</html>
