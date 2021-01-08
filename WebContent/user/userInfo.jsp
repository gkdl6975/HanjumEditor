<%@page import="java.util.HashMap"%>
<%@page import="com.hanjum.user.vo.EditorBean"%>
<%@page import="com.hanjum.user.vo.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	UserBean userBean = (UserBean)request.getAttribute("userBean");
	HashMap<String, String> editorInfo = (HashMap<String, String>)request.getAttribute("editorInfo");
	String user_type = null;
	switch (userBean.getUser_type()){
	case 1:
		user_type = "일반 회원";
		break;
	case 2:
		user_type = "편집자";
		break;
	default:
		user_type = "관리자";
	}
	int successCount = 0;
	if(request.getAttribute("successCount")!=null) {
		successCount = Integer.parseInt(request.getAttribute("successCount")+"");
	}
	int noticeCount = (int)request.getAttribute("noticeCount");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/util.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/profile-photo.js"></script>
<title>My</title>
<style>
.color-primary {
	color: #007bff;
}
.form-group-content{display:block;}
.form-group-content progress{
	height: 20px;
	padding: 15px 0px;
}
.form-group-content:hover > span {
	font-weight: bold;
}
.toggle_input {
	position: absolute; left: -1000%;
}
.toggle_label {
	position: relative !important;
	top:0 !important; left:0 !important;
	display: inline-block; width:50px; height: 24px;
	background: #d3d3d3 !important;
	border-radius: 24px; 
}
.toggle_label:after {
	content: "";
	position: absolute; top:50%; left:2px; transform: translateY(-50%); 
	width: 20px; height: 20px;
	border-radius: 100%; box-shadow: 1px 3px 4px rgba(0,0,0,.1);
	background: #fff; transition:all .4s;
}
input[name=editor_status]:checked + .toggle_label {background: #007bff !important;}
input[name=editor_status]:checked + .toggle_label:after { left:28px;}
.toggle_label span { display: none;}
.edit span { display:none;}
.edit-form {
			width: 30%;
			display: inline-block;
		}
.edit { width:20px; height:20px; opacity: 0.6; display: none; cursor: pointer; background-image: url(images/pen.png); background-size: 20px;}
.form-group-content {overflow: auto;}
.profile_photo {max-width: 300px; max-height: 300px; width:80%;}
#editor_photo_form { display:none; }
.new-count {position: relative;}
.new-count-content {position:absolute; top:-10px; left:49px; display:inline-block; line-height:17px; text-align:center; width: 16px; height: 16px; font-size: 13px; color:#fff; font-weight:500; background: #f00; border-radius: 100%;}
.user_lv { font-weight: bold;}
</style>
</head>
<body>
	<div class="login-wrap p-4 p-md-5">
		
	<%
		if(editorInfo != null){
			
			%>
			<div class="form-group">
			 <span class="form-group-content">
			 	<img id="photo" class="profile_photo" data-src="<%=editorInfo.get("photo") %>" src="editorUserPhotoUpload/<%=editorInfo.get("photo") %>" title="profile">
			 	<span id="editor_photo"></span>
			 	<span id="edit_photo" class="edit edit_ready m-l-10" style="vertical-align: bottom;"><span>수정</span></span>
			 </span>             
             </div>
			<%
		}
	%>
              <h3 class="mb-4">My <span id="USER_ID" class=".color-primary m-l-20"><%=userBean.getUser_id()%></span><span class="float-r m-r-10"><%=user_type %><span></span></span></h3>
             <div class="form-group">
             <span class="form-group-content" style="overflow: visible;">
             <span class="user_lv" style="
	                <%
	                if(userBean.getUser_level() >= 0 && userBean.getUser_level() < 20){
	                	%>color:#cc00cc<%
	                } else if(userBean.getUser_level() >= 20 && userBean.getUser_level() < 40){
	                	%>color:#000066<%
	                } else if(userBean.getUser_level() >= 40 && userBean.getUser_level() < 50){
	                	%>color:#6666ff<%
	                } else if(userBean.getUser_level() >= 50 && userBean.getUser_level() < 60){
	                	%>color:#66cc66<%
	                } else if(userBean.getUser_level() >= 60 && userBean.getUser_level() < 70){
	                	%>color:#e6e600<%
	                } else if(userBean.getUser_level() >= 70 && userBean.getUser_level() < 80){
	                	%>color:#ffcc00<%
	                } else if(userBean.getUser_level() >= 80 && userBean.getUser_level() < 90){
	                	%>color:#e62e00<%
	                } else if(userBean.getUser_level() >= 90){
	                	%>background-image: linear-gradient(to left, violet, indigo, blue, green, yellow, orange, red); -webkit-background-clip: text; color: transparent;<%
	                } else {
	                	%>color:#eeeeee<%
	                }
	                %>
	                
	                ;">Lv <%=userBean.getUser_level()%></span>
             
             <input type="hidden" id="user_id" name="user_id" value="<%=userBean.getUser_id() %>">

             <span class="color-primary m-l-10" id="user_name"><%=userBean.getUser_name()%></span><span id="edit_user_name" class="edit edit_ready m-l-10"><span>수정</span></span>
             <span class="float-r m-r-10">
             	<span class="new-count"><button class="btn btn-white" onclick="location.href='My.uo?fr=chat'">채팅</button>
             	</span>
             	<span class="new-count"><button class="btn btn-primary" onclick="location.href='My.uo?fr=notice'">알림</button>
             		<%
             		if(noticeCount>0){
             		%>
             		<span class="new-count-content"><%=noticeCount %></span>
             		<%
             		}
             		%>
             	</span>
             	
             </span>
             </span>
             </div> 
             <div class="form-group">
			 <span class="form-group-content">
			 	포인트 <span class="float-r m-r-10"><a data-href="#">0</a> p</span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	Exp <span class="float-r m-r-10"><progress value="<%=userBean.getUser_lv_exp() %>" max="<%=userBean.getUser_level()*20 %>" title="경험치 <%=userBean.getUser_lv_exp() %> / <%=userBean.getUser_level()*20 %>"></progress></span>
			 </span>             
             </div>
             <%
             if(request.getAttribute("successCount")!=null){
            	 %>
           	 <div class="form-group">
			 <span class="form-group-content">
			 	총 거래건수 <span class="float-r m-r-10"><a data-href="#"><%=successCount %></a>건</span>
			 </span>             
             </div>
            	 <%
             }
             %>
             <%
             if(editorInfo != null){
            	 int readyStatus = 0;
            		String readyStatusStr = request.getAttribute("readyStatus")+"";
            		if(readyStatusStr != null){
            			readyStatus = Integer.parseInt(readyStatusStr);
            		}
            		%>
            		<div class="form-group">
       			 <span class="form-group-content">
       			 	팔로워 수 <span class="float-r m-r-10"><span class="color-primary"><%=editorInfo.get("follower") %></span>명</span>
       			 </span>             
                    </div>
                    <%
            	 %>
             <input type="hidden" id="editor_min_price_val" name="editor_min_price_val" value="<%=editorInfo.get("min_price_val") %>">
             <input type="hidden" id="editor_max_price_val" name="editor_max_price_val" value="<%=editorInfo.get("max_price_val") %>">
             <div class="form-group">
			 <span class="form-group-content">
			 	내 평점 <span class="float-r m-r-10"><span class="color-primary"><%=userBean.getUser_score() %></span> 점</span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	접속 횟수 <span class="float-r m-r-10"><span class="color-primary"><%=userBean.getUser_login_count() %></span> 회</span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	활동 상태 <span class="float-r m-r-10"><span class="color-primary">
			 	<%
			 	if(readyStatus == 1){
				%>			 		
			 	<input type="checkbox" class="toggle_input" name="editor_status" id="editor_status" <%if(editorInfo.get("status").equals("1")){ %>checked="checked"<%} %>><label class="toggle_label" for="editor_status"><span>상태</span></label>
				<%
			 	} else {
			 	%>
			 	<a href="EditorWrite.bo" class="btn btn-primary">포트폴리오 등록하기</a>
			 	<%
			 	}
			 	%>
			 	</span>
			 </span>   
			 </span>          
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	이용 프로그램 <span class="float-r m-r-10"><span id="editor_program" class="color-primary"><%=editorInfo.get("program") %></span></span><span id="edit_program" class="edit edit_ready m-r-10 float-r"><span>수정</span></span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	최종 납품 해상도 <span class="float-r m-r-10"><span id="editor_solution" class="color-primary"><%=editorInfo.get("solution") %></span></span><span id="edit_solution" class="edit edit_ready m-r-10 float-r"><span>수정</span></span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	작업 가능 항목 <span class="float-r m-r-10"><span id="editor_inventory" class="color-primary"><%=editorInfo.get("inventory") %></span></span><span id="edit_inventory" class="edit edit_ready m-r-10 float-r"><span>수정</span></span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	납품 파일 공유 방식 <span class="float-r m-r-10"><span id="editor_upload" class="color-primary"><%=editorInfo.get("upload") %></span></span><span id="edit_upload" class="edit edit_ready m-r-10 float-r"><span>수정</span></span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	실물 미팅 <span class="float-r m-r-10"><span id="editor_meeting" class="color-primary"><%=editorInfo.get("meeting") %></span></span><span id="edit_meeting" class="edit edit_ready m-r-10 float-r"><span>수정</span></span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	선호 작업 유형 <span class="float-r m-r-10"><span id="editor_fort" class="color-primary"><%=editorInfo.get("fort") %></span></span><span id="edit_fort" class="edit edit_ready m-r-10 float-r"><span>수정</span></span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	샘플 작업 가능 유무 <span class="float-r m-r-10"><span id="editor_sample" class="color-primary"><%=editorInfo.get("sample") %></span></span><span id="edit_sample" class="edit edit_ready m-r-10 float-r"><span>수정</span></span>
			 </span>             
             </div>
             <div class="form-group ">
			 <span class="form-group-content">
			 	예상 단가 <span class="float-r m-r-10"><span id="editor_price" class="color-primary"><%=editorInfo.get("min_price") %> ~ <%=editorInfo.get("max_price") %></span></span><span id="edit_price" class="edit edit_ready m-r-10 float-r"><span>수정</span></span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	거주지 <span class="float-r m-r-10"><span id="editor_address" class="color-primary"><%=editorInfo.get("address") %></span></span><span id="edit_address" class="edit edit_ready m-r-10 float-r"><span>수정</span></span>
			 </span>             
             </div>
             <div id="portfolioArea">
<!--                포트폴리오 공간              -->
             </div>
            	 <%
             } else { 
            	 %>
            	 <div class="form-group">
				 <span class="form-group-content float-r m-r-10" style="display: inline-block;">
				 	<a href="Logout.uo" class="m-l-10">로그아웃</a>
				 </span>            
				 <span class="form-group-content float-r m-r-10" style="display: inline-block;">
				 	<a href="My.uo?fr=info" class="m-l-10">회원정보 더보기</a>
				 </span>    
	             </div>
            	 <%
             }
             %>
             <div style="clear: both;"></div>
             <div class="form-group">
			 <span class="form-group-content">
			 	<span class="float-r m-r-10">
			 	<%
             		if(userBean.getUser_type() == 0){
            	 %>
			 	<a href="UserManage.uo" target="_blank" class="m-l-10">관리</a>
	             <%
	             	}
	             %>
			 	</span>
			 </span>             
             </div>
     </div>
     <script type="text/javascript">
     	$(document).ready(function(){
     		$("#portfolioArea").load("UserPortfolioList.uo");
     	})
     </script>
</body>
</html>

