<%@page import="com.hanjum.user.vo.PortfolioBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hanjum.user.vo.UserBean"%>
<%@page import="com.hanjum.board.vo.EditorBean"%>
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
		.color-primary {
			color: #007bff;
		}
		.label-primary { color: #4986fc !important; }
		.form-group-content {text-align: right;}
		.form-group:hover > .form-group-content{
			font-weight: bold;
		}
		.board_ref {
		max-width:560px;
		max-height:315px;
		width:90%;
		}
	</style>
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
					<p class="breadcrumbs"><span class="mr-2"><a href="home">Home <i class="fa fa-chevron-right"></i></a></span> <span>Editor <i class="fa fa-chevron-right"></i></span></p>
					<h1 id="pageTitle" class="mb-0 bread">편집자 조회</h1>
				</div>
			</div>
		</div>
</section>
	<%
	EditorBean editorBean = (EditorBean)request.getAttribute("editorBean");
	
	String pageUrl = "Editor.bo";
	String nowPageStr = (String)request.getAttribute("page");
	int nowPage = 1;
	if(nowPageStr != null){
		
	nowPage = Integer.parseInt(nowPageStr);
	}
	

	String editor_program = "", editor_solution = "", editor_inventory = "", 
			editor_upload = "", editor_work = "", editor_meeting = "", editor_fort = "",
					editor_sample = "", editor_ed_min_price = "", editor_ed_max_price = "",
							editor_address = "";
	
	if(editorBean != null){
		editor_program = editorBean.getBoard_ed_program().replace("1", "프리미어").replace("2", "파이널컷")
				.replace("3", "베가스").replace("4", "에프터 이펙트").replace("5", "기타");
		editor_solution = editorBean.getBoard_ed_solution().replace("1", "HD").replace("2", "FHD")
				.replace("3", "UHD").replace("4", "맞춤 가능");
		editor_inventory = editorBean.getBoard_ed_inventory().replace("1", "컷 편집").replace("2", "오디오 싱크")
				.replace("3", "BGM 삽입").replace("4", "효과음 삽입").replace("5", "모션 그래픽").replace("6", "템플릿 작업");
	
		switch (editorBean.getBoard_ed_upload()) {
		case 1: editor_upload = "이메일"; break;
		case 2: editor_upload = "NAS"; break;
		case 3: editor_upload = "웹하드"; break;
		case 4: editor_upload = "모두 가능"; break;
		}
		
		switch (editorBean.getBoard_ed_work()) {
		case 1: editor_work = "1캠"; break;
		case 2: editor_work = "2캠"; break;
		case 3: editor_work = "3캠"; break;
		case 4: editor_work = "4캠 이상"; break;
		}
		
		switch (editorBean.getBoard_ed_meeting()) {
		case 1: editor_meeting = "가능"; break;
		case 2: editor_meeting = "불가능"; break;
		}
		
		switch (editorBean.getBoard_ed_fort()) {
		case 1: editor_fort = "건당 계약"; break;
		case 2: editor_fort = "단기 계약"; break;
		case 3: editor_fort = "장기 계약"; break;
		}
		
		switch (editorBean.getBoard_ed_sample()) {
		case 1: editor_sample = "가능"; break;
		case 2: editor_sample = "불가능"; break;
		}
		if(editorBean.getBoard_ed_min_price() != 0) {
			if((editorBean.getBoard_ed_min_price() / 1000) < 10){
				editor_ed_min_price = (editorBean.getBoard_ed_min_price() / 1000)+"천원";
			} else {
				if(editorBean.getBoard_ed_min_price() % 10000 > 0){
					editor_ed_min_price = (editorBean.getBoard_ed_min_price() / 10000.0)+"만원";
				} else {
					editor_ed_min_price = (editorBean.getBoard_ed_min_price() / 10000)+"만원";	
				}
			}
		} else {
			editor_ed_min_price = "0원";
		}
		if(editorBean.getBoard_ed_max_price() != 0) {
			if((editorBean.getBoard_ed_max_price() / 1000) < 10){
				editor_ed_max_price = (editorBean.getBoard_ed_max_price() / 1000)+"천원";
			} else {
				if(editorBean.getBoard_ed_max_price() % 10000 > 0){
					editor_ed_max_price = (editorBean.getBoard_ed_max_price() / 10000.0)+"만원";
				} else {
					editor_ed_max_price = (editorBean.getBoard_ed_max_price() / 10000)+"만원";	
				}
			}
		} else {
			editor_ed_max_price = "0원";
		}
		editor_address = editorBean.getBoard_ed_address();
	%>
	<section id="pageContent" class="ftco-section bg-light">
		<div class="login-wrap p-4 p-md-5" style="margin:0px auto; max-width : 800px;">
			<div class="form-group">
	        	<label class="label has-focus label-primary">이력서 제목</label>
	        	<div class="form-group-content p-tb-10">
	        	<%=editorBean.getBoard_subject() %>
	        	</div>
        	</div>
			<div class="form-group">
	        	<label class="label has-focus label-primary">편집자 아이디</label>
	        	<div class="form-group-content p-tb-10">
	        	<%=editorBean.getUser_id() %>
	        	</div>
	        </div>
	        <div class="form-group">
	        	<label class="label has-focus label-primary">이력서 소개</label>
	        	<div class="form-group-content p-tb-10">
	        	<%=editorBean.getBoard_content()%>
	        	</div>
	        </div>
	        <div class="form-group">
	            <label  class="label has-focus label-primary">편집자 소개</label>
	            <div class="p-tb-20 m-l-50" style="text-align: justify;">
	            <%=editorBean.getBoard_ed_content_detail()%>
	            </div>
        	</div>
			<div class="form-group">
	        	<label class="label has-focus label-primary">이용 프로그램</label>
	        	<div class="form-group-content p-tb-10">
	        	<%=editor_program %>
	        	</div>
			</div>
			<div class="form-group">
	        	<label class="label has-focus label-primary">최종 납품 해상도</label>
	        	<div class="form-group-content p-tb-10">
	        	<%=editor_solution %>
	        	</div>
			</div>
			<div class="form-group">
	        	<label class="label has-focus label-primary">작업 가능 항목</label>
	        	<div class="form-group-content p-tb-10">
	        	<%=editor_inventory %>
	        	</div>
			</div>
			<div class="form-group">
	        	<label class="label has-focus label-primary">납품 파일 공유 방식</label>
	        	<div class="form-group-content p-tb-10">
	        	<%=editor_upload %>
	        	</div>
			</div>
			<div class="form-group">
	        	<label class="label has-focus label-primary">실물 미팅</label>
	        	<div class="form-group-content p-tb-10">
	        	<%=editor_meeting %>
	        	</div>
			</div>
			<div class="form-group">
	        	<label class="label has-focus label-primary">선호 작업 유형</label>
	        	<div class="form-group-content p-tb-10">
	        	<%=editor_fort %>
	        	</div>
			</div>
			<div class="form-group">
	        	<label class="label has-focus label-primary">샘플 작업 가능 유무</label>
	        	<div class="form-group-content p-tb-10">
	        	<%=editor_sample %>
	        	</div>
			</div>
			<div class="form-group">
	        	<label class="label has-focus label-primary">예상 단가</label>
	        	<div class="form-group-content p-tb-10">
	        	<%=editor_ed_min_price %> ~ <%=editor_ed_max_price %>
	        	</div>
			</div>
			<div class="form-group">
	        	<label class="label has-focus label-primary">거주지</label>
	        	<div class="form-group-content p-tb-10">
	        	<%=editor_address %>
	        	</div>
			</div>
			<%
				int pf_count = 0;
				if(request.getAttribute("pf_count") != null){
		    		pf_count = (int)request.getAttribute("pf_count");
		    	}
		    	ArrayList<PortfolioBean> portfolioList = (ArrayList<PortfolioBean>)request.getAttribute("portfolioList");

			%>
			<div class="form-group">
	        	<label class="label has-focus label-primary">포트폴리오 갯수</label>
	        	<div class="form-group-content p-tb-10">
	        	<%=pf_count %>개
	        	</div>
			</div>
			
			<%
             for(int i = 0; i < portfolioList.size(); i++){
            	 PortfolioBean portfolioBean = portfolioList.get(i);
            	 String genre = portfolioBean.getEditor_pf_category()
							.replace("1", "유튜브")
							.replace("2", "홍보")
							.replace("3", "광고")
							.replace("4", "뮤직비디오")
							.replace("5", "드라마")
							.replace("6", "모션그래픽")
							.replace("7", "기타");
 				String refID = portfolioBean.getEditor_pf_link().substring(portfolioBean.getEditor_pf_link().lastIndexOf("v=")+2);

             %>
             <div id="portfolioForm<%=portfolioBean.getEditor_pf_id() %>">
			 	<iframe src="https://www.youtube.com/embed/<%=refID %>" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
             <div class="form-group">
			 <span class="form-group-content portfolioGroup">
			 	<span class="tx_overflow" style=" width: 80%;">장르 <span class="color-primary"><%=genre %></span>
			 	<span class="m-l-10"><%=portfolioBean.getEditor_pf_subject() %></span></span>
			 </span>             
             </div>
             </div>
             <%
             }
             %> 	
			<h3>리뷰</h3>
			<hr>
			<div id="review_area">
			
			</div>
			<div class="form-group d-flex justify-content-end mt-4">
	        <a class="btn btn-primary submit" type="button" id="ListBtn" href="EditorList.bo?page=<%=nowPage%>">목록으로</a> 
	        <%
	        if(userBean != null)
	        if(userBean.getUser_id().equals(editorBean.getUser_id())){
	        %>
	        <a class="btn btn-primary submit m-l-10" type="button" id="UpdateBtn" href="EditorUpdate.bo?page=<%=nowPage%>&board_id=<%=editorBean.getBoard_id()%>">수정하기</a> 
	        <%
	        } else{
	        	int like = (int)request.getAttribute("like"); 
	        %>
	        	 <a class="btn <%if(like==1){ %>btn-light<%} else { %>btn-primary<%} %> submit m-l-10" type="button" id="likeBtn" href="UserLike.uo?user_id=<%=editorBean.getUser_id()%>&like_userid=<%=userBean.getUser_id()%>&board_id=<%=editorBean.getBoard_id()%>&page=<%=nowPage%>"><%if(like==1){%>관심등록중<%}else{%>관심등록<%}%></a> 
	        <%}
	        %>
	        </div>
        </div>
	</section>	
	
	<%
	}
	%>
		<jsp:include page="../inc/bottom.jsp"/>
			
			
			

			<!-- loader -->
			<div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>
			
			
			<jsp:include page="../inc/script.jsp"/>
			<script type="text/javascript">
				$(document).ready(function(){
					$("#review_area").load("ReviewList.rv?user_id=<%=editorBean.getUser_id()%>");
					$("#review_area").on("click",".page-move",function(){
						var href = $(this).attr("data-href");
						$("#review_area").load(href);
					});
					$(document).on('click','.reviewDeleteBtn',function(){
			 			var review_id = $(this).attr('id').replace('delete','');
			 			 $.ajax({
					            cache : false,
					            url : "ReviewDeletePro.rv?review_form_id=<%=userBean.getUser_id()%>&review_id="+review_id, // 요기에
					            type : 'POST', 
					            data : formData, 
					            success : function(data) {
					            	if(data.indexOf("true")==-1){
					            		alert("리뷰삭제에 실패하였습니다")
					            	}
									$("#review_area").load("ReviewList.rv?user_id=<%=editorBean.getUser_id()%>");
					            }, // success 
					    
					            error : function(xhr, status) {
					                alert(xhr + " : " + status);
					            }
					        }); // $.ajax */
			 		})
					
				})
				function reviewWrite() {
	 			var formData = $("#reviewForm").serialize();
		        $.ajax({
		            cache : false,
		            url : "ReviewWritePro.rv", // 요기에
		            type : 'POST', 
		            data : formData, 
		            success : function(data) {
		            	if(data.indexOf("true")==-1){
		            		alert("리뷰작성에 실패하였습니다")
		            	}
						$("#review_area").load("ReviewList.rv?user_id=<%=editorBean.getUser_id()%>");
		            }, // success 
		    
		            error : function(xhr, status) {
		                alert(xhr + " : " + status);
		            }
		        }); // $.ajax */
				return false;
			}
			function reviewUpdateSubmit(){
				var formData = $("#reviewUpdateForm").serialize();
		        $.ajax({
		            cache : false,
		            url : "ReviewUpdatePro.rv", // 요기에
		            type : 'POST', 
		            data : formData, 
		            success : function(data) {
		            	if(data.indexOf("true")==-1){
		            		alert("리뷰수정에 실패하였습니다")
		            	}
						$("#review_area").load("ReviewList.rv?user_id=<%=editorBean.getUser_id()%>");
		            }, // success 
		    
		            error : function(xhr, status) {
		                alert(xhr + " : " + status);
		            }
		        }); // $.ajax */
				return false;
			}
		</script>
	
		</body>
</html>