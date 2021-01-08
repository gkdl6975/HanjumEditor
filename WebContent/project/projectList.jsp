<%@page import="com.hanjum.user.vo.UserBean"%>
<%@page import="com.hanjum.vo.Constant"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.hanjum.vo.PageInfo"%>
<%@page import="com.hanjum.board.vo.ProjectBean"%>
<%@page import="java.util.ArrayList"%>
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
	
	
	
</head>
<body>
	<jsp:include page="../inc/top.jsp"/>
	<!-- END nav -->
	
	<section class="hero-wrap hero-wrap-2" style="background-image: url('images/bg_2.jpg');">
		<div class="overlay"></div>
		<div class="container">
			<div class="row no-gutters slider-text align-items-end justify-content-center">
				<div class="col-md-9 ftco-animate pb-5 text-center">
					<p class="breadcrumbs"><span class="mr-2"><a href="home">Home <i class="fa fa-chevron-right"></i></a></span> <span>Project <i class="fa fa-chevron-right"></i></span></p>
					<h1 id="pageTitle" class="mb-0 bread">프로젝트 리스트</h1>
				</div>
			</div>
		</div>
	</section>

	<section id="pageContent" class="ftco-section bg-light">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 sidebar">
					<%
					boolean isLogin = false;
					UserBean userBean = (UserBean)session.getAttribute("userBean");
					if(userBean != null){
						isLogin = true;
					}
					HashMap<String, String> search = null;
					String genres = "", price_n = "", price_x = "", keyword = "", recordings = "", 
							camnum = "", clipnum = "", oriLength = "", editLength = "", transfer = "";
					if(request.getAttribute("search") != null){
						search = (HashMap<String, String>)request.getAttribute("search");
						if(search.containsKey("keyword")) { keyword=search.get("keyword"); }
						if(search.containsKey("genre")) { genres=search.get("genre"); }
						if(search.containsKey("price_n")) { price_n=search.get("price_n"); }
						if(search.containsKey("price_x")) { price_x=search.get("price_x"); }
						if(search.containsKey("recording")) { recordings=search.get("recording"); }
						if(search.containsKey("camnum")) { camnum=search.get("camnum"); }
						if(search.containsKey("clipnum")) { clipnum=search.get("clipnum"); }
						if(search.containsKey("oriLength")) { oriLength=search.get("oriLength"); }
						if(search.containsKey("editLength")) { editLength=search.get("editLength"); }
						if(search.containsKey("transfer")) { transfer=search.get("transfer"); }
					}
					%>
					<a href="ProjectWrite.bo" id="write-btn" class="btn btn-primary sidebar-box ftco-animate layer-btn">프로젝트 작성</a>
					<form name="search_form" action="ProjectListSearch.bo" method="POST">
					<div class="sidebar-box bg-white ftco-animate">
							<div class="form-group">
								<span class="icon fa fa-search" onclick="document.search_form.submit()"></span>
								<input type="text" class="form-control" name="keyword" placeholder="Search..." value="<%=keyword%>">
							</div>
					</div>
					
					<div class="sidebar-box bg-white p-4 ftco-animate">
						<h3 class="heading-sidebar">장르</h3>
							<label for="option-genre-1"><input type="checkbox" id="option-genre-1" name="board_creator_genre" value="1" <%if(genres.contains("1")){ %>checked<% } %>> 유튜브</label><br>
							<label for="option-genre-2"><input type="checkbox" id="option-genre-2" name="board_creator_genre" value="2" <%if(genres.contains("2")){ %>checked<% } %>> 홍보</label><br>
							<label for="option-genre-3"><input type="checkbox" id="option-genre-3" name="board_creator_genre" value="3" <%if(genres.contains("3")){ %>checked<% } %>> 광고</label><br>
							<label for="option-genre-4"><input type="checkbox" id="option-genre-4" name="board_creator_genre" value="4" <%if(genres.contains("4")){ %>checked<% } %>> 뮤직비디오</label><br>
							<label for="option-genre-5"><input type="checkbox" id="option-genre-5" name="board_creator_genre" value="5" <%if(genres.contains("5")){ %>checked<% } %>> 드라마</label><br>
							<label for="option-genre-6"><input type="checkbox" id="option-genre-6" name="board_creator_genre" value="6" <%if(genres.contains("6")){ %>checked<% } %>> 모션그래픽</label><br>
							<label for="option-genre-7"><input type="checkbox" id="option-genre-7" name="board_creator_genre" value="7" <%if(genres.contains("7")){ %>checked<% } %>> 기타</label><br>
					</div>

					<div class="sidebar-box bg-white p-4 ftco-animate">
						<h3 class="heading-sidebar">동시 녹음 유무</h3>
							<label for="option-recording-1"><input type="checkbox" id="option-recording-1" name="board_creator_recording" value="1" <%if(recordings.contains("1")){ %>checked<% } %>> 싱크 작업 필요</label><br>
							<label for="option-recording-2"><input type="checkbox" id="option-recording-2" name="board_creator_recording" value="2" <%if(recordings.contains("2")){ %>checked<% } %>> 부분적 필요</label><br>
							<label for="option-recording-3"><input type="checkbox" id="option-recording-3" name="board_creator_recording" value="3" <%if(recordings.contains("3")){ %>checked<% } %>> 원본 녹음 사용</label><br>
					</div>

					<div class="sidebar-box bg-white p-4 ftco-animate">
						<h3 class="heading-sidebar">녹화에 이용된 캠</h3>
							<label for="option-cam-num-1"><input type="checkbox" id="option-cam-num-1" name="board_creator_cam_num" value="1" <%if(camnum.contains("1")){ %>checked<% } %>> 1캠</label><br>
							<label for="option-cam-num-2"><input type="checkbox" id="option-cam-num-2" name="board_creator_cam_num" value="2" <%if(camnum.contains("2")){ %>checked<% } %>> 2캠</label><br>
							<label for="option-cam-num-3"><input type="checkbox" id="option-cam-num-3" name="board_creator_cam_num" value="3" <%if(camnum.contains("3")){ %>checked<% } %>> 3캠</label><br>
							<label for="option-cam-num-4"><input type="checkbox" id="option-cam-num-4" name="board_creator_cam_num" value="4" <%if(camnum.contains("4")){ %>checked<% } %>> 4캠 이상</label><br>
					</div>

					<div class="sidebar-box bg-white p-4 ftco-animate">
						<h3 class="heading-sidebar">원본클립</h3>
							<label for="option-ori_clip_num-1"><input type="checkbox" id="option-ori_clip_num-1" name="board_creator_ori_clip_num" value="1" <%if(clipnum.contains("1")){ %>checked<% } %>> 5개 이하</label><br>
							<label for="option-ori_clip_num-2"><input type="checkbox" id="option-ori_clip_num-2" name="board_creator_ori_clip_num" value="2" <%if(clipnum.contains("2")){ %>checked<% } %>> 5개 ~ 20개</label><br>
							<label for="option-ori_clip_num-3"><input type="checkbox" id="option-ori_clip_num-3" name="board_creator_ori_clip_num" value="3" <%if(clipnum.contains("3")){ %>checked<% } %>> 20 ~ 50개</label><br>
							<label for="option-ori_clip_num-4"><input type="checkbox" id="option-ori_clip_num-4" name="board_creator_ori_clip_num" value="4" <%if(clipnum.contains("4")){ %>checked<% } %>> 50개 이상</label><br>
					</div>
					
					<div class="sidebar-box bg-white p-4 ftco-animate">
						<h3 class="heading-sidebar">편집 전 런타임</h3>
							<label for="option-ori_length-1"><input type="checkbox" id="option-ori_length-1" name="board_creator_ori_length" value="1" <%if(oriLength.contains("1")){ %>checked<% } %>> 10분 이하</label><br>
							<label for="option-ori_length-2"><input type="checkbox" id="option-ori_length-2" name="board_creator_ori_length" value="2" <%if(oriLength.contains("2")){ %>checked<% } %>> 30분 이하</label><br>
							<label for="option-ori_length-3"><input type="checkbox" id="option-ori_length-3" name="board_creator_ori_length" value="3" <%if(oriLength.contains("3")){ %>checked<% } %>> 1시간 이하</label><br>
							<label for="option-ori_length-4"><input type="checkbox" id="option-ori_length-4" name="board_creator_ori_length" value="4" <%if(oriLength.contains("4")){ %>checked<% } %>> 5시간 이하</label><br>
							<label for="option-ori_length-5"><input type="checkbox" id="option-ori_length-5" name="board_creator_ori_length" value="5" <%if(oriLength.contains("5")){ %>checked<% } %>> 5시간 이상</label><br>
							<label for="option-ori_length-6"><input type="checkbox" id="option-ori_length-6" name="board_creator_ori_length" value="6" <%if(oriLength.contains("6")){ %>checked<% } %>> 정확히 알 수 없음</label><br>
					</div>
					
					<div class="sidebar-box bg-white p-4 ftco-animate">
						<h3 class="heading-sidebar">편집 후 런타임</h3>
							<label for="option-edit_length-1"><input type="checkbox" id="option-edit_length-1" name="board_creator_edit_length" value="1" <%if(editLength.contains("1")){ %>checked<% } %>> 5분 이하</label><br>
							<label for="option-edit_length-2"><input type="checkbox" id="option-edit_length-2" name="board_creator_edit_length" value="2" <%if(editLength.contains("2")){ %>checked<% } %>> 10분 이하</label><br>
							<label for="option-edit_length-3"><input type="checkbox" id="option-edit_length-3" name="board_creator_edit_length" value="3" <%if(editLength.contains("3")){ %>checked<% } %>> 30분 이하</label><br>
							<label for="option-edit_length-4"><input type="checkbox" id="option-edit_length-4" name="board_creator_edit_length" value="4" <%if(editLength.contains("4")){ %>checked<% } %>> 1시간 이하</label><br>
							<label for="option-edit_length-5"><input type="checkbox" id="option-edit_length-5" name="board_creator_edit_length" value="5" <%if(editLength.contains("5")){ %>checked<% } %>> 1시간 이상</label><br>
					</div>
					
					<div class="sidebar-box bg-white p-4 ftco-animate">
						<h3 class="heading-sidebar">파일의 전달방식</h3>
							<label for="option-ori_transfer-1"><input type="checkbox" id="option-ori_transfer-1" name="board_creator_ori_transfer" value="1" <%if(transfer.contains("1")){ %>checked<% } %>> 이메일</label><br>
							<label for="option-ori_transfer-2"><input type="checkbox" id="option-ori_transfer-2" name="board_creator_ori_transfer" value="2" <%if(transfer.contains("2")){ %>checked<% } %>> 웹하드</label><br>
							<label for="option-ori_transfer-3"><input type="checkbox" id="option-ori_transfer-3" name="board_creator_ori_transfer" value="3" <%if(transfer.contains("3")){ %>checked<% } %>> NAS</label><br>
							<label for="option-ori_transfer-4"><input type="checkbox" id="option-ori_transfer-4" name="board_creator_ori_transfer" value="4" <%if(transfer.contains("4")){ %>checked<% } %>> SMS</label><br>
							<label for="option-ori_transfer-5"><input type="checkbox" id="option-ori_transfer-5" name="board_creator_ori_transfer" value="5" <%if(transfer.contains("5")){ %>checked<% } %>> 직접전달</label><br>
					</div>
					
					<div class="sidebar-box bg-white p-4 ftco-animate">
						<h3 class="heading-sidebar">단가</h3>
							<div class="input_price"><input class="defaultTextInput" type="number" id="MinPrice" step="1000" min="0" name="board_creator_cre_min_price" placeholder="최소단가" value="<%=price_n%>"> ~<br><br> 
							<input class="defaultTextInput" type="number" id="MaxPrice" step="1000" min="1000" name="board_creator_cre_max_price" placeholder="최대단가" value="<%=price_x%>"> 원</div>
					</div>
					
				</form>
				</div>
				<div id="list-container" class="col-lg-9">
				<div class="row">
						<%
					    
							ArrayList<ProjectBean> projectList = (ArrayList<ProjectBean>)request.getAttribute("projectList");
							PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
							int listCount = pageInfo.getListCount();
							int nowPage = pageInfo.getPage();
							int maxPage = pageInfo.getMaxPage();
							int startPage = pageInfo.getStartPage();
							int endPage = pageInfo.getEndPage();
							int pageSize = Constant.BOARD_PAGE_SIZE;
							String pageUrl = "ProjectList.bo";
							String contentUrl = "Project.bo";
							
						if(projectList != null && listCount > 0){
							String genre = "";
							String recording = "";
							String ori_clip = "";
							String ori_length = "";
							String edit_length = "";
							String ori_transfer	= "";
							String min_price = "";
							String max_price = "";
							for(int i= 0; i < projectList.size(); i++){
								ProjectBean project = projectList.get(i);
								SimpleDateFormat datef = new SimpleDateFormat("yyyy년 MM월 dd일");
								String board_date = datef.format(projectList.get(i).getBoard_date());
								genre = project.getBoard_creator_genre()
										.replace("1", "유튜브")
										.replace("2", "홍보")
										.replace("3", "광고")
										.replace("4", "뮤직비디오")
										.replace("5", "드라마")
										.replace("6", "모션그래픽")
										.replace("7", "기타");
								switch(project.getBoard_creator_recording()){
								case 1: recording = "싱크 작업 필요"; break;
								case 2: recording = "부분적 필요"; break;
								case 3: recording = "원본 녹음 사용"; break;
								}
								switch(project.getBoard_creator_ori_clip_num()){
								case 1: ori_clip = "5개 이하"; break;
								case 2: ori_clip = "5개 ~ 20개"; break;
								case 3: ori_clip = "20개 ~ 50개"; break;
								case 4: ori_clip = "50개 이상"; break;
								}
								switch(project.getBoard_creator_ori_length()){
								case 1: ori_length = "10분 이하"; break;
								case 2: ori_length = "30분 이하"; break;
								case 3: ori_length = "1시간 이하"; break;
								case 4: ori_length = "5시간 이하"; break;
								case 5: ori_length = "5시간 이상"; break;
								case 6: ori_length = "정확히 알 수 없음"; break;
								}
								switch(project.getBoard_creator_edit_length()){
								case 1: edit_length = "5분 이하"; break;
								case 2: edit_length = "10분 이하"; break;
								case 3: edit_length = "30분 이하"; break;
								case 4: edit_length = "1시간 이하"; break;
								case 5: edit_length = "1시간 이상"; break;
								}
								switch(project.getBoard_creator_ori_transfer()){
								case 1: ori_transfer = "이메일"; break;
								case 2: ori_transfer = "웹하드"; break;
								case 3: ori_transfer = "NAS"; break;
								case 4: ori_transfer = "SMS"; break;
								case 5: ori_transfer = "직접 전달"; break;
								}
								if((project.getBoard_creator_cre_min_price() / 1000) < 10){
									min_price = (project.getBoard_creator_cre_min_price() / 1000)+"천원";
								} else {
									if(project.getBoard_creator_cre_min_price() % 10000 > 0){
										min_price = (project.getBoard_creator_cre_min_price() / 10000.0)+"만원";
									} else {
										min_price = (project.getBoard_creator_cre_min_price() / 10000)+"만원";	
									}
								}
								if((project.getBoard_creator_cre_max_price() / 1000) < 10){
									max_price = (project.getBoard_creator_cre_max_price() / 1000)+"천원";
								} else {
									if(project.getBoard_creator_cre_max_price() % 10000 > 0){
									max_price = (project.getBoard_creator_cre_max_price() / 10000.0)+"만원";
									} else {
									max_price = (project.getBoard_creator_cre_max_price() / 10000)+"만원";	
									}
									
								}
								String project_status = "";
								switch(project.getBoard_creator_status()){
								case 0: project_status = "모집중"; break;
								case 1: project_status = "진행중"; break;
								case 2: project_status = "완료"; break;
								default: project_status = "모집중"; break;
								}
								%>
    		
       <div id="item-<%=i %>" data-href="<%=contentUrl%>?page=<%=nowPage %>&board_id=<%=project.getBoard_id()%>" class="col-md-6 d-flex align-items-stretch ftco-animate" style="cursor: pointer;" onclick="forward('<%=i%>','<%=isLogin%>')">
          <div class="project-wrap">
          	<div class="project-profile">
          	<span class="status" <%if(project.getBoard_creator_status()==1){%>
	          	style="background-color: #8b00ff;"
	          	<%} else if(project.getBoard_creator_status()==2){%>
	          	style="background-color: #008000;"
	          	<%} %>><%=project_status %></span>
             <span id="item-img<%=i %>" class="img item-link"style="position:absolute; top:50%; left:50%; transform:translate(-50%, -50%); background-image: url(images/no-profile.png); border-radius: 50%; width:150px; height:150px;margin:0 auto;">
            </span>
          	</div>
            <div class="text p-4">
                <h3><%=project.getBoard_subject() %></h3>
                <p class="advisor"><span class="userLv" style="font-weight:bold;
	                <%
	                if(project.getUser_level() >= 0 && project.getUser_level() < 20){
	                	%>color:#cc00cc<%
	                } else if(project.getUser_level() >= 20 && project.getUser_level() < 40){
	                	%>color:#000066<%
	                } else if(project.getUser_level() >= 40 && project.getUser_level() < 50){
	                	%>color:#6666ff<%
	                } else if(project.getUser_level() >= 50 && project.getUser_level() < 60){
	                	%>color:#66cc66<%
	                } else if(project.getUser_level() >= 60 && project.getUser_level() < 70){
	                	%>color:#e6e600<%
	                } else if(project.getUser_level() >= 70 && project.getUser_level() < 80){
	                	%>color:#ffcc00<%
	                } else if(project.getUser_level() >= 80 && project.getUser_level() < 90){
	                	%>color:#e62e00<%
	                } else if(project.getUser_level() >= 90){
	                	%>background-image: linear-gradient(to left, violet, indigo, blue, green, yellow, orange, red); -webkit-background-clip: text; color: transparent;<%
	                } else {
	                	%>color:#eeeeee<%
	                }
	                %>
	                
	                ;">Lv <%=project.getUser_level()%></span> <%=project.getUser_id() %> <span><%=genre %></span></p>
                <ul class="d-flex justify-content-between">
                   <li><span class="flaticon-shower"></span><%=board_date %></li>
                   <li class="price"><%=min_price %>~<%=max_price %></li>
               </ul>
           </div>
       </div>
   </div>
<%
		}
	} else {
		%>
		<section id="emptyArea">등록된 프로젝트가 없습니다.</section>
		<%
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
			</div>
			</div>
		</section>
		
		<jsp:include page="../inc/bottom.jsp"/>
			
			
			

			<!-- loader -->
			<div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>
			
			
			<jsp:include page="../inc/script.jsp"/>
			<script type="text/javascript">
				function forward(item, login){
					var url = $("#item-"+item).attr("data-href");
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