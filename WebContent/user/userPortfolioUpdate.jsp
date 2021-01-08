<%@page import="com.hanjum.user.vo.PortfolioBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    PortfolioBean portfolioBean = (PortfolioBean)request.getAttribute("portfolioBean");
    if(portfolioBean != null){
    %>
<form id="pf_form_update" action="UserPortfolioUpdate.uo" method="post">
<input type="hidden" name="editor_pf_id" value="<%=portfolioBean.getEditor_pf_id()%>">
<div class="form-group" style="width:49%; display: inline-block;">
	<span class="form-group-content">
	<label class="label has-focus" for="pf_link">포트폴리오 링크</label>
    <input type="text" id="pf_link" class="form-control" name="editor_pf_link" required="required" value="<%=portfolioBean.getEditor_pf_link()%>"/>
   </span>             
</div>
<div class="form-group" style="width:49%; display: inline-block;">
	<span class="form-group-content">
	<label class="label has-focus" for="pf_category">장르</label>
	<select id="pf_category" class="form-control" name="editor_pf_category" required="required">	
    <option value=""></option>
    <option value="1" <%if(portfolioBean.getEditor_pf_category().equals("1")){%>selected<%} %>>유튜브</option>
    <option value="2" <%if(portfolioBean.getEditor_pf_category().equals("2")){%>selected<%} %>>홍보</option>
    <option value="3" <%if(portfolioBean.getEditor_pf_category().equals("3")){%>selected<%} %>>광고</option>
    <option value="4" <%if(portfolioBean.getEditor_pf_category().equals("4")){%>selected<%} %>>뮤직비디오</option>
    <option value="5" <%if(portfolioBean.getEditor_pf_category().equals("5")){%>selected<%} %>>드라마</option>
    <option value="6" <%if(portfolioBean.getEditor_pf_category().equals("6")){%>selected<%} %>>모션그래픽</option>
    <option value="7" <%if(portfolioBean.getEditor_pf_category().equals("7")){%>selected<%} %>>기타</option>
    </select>
   </span>             
</div>
    
<div class="form-group">
	<span class="form-group-content">
	<label class="label has-focus" for="pf_subject">포트폴리오 제목</label>
    <input type="text" id="pf_subject" class="form-control" name="editor_pf_subject" required="required" value="<%=portfolioBean.getEditor_pf_subject()%>"/>
   </span>
   <a id="portfolioUpdateSubmit">수정완료</a>             
</div>
</form>
<%

}
%>