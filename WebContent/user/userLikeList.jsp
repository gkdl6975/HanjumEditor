<%@page import="com.hanjum.user.vo.LikeBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ArrayList<LikeBean> likeBeans = (ArrayList<LikeBean>)request.getAttribute("likeList");
%>
<div class="like_list_container p-4">
	<p>좋아요 한 편집자 <%=likeBeans.size() %>명</p>
	<hr>
	<%
	if(likeBeans.size()>0){
		for(int i = 0; i < likeBeans.size(); i++){
			LikeBean likeBean = likeBeans.get(i);
		%>
		<div class="like-group m-tb-10" onclick="applySend('<%=likeBean.getUser_name()%>','<%=likeBean.getUser_id()%>')">
			<div class="like_photo"><img alt="프로필사진" src="editorUserPhotoUpload/<%=likeBean.getEditor_photo() %>" style="width: 50px;"/></div>
			<span class="like_name"><%=likeBean.getUser_name() %></span>
			<span class="like_btn">&gt;</span>
	    </div>
	    <%
		}
	} else {
		%>
		관심 등록한 편집자가 없습니다.
		<%
	}
    %>
</div>