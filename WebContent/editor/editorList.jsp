<%@page import="com.hanjum.user.vo.UserBean"%>
<%@page import="com.hanjum.vo.Constant"%>
<%@page import="com.hanjum.vo.PageInfo"%>
<%@page import="com.hanjum.board.vo.EditorBean"%>
<%@page import="java.util.ArrayList"%>
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
</head>
<body>
	<jsp:include page="../inc/top.jsp"/>
	<!-- END nav -->
	
	<section class="hero-wrap hero-wrap-2" style="background-image: url('images/bg_2.jpg');">
		<div class="overlay"></div>
		<div class="container">
			<div class="row no-gutters slider-text align-items-end justify-content-center">
				<div class="col-md-9 ftco-animate pb-5 text-center">
					<p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home <i class="fa fa-chevron-right"></i></a></span> <span>Editor <i class="fa fa-chevron-right"></i></span></p>
					<h1 class="mb-0 bread">편집자 리스트</h1>
				</div>
			</div>
		</div>
	</section>

	<section class="ftco-section bg-light">
		<div class="container">
			<div class="row">
			<%
			boolean isLogin = false;
			UserBean userBean = (UserBean)session.getAttribute("userBean");
			if(userBean != null){
				isLogin = true;
			}
			PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
			int listCount = pageInfo.getListCount();
			int nowPage = pageInfo.getPage();
			int maxPage = pageInfo.getMaxPage();
			int startPage = pageInfo.getStartPage();
			int endPage = pageInfo.getEndPage();
			int pageSize = Constant.BOARD_PAGE_SIZE;
			String pageUrl = "EditorList.bo";
			String contentUrl = "Editor.bo";
			
        if(request.getAttribute("editorList") != null){
        	ArrayList<EditorBean> editorList = (ArrayList<EditorBean>)request.getAttribute("editorList");
	        for(int i = 0; i < editorList.size(); i++){
	        	EditorBean editorBean = editorList.get(i);
	        	int score = editorBean.getUser_score();
	        %>
				<div id="edItem-<%=i %>" data-href="Editor.bo?board_id=<%=editorBean.getBoard_id()%>&page=<%=nowPage %>" class="col-md-6 col-lg-3 ftco-animate d-flex align-items-stretch" style="cursor: pointer;" onclick="forwardEd('<%=i %>','<%=isLogin %>')">
					<div class="staff">
						<div class="img-wrap d-flex align-items-stretch">
							<div class="img align-self-stretch" style="width:255px; background-size:255px; background-image: url(editorUserPhotoUpload/<%=editorBean.getBoard_ed_photo()%>);"></div>
						</div>
						<div class="text pt-3">
							<h3 style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap;"><%=editorBean.getBoard_subject() %></h3>
							<span class="position mb-2"><span class="userLv" style="font-weight:bold;
	                <%
	                if(editorBean.getUser_level() >= 0 && editorBean.getUser_level() < 20){
	                	%>color:#cc00cc<%
	                } else if(editorBean.getUser_level() >= 20 && editorBean.getUser_level() < 40){
	                	%>color:#000066<%
	                } else if(editorBean.getUser_level() >= 40 && editorBean.getUser_level() < 50){
	                	%>color:#6666ff<%
	                } else if(editorBean.getUser_level() >= 50 && editorBean.getUser_level() < 60){
	                	%>color:#66cc66<%
	                } else if(editorBean.getUser_level() >= 60 && editorBean.getUser_level() < 70){
	                	%>color:#e6e600<%
	                } else if(editorBean.getUser_level() >= 70 && editorBean.getUser_level() < 80){
	                	%>color:#ffcc00<%
	                } else if(editorBean.getUser_level() >= 80 && editorBean.getUser_level() < 90){
	                	%>color:#e62e00<%
	                } else if(editorBean.getUser_level() >= 90){
	                	%>background-image: linear-gradient(to left, violet, indigo, blue, green, yellow, orange, red); -webkit-background-clip: text; color: transparent;<%
	                } else {
	                	%>color:#eeeeee<%
	                }
	                %>
	                
	                ;">Lv <%=editorBean.getUser_level()%></span> <%=editorBean.getUser_name() %></span>
							<div class="faded">
								<p style="height: 80px; text-overflow: ellipsis;"><%=editorBean.getBoard_content() %></p>
								<p class="star">
				                    <%
			for(int j = 0; j < 5; j++){
				if(score-2<0){ // 점수에서 2점을 뺄때 0보다 낮으면 판별시작 그외에는 1개 출력후 2빼기
					if(score-1<0){ // 반개도 못채울경우 0개 출력
					%>
	            <span class="fa fa-star-o"></span> 
					<%
					} else { // 1점이라도 남아있으면 반개 출력후 1 빼기
					score -= 1;
					%>	
				<span class="fa fa-star-half-o"></span>
					<%
					}
				} else { // 2점 남아있으면 1개 출력후 2 빼기
					%>
				<span class="fa fa-star"></span>	
					<%
					score -= 2;
				}
			}
			%>
                				</p>
							</div>
						</div>
					</div>
				</div>
			<%
	        }
       }
        %>
			</div>
			<div class="row mt-5">
						<div class="col">
							<div class="block-27">
								<ul>
								<%if(startPage > pageSize){ %>
									<li><a href="<%=pageUrl%>?page=<%=startPage-pageSize%>">&lt;</a></li>
									<% 
										}
									for(int i=startPage; i<=endPage; i++){
										if(i == nowPage){
											%>
									<li class="active"><span><%=i %></span></li>
									<% } else { %>
									<li><a href="<%=pageUrl%>?page=<%=i%>"><%=i %></a></li>
									<% } 
									}
									if(endPage < maxPage){ 
									%>
									<li><a href="<%=pageUrl%>?page=<%=startPage+pageSize %>">&gt;</a></li>
									<%
									}
									%>
								</ul>
							</div>
						</div>
					</div>
		</div>
	</section>

	<jsp:include page="../inc/bottom.jsp"/>
		

		<!-- loader -->
		<div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


		<script src="js/jquery.min.js"></script>
		<script src="js/jquery-migrate-3.0.1.min.js"></script>
		<script src="js/popper.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/jquery.easing.1.3.js"></script>
		<script src="js/jquery.waypoints.min.js"></script>
		<script src="js/jquery.stellar.min.js"></script>
		<script src="js/owl.carousel.min.js"></script>
		<script src="js/jquery.magnific-popup.min.js"></script>
		<script src="js/jquery.animateNumber.min.js"></script>
		<script src="js/bootstrap-datepicker.js"></script>
		<script src="js/scrollax.min.js"></script>
		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
		<script src="js/google-map.js"></script>
		<script src="js/main.js"></script>
		<script type="text/javascript">
		function forwardEd(item, login){
			var url = $("#edItem-"+item).attr("data-href");
			if(login == "true"){
				location.href = url;
			} else {
				var loginMsg = confirm("로그인이 필요한 페이지 입니다 로그인 하시겠습니까?");
				if(loginMsg){
					location.href = "My.uo?fr=login";
				}
			}
		}
			</script>
	</body>
	</html>