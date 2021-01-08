<%@page import="com.hanjum.notice.vo.NoticeBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
<title>한줌에디터</title>
<link rel="stylesheet" href="../css/style.css">
<style>
.color-primary {
	color: #007bff;
}
.notice_area {
	padding: 20px;
}
.notice_close {
	cursor: pointer;
	float:right;
	display: none;
}
.notice_group:hover>.notice_content>.notice_close {
	display: inline-block;
}
</style>
</head>
<body>
<%
	ArrayList<NoticeBean> noticeNewList = (ArrayList<NoticeBean>)request.getAttribute("noticeNewList");
	ArrayList<NoticeBean> noticeOldList = (ArrayList<NoticeBean>)request.getAttribute("noticeOldList");

%>
<div class="login-wrap p-4 p-md-5">
<h3 class="mb-4">알림</h3>
<div class="form-group">
    <label class="label has-focus"><span class="color-primary">새알림</span></label>
    <hr>
    <div class="notice_area">
    <%
    if(noticeNewList.size()>0){
    	for(int i = 0; i < noticeNewList.size(); i++){
    		NoticeBean noticeBean = noticeNewList.get(i);
    		String noticeContent = "";
    		String user_id = noticeBean.getUser_id();
    		String from_id = noticeBean.getNotice_from_id();
    		switch (noticeBean.getNotice_content()){
    		case 1: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님의 프로젝트를 지원했습니다.</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 2: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님께서 프로젝트를 지원했습니다.</a>"+
    		"<a class='btn btn-primary' style='margin-left:10px;' href='matchNotice.nt?notice_id="+noticeBean.getNotice_id()+"'>수락</a>"+
    		"<a class='btn btn-light' style='margin-left:10px;' href='declineNotice.nt?notice_id="+noticeBean.getNotice_id()+"'>거절</a>"; break;
    		case 3: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님의 프로젝트 요청을 수락하였습니다.</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 4: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님께서 프로젝트 요청을 수락하였습니다.</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 5: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님의 프로젝트 요청을 거절하였습니다.</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 6: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님께서 프로젝트 요청을 거절하였습니다.</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 7: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님의 프로젝트 지원을 취소하였습니다.</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 8: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님께서 프로젝트 지원을 취소하였습니다.</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 9: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님의 프로젝트 계약을 취소하였습니다.</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 10: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님과의 프로젝트 계약을 취소하였습니다.</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 11: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님의 프로젝트 계약을 취소하였습니다.</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 12: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님과의 프로젝트 계약을 취소하였습니다.</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 13: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님의 프로젝트가 완료되었습니다. 여기를 눌러서 리뷰를 남겨보세요</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 14: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님과의 프로젝트가 완료되었습니다.</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 15: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님이 작성한 리뷰를 확인하세요!"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 16: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님이 프로젝트 지원요청을 하셨습니다."+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 17: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님에게 프로젝트 지원요청을 하셨습니다."+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;		
    		
    		}
    	%>
    <div class="form-group-content notice_group">
    <span class="notice_content"><%=noticeContent %></span>
    </div>
    	<%
    	}
    } else {
    	%>
    <div class="form-group-content">새로운 알림이 없습니다.</div>
    	<%
    }
    %>
    </div>
</div>
<div class="form-group">
    <label class="label has-focus">지난알림</label>
    <hr>
    <div class="notice_area">
    <%
    if(noticeOldList.size()>0){
    	for(int i = 0; i < noticeOldList.size(); i++){
    		NoticeBean noticeBean = noticeOldList.get(i);
    		String noticeContent = "";
    		String user_id = noticeBean.getUser_id();
    		String from_id = noticeBean.getNotice_from_id();
    		switch (noticeBean.getNotice_content()){
    		case 1: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님의 프로젝트를 지원했습니다.</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 2: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님께서 프로젝트를 지원했습니다.</a>"+
    	    		"<a class='btn btn-primary' style='margin-left:10px;' href='matchNotice.nt?notice_id="+noticeBean.getNotice_id()+"'>수락</a>"+
    	    		"<a class='btn btn-light' style='margin-left:10px;' href='declineNotice.nt?notice_id="+noticeBean.getNotice_id()+"'>거절</a>"; break;
    		case 3: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님의 프로젝트 요청을 수락하였습니다.</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 4: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님께서 프로젝트 요청을 수락하였습니다.</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 5: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님의 프로젝트 요청을 거절하였습니다.</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 6: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님께서 프로젝트 요청을 거절하였습니다.</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 7: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님의 프로젝트 지원을 취소하였습니다.</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 8: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님께서 프로젝트 지원을 취소하였습니다.</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 9: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님의 프로젝트 계약을 취소하였습니다.</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 10: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님과의 프로젝트 계약을 취소하였습니다.</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 11: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님의 프로젝트 계약을 취소하였습니다.</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 12: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님과의 프로젝트 계약을 취소하였습니다.</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 13: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님의 프로젝트가 완료되었습니다. 여기를 눌러서 리뷰를 남겨보세요</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 14: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님과의 프로젝트가 완료되었습니다.</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 15: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님이 작성한 리뷰를 확인하세요!</a>"+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 16: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님이 프로젝트 지원요청을 하셨습니다."+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		case 17: noticeContent = "<a href='"+noticeBean.getNotice_url()+"'>"+from_id+"님에게 프로젝트 지원요청을 하셨습니다."+
    				"<span class='notice_close' onclick='noticeClose("+noticeBean.getNotice_id()+")'>&times;</span>"; break;
    		}
    	%> 
        <div class="form-group-content notice_group">
	    <span class="notice_content"><%=noticeContent %></span>
	    </div>
    	<%
    	}
    } else {
    	%>
    <div class="form-group-content">지난 알림이 없습니다.</div>
    	<%
    }
    %>
	</div>
</div>

</div>
<script src="js/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
		cache : false,
		url : "updateStatus.nt",
		type : 'POST',
		success : function(data) {
			
		}
	});
})
function noticeClose(notice_id){
	$.ajax({
		cache : false,
		url : "deleteNotice.nt?notice_id="+notice_id,
		type : 'POST',
		success : function(data) {
			$("#member-container").empty();
			$("#member-container").load("getNoticeList.nt");
		}
	});
}
</script>
</body>
</html>