<%@page import="com.hanjum.review.vo.ReviewBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript">
</script>
</head>
<body>
	<section id="writeForm">
		<h1>리뷰등록</h1>
		<form action="ReviewWritePro.bo" method="post" name="reviewForm">
		아이디(리뷰작성자):<input type="text" id="review_form_id" name="review_form_id">
		아이디(리뷰당하는사람):<input type="text" id="user_id" name="user_id">	
    <div>
        <label>전문성</label>
        <input type="range" name="review_speciality" min="0" max="10" step="1" value="0" oninput="document.getElementById('value1').innerHTML=this.value;" required="required">
        <span id="value1"></span>
    </div>
    <div>
        <label>만족도</label>
        <input type="range" name="review_satisfaction" min="0" max="10" step="1" value="0" oninput="document.getElementById('value2').innerHTML=this.value;" required="required">
        <span id="value2"></span>
    </div>
    <div>
        <label>적극성</label>
        <input type="range" name="review_positivity" min="0" max="10" step="1" value="0" oninput="document.getElementById('value3').innerHTML=this.value;" required="required">
        <span id="value3"></span>
    </div>
    <div>
        <label>소통</label>
        <input type="range" name="review_communication" min="0" max="10" step="1" value="0" oninput="document.getElementById('value4').innerHTML=this.value;" required="required">
        <span id="value4"></span>
    </div>
    <div>
    	<textarea name="review_content" id="review_content" rows="10" cols="10"></textarea>
    </div>
    <input type="submit" value="등록">
</form>
	</section>
</body>
</html>