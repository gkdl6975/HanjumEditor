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
</head>
<body>
 <jsp:include page="../inc/top.jsp"/>
 <!-- END nav -->
 
 <section class="hero-wrap hero-wrap-2" style="background-image: url('images/bg_2.jpg');">
  <div class="overlay"></div>
  <div class="container">
    <div class="row no-gutters slider-text align-items-end justify-content-center">
      <div class="col-md-9 ftco-animate pb-5 text-center">
       <p class="breadcrumbs"><span class="mr-2"><a href="home">Home <i class="fa fa-chevron-right"></i></a></span> <span>About us <i class="fa fa-chevron-right"></i></span></p>
       <h1 class="mb-0 bread">About Us</h1>
     </div>
   </div>
 </div>
</section>

<section class="ftco-section ftco-about img">
 <div class="container">
  <div class="row d-flex">
   <div class="col-md-12 about-intro">
   <img src="images/main_intro.png">
</div>
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


<section class="ftco-intro ftco-section ftco-no-pb">
 <div class="container">
    <div class="row justify-content-center">
       <div class="col-md-12 text-center">
          <div class="img"  style="background-image: url(images/bg_4.jpg);">
             <div class="overlay"></div>
             <h2>혹시 저희 사이트에서 불편함을 겪고 계신가요?</h2>
             <p>고객센터에서 자주묻는 질문과 1:1 상담을 이용해주세요</p>
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

<jsp:include page="../inc/bottom.jsp"/>



<!-- loader -->
<div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


<jsp:include page="../inc/script.jsp"/>

</body>
</html>