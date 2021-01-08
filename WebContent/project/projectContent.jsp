<%@page import="java.util.ArrayList"%>
<%@page import="com.hanjum.board.vo.BoardBean"%>
<%@page import="com.hanjum.user.vo.UserBean"%>
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
	<link rel="stylesheet" href="css/project.css">
	<link rel="stylesheet" href="css/layer.css">
	
	<%
	if(session.getAttribute("userBean")==null){
	%>
	<script>
		var loginConfirm = confirm("로그인이 필요합니다 로그인 하시겠습니까?");
		if(loginConfirm){
			location.href = "My.uo?fr=login";
		} else {
			history.back();
		}
	</script>
	<%
	}
	
	UserBean userBean = (UserBean)session.getAttribute("userBean");
	int checkApply = (int)request.getAttribute("checkApply");
	int checkProject = (int)request.getAttribute("checkProject");
	String prefPage = "";
	if(request.getHeader("referer") != null){
		String prefStr = request.getHeader("referer");
		prefPage = prefStr.substring(prefStr.lastIndexOf("/")+1);
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
					<h1 id="pageTitle" class="mb-0 bread">프로젝트 조회</h1>
				</div>
			</div>
		</div>
	</section>
	<%
    ProjectBean project = (ProjectBean)request.getAttribute("project");
	
	String pageUrl = "Project.bo";
	String nowPageStr = (String)request.getAttribute("page");
	int nowPage = Integer.parseInt(nowPageStr);
	if(project != null){
		String genre = "";
		String recording = "";
		String ori_clip = "";
		String ori_length = "";
		String edit_length = "";
		String ori_transfer	= "";
		String min_price = "";
		String max_price = "";
		String cam_num = "";
		
		ArrayList<String> ref = null;
		if(project.getBoard_creator_cre_ref() != "" && project.getBoard_creator_cre_ref().contains("v=")){
			ref = new ArrayList<String>();
			for(String refStr : project.getBoard_creator_cre_ref().split(",")){
				ref.add(refStr);
			}
		}
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
		switch(project.getBoard_creator_cam_num()){
			case 1: cam_num = "1캠"; break;
			case 2: cam_num = "2캠";break;
			case 3: cam_num = "3캠";break;
			case 4: cam_num = "4캠 이상";break;
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
			
	%>
	<section id="pageContent" class="ftco-section bg-light">
<div class="login-wrap p-4 p-md-5" style="margin:0px auto; max-width : 800px;">
		<div class="form-group">
        	<label class="label has-focus label-primary">프로젝트 제목</label>
        	<div class="form-group-content p-tb-10">
        	<%=project.getBoard_subject() %>
        	</div>
        </div>
        <div class="form-group">
        	<label class="label has-focus label-primary">작성자</label>
        	<div class="form-group-content p-tb-10">
        	<%=project.getUser_id() %>
        	</div>
        </div>
        <div class="form-group">
        	<label class="label has-focus label-primary">프로젝트 소개</label>
        	<div class="form-group-content p-tb-10">
        	<%=project.getBoard_content()%>
        	</div>
        </div>
		<div class="form-group">
        	<label class="label has-focus label-primary">장르</label>
        	<div class="form-group-content p-tb-10">
			<%=genre %>
        	</div>
		</div>
		<div class="form-group">
            <label  class="label has-focus label-primary">세부 설명</label>
            <div class="p-tb-20 m-l-50" style="text-align: justify;">
            <%=project.getBoard_creator_content_detail()%>
            </div>
        </div>
        <div class="form-group">
        	<label class="label has-focus label-primary">동시 녹음 유무</label>
        	<div class="form-group-content p-tb-10">
        	<%=recording %>
        	</div>
		</div>
		<div class="form-group">
        	<label class="label has-focus label-primary">녹화에 이용된 캠</label>
        	<div class="form-group-content p-tb-10">
        	<%=cam_num %>
        	</div>
		</div>
		<div class="form-group">
        	<label class="label has-focus label-primary">원본 클립</label>
        	<div class="form-group-content p-tb-10">
        	<%=ori_clip %>
        	</div>
		</div>
		<div class="form-group">
        	<label class="label has-focus label-primary">편집전 런타임</label>
        	<div class="form-group-content p-tb-10">
        	<%=ori_length %>
        	</div>
		</div>
		<div class="form-group">
        	<label class="label has-focus label-primary">편집후 런타임</label>
        	<div class="form-group-content p-tb-10">
        	<%=edit_length %>
        	</div>
		</div>
		<div class="form-group">
        	<label class="label has-focus label-primary">파일의 전달방식</label>
        	<div class="form-group-content p-tb-10">
        	<%=ori_transfer %>
        	</div>
		</div>
		<div class="form-group">
            <label class="label has-focus label-primary">예상 단가</label>
            <div class="form-group-content p-tb-10">
            <%=min_price %> ~ <%=max_price %>
            </div>
        </div>
        <%
    	if(ref != null && ref.size() > 0){
    %>
    <div id="ref_area" class="form-group">
        <label class="label has-focus label-primary">레퍼런스 링크</label>
        <%
		for(int i = 0; i < ref.size(); i++){
			String refID = "";
			if(ref.get(i).contains("v=")){
				refID = ref.get(i).substring(ref.get(i).lastIndexOf("v=")+2);
			}

		%>
		<div class="form-group-content p-tb-10">
		<div class="board_ref"><iframe src="https://www.youtube.com/embed/<%=refID %>" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></div>
		</div>
		<%
		}
		%>
    </div>
    <%
    	}
        %>
        <div class="form-group d-flex justify-content-end mt-4">
        <%
        if(userBean != null){
	        if(userBean.getUser_id().equals(project.getUser_id()) && project.getBoard_creator_status() == 0){
	        %>
	       	<a class="btn btn-primary submit m-r-10" type="button" id="ApplySendBtn" data-href="UserLikeList.uo?user_id=<%=project.getUser_id()%>">지원요청</a> 
	        <a class="btn btn-primary submit m-r-10" type="button" id="UpdateBtn" href="ProjectUpdate.bo?page=<%=nowPage%>&board_id=<%=project.getBoard_id()%>">수정하기</a> 
			<a class="btn btn-light submit m-r-10" type="button" id="DeleteBtn" href = 'ProjectDeletePro.bo?board_id=<%=project.getBoard_id()%>'>삭제하기</a>
	        <%
	        }
        	if(userBean.getUser_type() == 2 && !userBean.getUser_id().equals(project.getUser_id()) && project.getBoard_creator_status() == 0){
        		if(checkApply == 0){
        	%>
        	<a class="btn btn-primary submit m-r-10" type="button" id="ApplyBtn" data-href="ProjectApplyPro.bo?board_id=<%=project.getBoard_id()%>&page=<%=nowPage%>">지원하기</a> 
        	<%
        		} else if(checkApply == 1) {
        			%>
                	<a class="btn btn-light submit m-r-10" type="button" id="CancleBtnAp" href="ProjectApplyCanclePro.bo?board_id=<%=project.getBoard_id()%>&page=<%=nowPage%>">지원취소</a> 
                	<%
        		} else if(checkApply == 2) {
        			%>
                	<a class="btn btn-light submit m-r-10" type="button" id="CancleBtnCo" href="ProjectContractCanclePro.bo?board_id=<%=project.getBoard_id()%>&page=<%=nowPage%>">계약취소</a> 
                	<%
        		}
        	} else if(userBean.getUser_type() == 1 && userBean.getUser_id().equals(project.getUser_id())){
        		if(checkProject == 1) {
        			%>
                	<a class="btn btn-primary submit m-r-10" type="button" id="ApplyBtnCo" href="ProjectContractSuccessPro.bo?board_id=<%=project.getBoard_id()%>&page=<%=nowPage%>">계약완료</a> 
                	<a class="btn btn-light submit m-r-10" type="button" id="CancleBtnCo" href="ProjectContractCanclePro.bo?board_id=<%=project.getBoard_id()%>&page=<%=nowPage%>">계약취소</a> 
                	<%
        		}
        	}
        }
        %>
        <a class="btn btn-primary submit" type="button" id="ListBtn" href="ProjectList.bo?page=<%=nowPage%>">목록으로</a> 
        
        </div>
</div>

</section>
			<jsp:include page="../inc/script.jsp"/>
			<div class="layerForm" id="write-layer">
						<div class="layer-content" id="write-layer-content">
						<span class="close" id="close-write">&times;</span>
						<div class="layer-container">
			<!-- 			페이지가 들어갈 공간 -->
						</div>
						</div>
			</div>
		<script type="text/javascript">
			 var modal = $(".layerForm"); 
			 var $container = $('.layer-container');
			 var $content = $('#write-layer-content'); // 회원가입 페이지가 들어갈 공간
			 function toggleModal() { 
//	             var show = modal.classList.toggle("show-layer");
				if(!modal.attr("class").includes("show-layer")){  // 이거는 페이지를 여는 스위치 show-layer가 만약 없다면
					modal.addClass('show-layer'); // 클래스 삽입
					$container.load('ProjectWrite.bo');
				} else { // 만약 있다면 닫아야 하니까
				 	$container.empty();	 // 불러왔던 페이지를 다 없애고 (empty() 함수)
					modal.removeClass('show-layer'); // 삽입했던 show-layer 클래스 삭제
					if ($content.attr("class") == "layer-login-content") {
						$content.removeClass('layer-login-content');
						$content.addClass('layer-content');			
					} else if ($content.attr("class") == "layer-join-content") {
						$content.removeClass('layer-join-content');
						$content.addClass('layer-content');			
					}
				}
	         }
			
		     function windowOnClick(event) {
				var target = $(event.target);
					if(target.is(".layerForm")){
						toggleModal()
					}
		     }
		     
		     function applySend(name, id){
		    	var cfSend = confirm(name+"님께 프로젝트 지원요청을 보내시겠습니까?");
		     	if(cfSend){
		     		location.href = "ProjectApplySend.bo?board_id=<%=project.getBoard_id()%>&writer_id=<%=project.getUser_id()%>&page=<%=nowPage%>&user_id="+id;
		     	}
		     
		     }
		     
			$(document).ready(function(){
				$("#ApplyBtn").click(function(){
					
					const price = Number(prompt("계약 희망 금액을 입력하십시오. \n(<%=min_price %> ~ <%=max_price %>)","ex) <%=project.getBoard_creator_cre_max_price()%>"));
					if(isNaN(price)){
						alert("숫자만 입력하세요 ex) <%=project.getBoard_creator_cre_max_price()%>");
					} else {
						if(price <= <%=project.getBoard_creator_cre_max_price()%> && price >= <%=project.getBoard_creator_cre_min_price()%>){
							var cfPrice = confirm(price+"원으로 지원하시겠습니까?");
							if(cfPrice){
								var href = $("#ApplyBtn").attr("data-href");
								location.href = href+"&contract_price="+price;
							}
						} else {
							alert("<%=min_price %> ~ <%=max_price %> 사이의 금액으로 지원하실수 있습니다.");
						}
					}
				})
				$(".layer-btn").click(function(){
					toggleModal();
				});
				$(".close").click(function(){
					toggleModal();
				});
				$(window).click(function(e){
					windowOnClick(e);
				});
				$("#ApplySendBtn").click(function(){
					$content.removeClass('layer-content'); // 회원가입 페이지만큼 공간을 키워야 돼서 미리 만들어놓은 css 적용
													   // 있던 클래스 삭제
					$content.addClass('layer-join-content'); // 미리 설정해놓은 join 전용 클래스 삽입
					modal.addClass('show-layer');			// 그리고 숨겨놓은 공간을 열어야 해서 display: block 로
															// 설정을 바꿔주는 show-layer 클래스 삽입
					$container.load($(this).attr('data-href'));	
				})
			})
			
		</script>
<%
}
%>
		<jsp:include page="../inc/bottom.jsp"/>
			
			
			

			<!-- loader -->
			<div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>
			
			
			
		</body>
</html>
