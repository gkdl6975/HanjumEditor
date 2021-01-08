<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="pf_form" action="UserPortfolioInsert.uo" method="post">
<div class="form-group" style="width:49%; display: inline-block;">
	<span class="form-group-content">
	<label class="label" for="pf_link">포트폴리오 링크</label>
    <input type="text" id="pf_link" class="form-control" name="editor_pf_link" required="required"/>
   </span>             
</div>
<div class="form-group" style="width:49%; display: inline-block;">
	<span class="form-group-content">
	<label class="label" for="pf_category">장르</label>
	<select id="pf_category" class="form-control" name="editor_pf_category" required="required">	
    <option value=""></option>
    <option value="1">유튜브</option>
    <option value="2">홍보</option>
    <option value="3">광고</option>
    <option value="4">뮤직비디오</option>
    <option value="5">드라마</option>
    <option value="6">모션그래픽</option>
    <option value="7">기타</option>
    </select>
   </span>             
</div>
    
<div class="form-group">
	<span class="form-group-content">
	<label class="label" for="pf_subject">포트폴리오 제목</label>
    <input type="text" id="pf_subject" class="form-control" name="editor_pf_subject" required="required"/>
   </span>             
</div>
</form>