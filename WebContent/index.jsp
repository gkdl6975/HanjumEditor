<%@page import="java.util.Date"%>
<%@page import="com.hanjum.board.vo.EditorBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.hanjum.board.vo.ProjectBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	<style>
		.login-wrap { margin-top: -480px;}
		.userLv { font-weight: bold;}
	</style>
	
</head>
<body>
<jsp:include page="inc/top.jsp"/>
<!-- END nav -->
<div class="hero-wrap js-fullheight" style="background-image: url('images/bg_1.jpg');">
  <div class="overlay"></div>
  <div class="container">
    <div class="row no-gutters slider-text js-fullheight align-items-center" data-scrollax-parent="true">
      <div class="col-md-7 ftco-animate">
        <span class="subheading">Welcome to Hanjum</span>
        <h1 class="mb-4 t-shadow">한줌에디터에서 영상 편집자를 찾아보세요.</h1>
        <p class="caps t-shadow">다양한 프로젝트와 능력있는 편집자들을 찾아 원하는 대로 만들어가세요.</p>
        <%
        	boolean isLogin = true;
        	if(session.getAttribute("userBean") == null){
        		isLogin = false;
        %>
        <p class="mb-0"><a class="btn btn-primary" href="My.uo?fr=joinEditor">편집자 등록</a> <a class="btn btn-white" href="My.uo?fr=join">일반 회원등록</a></p>
    	<%
        	}
    	%>
    </div>
</div>
</div>
</div>

<section class="ftco-section ftco-no-pb ftco-no-pt">
   <div class="container">
      <div class="row">
         <div class="col-md-7"></div>
         <div class="col-md-5 order-md-last">
<!-- 	로그인 창이 위치하는 곳 -->
 </div>
</div>
</div>
</section>

<section class="ftco-section">
   <div class="container">
      <div class="row justify-content-center pb-4">
          <div class="col-md-12 heading-section text-center ftco-animate">
          	<span class="subheading">장르를 선택하세요</span>
            <h2 class="mb-4">찾고자 하는 프로젝트의 장르가 있습니까?</h2>
        </div>
    </div>
    <div class="row justify-content-center">
    <%
    HashMap<String, Integer> genreCount = null;
    if(request.getAttribute("projectGenreCount") != null){
    	
    	genreCount = (HashMap<String, Integer>)request.getAttribute("projectGenreCount");
   		
	    for(int i = 0; i < genreCount.size(); i++){
	    	String imgUrl = "";
	    	String genreUrl = "ProjectListSearch.bo";
	    	switch (genreCount.keySet().toArray()[i]+""){
	    	case "유튜브" : imgUrl = "images/bg_youtube.jpg";
	    				  genreUrl += "?board_creator_genre=1"; break;  
	    	case "홍보영상" : imgUrl = "images/bg_promotion.jpg";
	    				  genreUrl += "?board_creator_genre=2"; break;   
	    	case "광고" : imgUrl = "images/bg_ad.jpg";
	    				  genreUrl += "?board_creator_genre=3"; break;   
	    	case "드라마" : imgUrl = "images/bg_drama.jpg";
	    				  genreUrl += "?board_creator_genre=5"; break;   
	    	case "모션그래픽" : imgUrl = "images/bg_motion.jpg";
	    				  genreUrl += "?board_creator_genre=6"; break;  
	    	default : imgUrl = "images/work-1.jpg";
	    				  genreUrl += "?board_creator_genre=7";  
	    	}
	    %>
	     <div class="col-md-3 col-lg-2">
	        <a href="<%=genreUrl %>" class="course-category img d-flex align-items-center justify-content-center" style="background-image: url(<%=imgUrl%>);">
	           <div class="text w-100 text-center font-weight-bold">
	              <h3><%=genreCount.keySet().toArray()[i] %></h3>
	              <span><%=genreCount.values().toArray()[i] %> 개의 프로젝트</span>
	          </div>
	      </a>
	  </div>
	  <%
	    }
    } else {
    	if(request.getServletPath().contains("index")){
        	response.sendRedirect("home");
        }
    }
  %>
<div class="col-md-12 text-center mt-5">
    <a href="ProjectList.bo" class="btn btn-secondary">모두보기</a>
</div>
</div>
</div>
</section>

<section class="ftco-section bg-light">
   <div class="container">
      <div class="row justify-content-center pb-4">
          <div class="col-md-12 heading-section text-center ftco-animate">
          	<span class="subheading">Start Project</span>
            <h2 class="mb-4">등록된 프로젝트</h2>
        </div>
    </div>
    <div class="row">
    <%
    	if(request.getAttribute("projectList") != null){
    		ArrayList<ProjectBean> projectList = (ArrayList<ProjectBean>)request.getAttribute("projectList");
			
			String contentUrl = "Project.bo";
			
		if(projectList != null){
			String genre = "";
			String recording = "";
			String ori_clip = "";
			String ori_length = "";
			String edit_length = "";
			String ori_transfer	= "";
			String min_price = "";
			String max_price = "";
			for(int i= 0; i < projectList.size(); i++){
				if(i > 5){
					break;
				}
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
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String projectDate = sdf.format(project.getBoard_date());
	    %>
	       <div id="item-<%=i %>" data-href="<%=contentUrl%>?board_id=<%=project.getBoard_id()%>" style="cursor: pointer;" class="col-md-4 ftco-animate" onclick="forward('<%=i%>','<%=isLogin%>')">
	          <div class="project-wrap">
	          	<div class="project-profile">
	          	<span class="status" <%if(project.getBoard_creator_status()==1){%>
	          	style="background-color: #8b00ff;"
	          	<%} else if(project.getBoard_creator_status()==2){%>
	          	style="background-color: #008000;"
	          	<%} %>>
	          	<%=project_status %></span>
	             <span id="item-img<%=i %>" class="img item-link" style="position:absolute; top:50%; left:50%; transform:translate(-50%, -50%); background-image: url(images/no-profile.png); border-radius: 50%; width:150px; height:150px;margin:0 auto;">
	            </span>
	          	</div>
	            <div class="text p-4">
	                <h3><%=project.getBoard_subject() %></h3>
	                <p class="advisor"><span class="userLv" style="
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
	                
	                ;">Lv <%=project.getUser_level()%></span> <%=project.getUser_name() %> <span><%=genre %></span></p>
	                <ul class="d-flex justify-content-between">
	                   <li><span class="flaticon-shower"></span><%=projectDate %></li>
	                   <li class="price"><%=min_price %> ~ <%=max_price %></li>
	               </ul>
	           </div>
	       </div>
	   </div>
	<%
	    	}
	    }
    }
%>
</div>
</div>
</section>

<section class="ftco-section ftco-counter img" id="section-counter" style="background-image: url(images/bg_4.jpg);">
 <div class="overlay"></div>
 <div class="container">
  <div class="row">
    <%
   	if(request.getAttribute("projectStatusCount") != null){
   		HashMap<Integer, Integer> statusCount = (HashMap<Integer,Integer>)request.getAttribute("projectStatusCount");
   		for(int i = 0; i < statusCount.size(); i++){
   			String siteInfoContent = "";
   			String siteInfoIcon = "";
   			switch(i){
   			case 0: siteInfoContent = "등록된 프로젝트 수"; siteInfoIcon = "flaticon-instructor"; break;
   			case 1: siteInfoContent = "모집중인 프로젝트 수"; siteInfoIcon = "flaticon-online"; break;
   			case 2: siteInfoContent = "등록된 편집자 수"; siteInfoIcon = "flaticon-graduated"; break;
   			case 3: siteInfoContent = "진행중인 프로젝트 수"; siteInfoIcon = "flaticon-tools"; break;
   			}
   		
   %>
    <div class="col-md-3 d-flex justify-content-center counter-wrap ftco-animate">
 			<div class="block-18 d-flex align-items-center">
	   			 <div class="icon"><span class="<%=siteInfoIcon%>"></span></div>
	   			 <div class="text">
	    		 <strong class="number" data-number="<%=statusCount.get(i+1)%>">0</strong>
	    		 <span><%=siteInfoContent %></span>
	 		</div>
		</div>
 	</div>
 	<%
   		}
   	}
 	%>
</div>
</div>
</section>

<section class="ftco-section ftco-about img">
   <div class="container">
      <div class="row d-flex">
         <div class="col-md-12 about-intro ftco-animate">
            <img src="images/main_intro.png">
 </div>
</div>
</div>
</section>


<section class="ftco-section testimony-section bg-light">
   <div class="overlay" style="background-image: url(images/editor.jpg);"></div>
   <div class="container">
    <div class="row pb-4">
      <div class="col-md-7 heading-section ftco-animate">
         <span class="subheading">Editor</span>
         <h2 class="mb-4">어떤 편집자를 원하십니까?</h2>
     </div>
 </div>
</div>
<div class="container container-2">
    <div class="row ftco-animate">
      <div class="col-md-12">
        <div class="carousel-testimony owl-carousel">
 
        <%
        if(request.getAttribute("editorList") != null){
        	ArrayList<EditorBean> editorList = (ArrayList<EditorBean>)request.getAttribute("editorList");
	        for(int i = 0; i < editorList.size(); i++){
	        	EditorBean editorBean = editorList.get(i);
    	    	int score = editorBean.getUser_score(); 
	        %>
	          <div id="edItem-<%=i %>" data-href="Editor.bo?board_id=<%=editorBean.getBoard_id()%>" class="item" style="cursor: pointer;" onclick="forwardEd('<%=i %>','<%=isLogin %>')">
	            <div class="testimony-wrap py-4">
	              <div class="text">
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
	                <p class="mb-4 text" style="height: 144px; color:#212529;"><%=editorBean.getBoard_subject() %></p>
	                <div class="d-flex align-items-center">
	                   <div class="user-img" style="background-image: url(editorUserPhotoUpload/<%=editorBean.getBoard_ed_photo()%>)"></div>
	                   <div class="pl-3">
	                      <p class="name"><%=editorBean.getUser_name() %></p>
	                      <span class="position"><span class="userLv" style="
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
	                
	                ;">Lv <%=editorBean.getUser_level()%> 편집자</span></span> 
	                  </div>
	              </div>
	          </div>
	      </div>
	  </div>
	<%
	        }
       }
        %>

</div>
</div>
</div>
</div>
</section>

<section class="ftco-intro ftco-section ftco-no-pb">
 <div class="container">
    <div class="row justify-content-center">
       <div class="col-md-12 text-center">
          <div class="img"  style="background-image: url(images/bg_4.jpg);">
             <div class="overlay"></div>
             <h2>혹시 저희 사이트에서 불편함을 겪고 계신가요?</h2>
             <p>고객센터에서 자주묻는 질문 코너를 이용해주세요</p>
             <p class="mb-0"><a href="Center.hp" class="btn btn-primary px-4 py-3">고객센터</a></p>
         </div>
     </div>
 </div>
</div>
</section>

<section class="ftco-section services-section">
  <div class="container">
    <div class="row d-flex">
      <div class="col-md-6 heading-section pr-md-5 ftco-animate d-flex align-items-center">
       <div class="w-100 mb-4 mb-md-0">
        <span class="subheading">Welcome to Hanjum</span>
        <h2 class="mb-4">한줌에디터의 프로세스를 경험해보세요.</h2>
        <p>크리에이터님의 영상을 제작해 주실 편집자분들을 찾기 위해 프로젝트를 등록해보세요. 구체적인 분야와 설명을 작성할수록 좋은 영상을 만들 수 있습니다.</p>
        <p>마음에 드는 믿을만한 편집자를 만나보세요. 유사 프로젝트를 진행한 경험이 있는 편집자에게 프로젝트 분석 및 제안을 받아볼 수 있습니다.</p>
     	<p>금전거래를 하시기 망설여지시나요? 한줌 에디터의 거래시스템을 통해 안전한 공정거래가 이루어지니 안심하세요.</p>
     	<p>실물미팅을 하시기 주저되시나요? 한줌 에디터의 1:1 채팅기능을 통해 원하시는 프로젝트의 방향을 토의해보세요.</p>
     	<p>프로젝트 진행이 끝나면 크리에이터님은 결과물을 받고 지급승인을 눌러 대금지급을 완료합니다.</p>
     </div>
   </div>
   <div class="col-md-6">
     <div class="row">
      <div class="col-md-12 col-lg-6 d-flex align-self-stretch ftco-animate">
        <div class="services">
          <div class="icon d-flex align-items-center justify-content-center"><span class="flaticon-tools"></span></div>
          <div class="media-body">
            <h3 class="heading mb-3">프로젝트 등록</h3>
            <p>프로젝트를 진행할 때 크리에이터님들이 원하는 구체적인 방향과 적절한 단가를 작성해주세요.</p>
          </div>
        </div>      
      </div>
      <div class="col-md-12 col-lg-6 d-flex align-self-stretch ftco-animate">
        <div class="services">
          <div class="icon icon-2 d-flex align-items-center justify-content-center"><span class="flaticon-instructor"></span></div>
          <div class="media-body">
            <h3 class="heading mb-3">편집자 모집</h3>
            <p>지원자들의 포트폴리오와 평가를 한눈에 볼 수 있습니다. 마음에 드는 편집자를 선택하셔서 프로젝트를 진행하세요.</p>
          </div>
        </div>    
      </div>
      <div class="col-md-12 col-lg-6 d-flex align-self-stretch ftco-animate">
        <div class="services">
          <div class="icon icon-3 d-flex align-items-center justify-content-center"><span class="flaticon-quiz"></span></div>
          <div class="media-body">
            <h3 class="heading mb-3">프로젝트 미팅</h3>
            <p>프로젝트 진행중에 1:1 채팅을 통해 프로젝트의 진행방향을 토의 하실 수 있습니다.</p>
          </div>
        </div>      
      </div>
      <div class="col-md-12 col-lg-6 d-flex align-self-stretch ftco-animate">
        <div class="services">
          <div class="icon icon-4 d-flex align-items-center justify-content-center"><span class="flaticon-browser"></span></div>
          <div class="media-body">
            <h3 class="heading mb-3">프로젝트 완료</h3>
            <p>마음에 드는 결과물을 받으셨다면 해당 편집자님을 관심등록 하여 다음에도 프로젝트를 함께 진행해보세요!</p>
          </div>
        </div>      
      </div>
    </div>
  </div>
</div>
</div>
</section>

<jsp:include page="inc/bottom.jsp"/>



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
<script src="js/main.js"></script>
<script src="js/focus.js"></script>
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
<%
		if(session.getAttribute("userBean")!=null){
			%>
			<script type="text/javascript">
				$(document).ready(function(){
					$(".order-md-last").load("UserInfo.uo");
				});
			</script>
			<%
		} else {
			%>
			<script type="text/javascript">
				$(document).ready(function(){
					$(".order-md-last").load("Login.uo");
				});
			</script>
			<%
		}
	%>
	
</body>
</html>