<%@page import="com.hanjum.user.vo.UserBean"%>
<%@page import="com.hanjum.contract.vo.ContractBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ArrayList<ContractBean> contractMatchList = (ArrayList<ContractBean>)request.getAttribute("contractMatchList");
ArrayList<ContractBean> contractSuccessList = (ArrayList<ContractBean>)request.getAttribute("contractSuccessList");
UserBean userBean = (UserBean)session.getAttribute("userBean");

%>
<style>
.color-primary {
	color: #007bff;
}
.form-group-content a {
	color: #495057;
}
.form-group-content a:hover {
	text-decoration: underline;
}
</style>
<div class="login-wrap p-4 p-md-5">
<h3>계약중인 프로젝트</h3>
<hr>
<%
if(contractMatchList.size() != 0){
	for(int i = 0; i < contractMatchList.size(); i++){
		ContractBean contractBean = contractMatchList.get(i);
		boolean isEditor = false;
		if(userBean.getUser_id().equals(contractBean.getContract_editor())){
			isEditor = true;
		}
	%>
	<div class="form-group">
	<span class="form-group-content">
		<span class="color-primary">[<%if(isEditor){%>편집중<%}else{%>의뢰중<%}%>]</span>
		<a href="Project.bo?board_id=<%=contractBean.getBoard_id()%>">
		<%=contractBean.getBoard_subject() %>
		</a>
	</span>             
</div>
	<%
	}
} else {
%>
<div class="form-group">
	<span class="form-group-content">
		계약중인 프로젝트가 없습니다
	</span>             
</div>
<%
}
%>
<h3>완료한 프로젝트</h3>
<hr>
<%
if(contractSuccessList.size() != 0){
	for(int i = 0; i < contractSuccessList.size(); i++){
		ContractBean contractBean = contractSuccessList.get(i);
		boolean isEditor = false;
		if(userBean.getUser_id().equals(contractBean.getContract_editor())){
			isEditor = true;
		}
	%>
	<div class="form-group">
	<span class="form-group-content">
		<span class="color-primary">[<%if(isEditor){%>편집완료<%}else{%>의뢰완료<%}%>]</span>
		<a href="Project.bo?board_id=<%=contractBean.getBoard_id()%>">
		<%=contractBean.getBoard_subject() %>
		</a>
	</span>             
</div>
	<%
	}
} else {
%>
<div class="form-group">
	<span class="form-group-content">
		완료한 프로젝트가 없습니다
	</span>             
</div>
<%
}
%>