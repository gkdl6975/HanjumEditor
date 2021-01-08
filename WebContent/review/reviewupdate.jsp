<%@page import="com.hanjum.review.vo.ReviewBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <%
//     ReviewBean reviewBean = new ReviewBean();
   	ReviewBean article = (ReviewBean)request.getAttribute("article"); 
    String nowPage = request.getParameter("page");
   %>
<div class="review_item_1">
			<div class="review_content">
				<label class="label has-focus" for="reviewContent">리뷰수정</label>
			    <input type="text" id="reviewContent" style="width: 90%; height: 100px;" class="form-control" name="review_content" value="<%=article.getReview_content() %>" required="required"/>
			</div>		
		</div>
		<div class="review_item_2">
			<div class="flex-col">
				<div class="review_col_content">전문성 
				<span class="float-r m-r-10">
				<input type="range" name="review_speciality" min="0" max="10" step="1" value="<%=article.getReview_speciality() %>" oninput="document.getElementById('value1').innerHTML=this.value;" required="required">
       			<span id="value1"></span>
       			</span>
				</div>
				<div class="review_col_content">만족도
				<span class="float-r m-r-10">
				<input type="range" name="review_satisfaction" min="0" max="10" step="1" value="<%=article.getReview_satisfaction() %>" oninput="document.getElementById('value2').innerHTML=this.value;" required="required">
       			<span id="value2"></span>
       			</span>
       			</div>
				<div class="review_col_content">적극성
				<span class="float-r m-r-10">
				<input type="range" name="review_positivity" min="0" max="10" step="1" value="<%=article.getReview_positivity() %>" oninput="document.getElementById('value3').innerHTML=this.value;" required="required">
       			<span id="value3"></span>
       			</span>
       			</div>
				<div class="review_col_content">소통
				<span class="float-r m-r-10">
				<input type="range" name="review_communication" min="0" max="10" step="1" value="<%=article.getReview_communication() %>" oninput="document.getElementById('value4').innerHTML=this.value;" required="required">
       			<span id="value4"></span>
       			</span>
       			</div>
       			<div class="review_col_content">
       			<input type="hidden" name="review_id" value="<%=article.getReview_id()%>">
       			<input type="hidden" name="page" value="<%=nowPage%>">
       			<span class="float-r m-r-10">
       			<input type="submit" class="btn btn-primary" value="수정"/>
       			</span>
       			</div>
			</div>
		</div>