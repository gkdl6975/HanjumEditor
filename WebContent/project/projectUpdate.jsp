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
	
	
	<style>
		.check_list { display: inline-block; padding:10px;}
		.check_list span { margin-left: 10px;}
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

</head>
<body>
	<jsp:include page="../inc/top.jsp"/>
    <section class="hero-wrap hero-wrap-2" style="background-image: url('images/bg_2.jpg');">
		<div class="overlay"></div>
		<div class="container">
			<div class="row no-gutters slider-text align-items-end justify-content-center">
				<div class="col-md-9 ftco-animate pb-5 text-center">
					<p class="breadcrumbs"><span class="mr-2"><a href="home">Home <i class="fa fa-chevron-right"></i></a></span> <span>Project <i class="fa fa-chevron-right"></i></span></p>
					<h1 id="pageTitle" class="mb-0 bread">프로젝트 수정</h1>
				</div>
			</div>
		</div>
	</section>
<%
ProjectBean project = (ProjectBean)request.getAttribute("project");
String nowPageStr = (String)request.getAttribute("page");
int nowPage = Integer.parseInt(nowPageStr);
if(project != null){
	String[] ref = null;
	if(project.getBoard_creator_cre_ref() != "" && project.getBoard_creator_cre_ref().contains("v=")){
		ref = project.getBoard_creator_cre_ref().split(",");
	}
%>

<section id="pageContent" class="ftco-section bg-light">
	
<div class="login-wrap p-4 p-md-5" style="margin:0px auto; max-width : 800px;">
	<h3 class="mb-4">프로젝트 수정</h3>
	<form action="ProjectUpdatePro.bo" method="post" name="fr_write" id="UpdateForm">
		<input type="hidden" name="board_id" value="<%=project.getBoard_id()%>">
		<input type="hidden" name="page" value="<%=nowPage%>">
		<div class="form-group">
        	<label class="label has-focus" for="Subject">프로젝트 제목</label>
            <input type="text" id="Subject" class="form-control" name="board_subject" required="required" value="<%=project.getBoard_subject()%>"/>
        </div>
        <div class="form-group">
        	<label class="label has-focus" for="Content">프로젝트 소개</label>
            <input type="text" id="Content" class="form-control" name="board_content" value="<%=project.getBoard_content()%>" required="required"/>
        </div>
		<div class="form-group">
        	<label class="label has-focus label-primary">장르</label>
			<div class="check_list"><input type="checkbox" id="genre1" name="board_creator_genre" value="1" <%if(project.getBoard_creator_genre().contains("1")){%>checked<%} %>><span class="checkbox">유튜브</span></div>
			<div class="check_list"><input type="checkbox" id="genre2" name="board_creator_genre" value="2" <%if(project.getBoard_creator_genre().contains("2")){%>checked<%} %>><span class="checkbox">홍보</span></div>
			<div class="check_list"><input type="checkbox" id="genre3" name="board_creator_genre" value="3" <%if(project.getBoard_creator_genre().contains("3")){%>checked<%} %>><span class="checkbox">광고</span></div>
			<div class="check_list"><input type="checkbox" id="genre4" name="board_creator_genre" value="4" <%if(project.getBoard_creator_genre().contains("4")){%>checked<%} %>><span class="checkbox">뮤직비디오</span></div>
			<div class="check_list"><input type="checkbox" id="genre5" name="board_creator_genre" value="5" <%if(project.getBoard_creator_genre().contains("5")){%>checked<%} %>><span class="checkbox">드라마</span></div>
			<div class="check_list"><input type="checkbox" id="genre6" name="board_creator_genre" value="6" <%if(project.getBoard_creator_genre().contains("6")){%>checked<%} %>><span class="checkbox">모션그래픽</span></div>
			<div class="check_list"><input type="checkbox" id="genre7" name="board_creator_genre" value="7" <%if(project.getBoard_creator_genre().contains("7")){%>checked<%} %>><span class="checkbox">기타</span></div>        
		</div>
		<div class="form-group">
            <label  class="label has-focus" for="DetailContent">세부 설명</label>
            <div class="form-control" style="height: auto;">
                <div style="padding-top:10px;"></div>
                	<textarea id="DetailContent" name="board_creator_content_detail" style="padding: 20px; width:100%; min-width:260px; "><%=project.getBoard_creator_content_detail() %></textarea>
            </div>
        </div>
        <div class="form-group">
        	<label class="label has-focus label-primary">동시 녹음 유무</label>
			<div class="check_list"><input type="radio" id="Recording1" name="board_creator_recording" value="1" <%if(project.getBoard_creator_recording() == 1){%>checked<%} %>><span class="checkbox">싱크 작업 필요</span></div>
			<div class="check_list"><input type="radio" id="Recording2"  name="board_creator_recording" value="2" <%if(project.getBoard_creator_recording() == 2){%>checked<%} %>><span class="checkbox">부분적 필요</span></div>
			<div class="check_list"><input type="radio" id="Recording3"  name="board_creator_recording" value="3" <%if(project.getBoard_creator_recording() == 3){%>checked<%} %>><span class="checkbox">원본 녹음 사용</span></div>
		</div>
		<div class="form-group">
        	<label class="label has-focus label-primary">녹화에 이용된 캠</label>
			<div class="check_list"><input type="radio" id="CamNum1" name="board_creator_cam_num" value="1" <%if(project.getBoard_creator_cam_num() == 1){%>checked<%} %>><span class="checkbox">1캠</span></div>
			<div class="check_list"><input type="radio" id="CamNum2" name="board_creator_cam_num" value="2" <%if(project.getBoard_creator_cam_num() == 2){%>checked<%} %>><span class="checkbox">2캠</span></div>
			<div class="check_list"><input type="radio" id="CamNum3" name="board_creator_cam_num" value="3" <%if(project.getBoard_creator_cam_num() == 3){%>checked<%} %>><span class="checkbox">3캠</span></div>
			<div class="check_list"><input type="radio" id="CamNum4" name="board_creator_cam_num" value="4" <%if(project.getBoard_creator_cam_num() == 4){%>checked<%} %>><span class="checkbox">4캠 이상</span></div>
		</div>
		<div class="form-group">
        	<label class="label has-focus label-primary">원본 클립</label>
			<div class="check_list"><input type="radio" id="OriClipNum1" name="board_creator_ori_clip_num" value="1" <%if(project.getBoard_creator_ori_clip_num() == 1){%>checked<%} %>><span class="checkbox">5개 이하</span></div>
			<div class="check_list"><input type="radio" id="OriClipNum2" name="board_creator_ori_clip_num" value="2" <%if(project.getBoard_creator_ori_clip_num() == 2){%>checked<%} %>><span class="checkbox">5개 ~ 20개</span></div>
			<div class="check_list"><input type="radio" id="OriClipNum3" name="board_creator_ori_clip_num" value="3" <%if(project.getBoard_creator_ori_clip_num() == 3){%>checked<%} %>><span class="checkbox">20 ~ 50개</span></div>
			<div class="check_list"><input type="radio" id="OriClipNum4" name="board_creator_ori_clip_num" value="4" <%if(project.getBoard_creator_ori_clip_num() == 4){%>checked<%} %>><span class="checkbox">50개 이상</span></div>
		</div>
		<div class="form-group">
        	<label class="label has-focus label-primary">편집전 런타임</label>
			<div class="check_list"><input type="radio" id="OriLength1" name="board_creator_ori_length" value="1" <%if(project.getBoard_creator_ori_length() == 1){%>checked<%} %>><span class="checkbox">10분 이하</span></div>
			<div class="check_list"><input type="radio" id="OriLength2" name="board_creator_ori_length" value="2" <%if(project.getBoard_creator_ori_length() == 2){%>checked<%} %>><span class="checkbox">30분 이하</span></div>
			<div class="check_list"><input type="radio" id="OriLength3" name="board_creator_ori_length" value="3" <%if(project.getBoard_creator_ori_length() == 3){%>checked<%} %>><span class="checkbox">1시간 이하</span></div>
			<div class="check_list"><input type="radio" id="OriLength4" name="board_creator_ori_length" value="4" <%if(project.getBoard_creator_ori_length() == 4){%>checked<%} %>><span class="checkbox">5시간 이하</span></div>
			<div class="check_list"><input type="radio" id="OriLength5" name="board_creator_ori_length" value="5" <%if(project.getBoard_creator_ori_length() == 5){%>checked<%} %>><span class="checkbox">5시간 이상</span></div>
			<div class="check_list"><input type="radio" id="OriLength6" name="board_creator_ori_length" value="6" <%if(project.getBoard_creator_ori_length() == 6){%>checked<%} %>><span class="checkbox">정확히 알 수 없음</span></div>
		</div>
		<div class="form-group">
        	<label class="label has-focus label-primary">편집후 런타임</label>
			<div class="check_list"><input type="radio" id="EditLength1" name="board_creator_edit_length" value="1" <%if(project.getBoard_creator_edit_length() == 1){%>checked<%} %>><span class="checkbox">5분 이하</span></div>
			<div class="check_list"><input type="radio" id="EditLength2" name="board_creator_edit_length" value="2" <%if(project.getBoard_creator_edit_length() == 2){%>checked<%} %>><span class="checkbox">10분 이하</span></div>
			<div class="check_list"><input type="radio" id="EditLength3" name="board_creator_edit_length" value="3" <%if(project.getBoard_creator_edit_length() == 3){%>checked<%} %>><span class="checkbox">30분 이하</span></div>
			<div class="check_list"><input type="radio" id="EditLength4" name="board_creator_edit_length" value="4" <%if(project.getBoard_creator_edit_length() == 4){%>checked<%} %>><span class="checkbox">1시간 이하</span></div>
			<div class="check_list"><input type="radio" id="EditLength5" name="board_creator_edit_length" value="5" <%if(project.getBoard_creator_edit_length() == 5){%>checked<%} %>><span class="checkbox">1시간 이상</span></div>
		</div>
		<div class="form-group">
        	<label class="label has-focus label-primary">파일의 전달방식</label>
			<div class="check_list"><input type="radio" id="OriTransfer1" name="board_creator_ori_transfer" value="1" <%if(project.getBoard_creator_ori_transfer() == 1){%>checked<%} %>><span class="checkbox">이메일</span></div>
			<div class="check_list"><input type="radio" id="OriTransfer2" name="board_creator_ori_transfer" value="2" <%if(project.getBoard_creator_ori_transfer() == 2){%>checked<%} %>><span class="checkbox">웹하드</span></div>
			<div class="check_list"><input type="radio" id="OriTransfer3" name="board_creator_ori_transfer" value="3" <%if(project.getBoard_creator_ori_transfer() == 3){%>checked<%} %>><span class="checkbox">NAS</span></div>
			<div class="check_list"><input type="radio" id="OriTransfer4" name="board_creator_ori_transfer" value="4" <%if(project.getBoard_creator_ori_transfer() == 4){%>checked<%} %>><span class="checkbox">SMS</span></div>
			<div class="check_list"><input type="radio" id="OriTransfer5" name="board_creator_ori_transfer" value="5" <%if(project.getBoard_creator_ori_transfer() == 5){%>checked<%} %>><span class="checkbox">직접전달</span></div>
		</div>
		<div class="form-group">
            <label class="label has-focus label-primary">예상 단가</label>
            <div class="input_price">
	            <input class="form-control" style="width:30%; display: inline-block;" type="number" id="MinPrice" step="1000" min="0" name="board_creator_cre_min_price" value="<%=project.getBoard_creator_cre_min_price()%>"> 원 ~ 
	            <input class="form-control" style="width:30%; display: inline-block;" type="number" id="MaxPrice" step="1000" min="0" name="board_creator_cre_max_price" value="<%=project.getBoard_creator_cre_max_price()%>"> 원
            </div>
        </div>
        <div id="ref_area" class="form-group">
            <label class="label has-focus label-primary">레퍼런스 링크</label>
            <div class="link_alert d-flex justify-content-end"><button type="button" id="ref_insert" class="btn btn-primary m-l-30">추가</button> <button type="button" id="ref_delete" class="btn btn-light m-l-30">삭제</button></div>
			<%
			if(ref != null){
				for(int i = 0; i < ref.length; i++){
					String refID = ref[i].substring(ref[i].lastIndexOf("v=")+2);
			%>
			<div id="divRef<%=i+1 %>" class="input_link m-tb-15"><input type="hidden" class="form-control" name="board_creator_cre_ref" id="Ref<%=i+1 %>" value="<%=ref[i]%>"></div>
	        <iframe width="560" height="315" src="https://www.youtube.com/embed/<%=refID %>" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></div>
	       
	       <%
				}
			} else {
			%>
			<div id="divRef1" class="input_link m-tb-15"><input type="text" class="form-control" name="board_creator_cre_ref" id="Ref1" placeholder="YOUTUBE 영상링크를 작성해주세요"></div>
			<%
			}
			%>
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
<script type="text/javascript" src="js/refLink.js"></script>	
</body>
</html>


