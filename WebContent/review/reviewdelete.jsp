<%@page import="com.hanjum.review.vo.ReviewBean"%>
<%@page import="com.hanjum.review.service.ReviewService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 	ReviewBean reviewBean = new ReviewBean(); 
// 	int review_id = reviewBean.getReview_id();
	int review_id = Integer.parseInt(request.getParameter("review_id")); 
	String nowPage = request.getParameter("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h2>리뷰 삭제</h2>
	<section id="passForm">
<%-- 	<form action="ReviewDelete.bo?review_id=<%=review_id%>&page=<%=nowPage %>" name="deleteForm" method="post"> --%>
		<form action="ReviewDeletePro.bo" name="deleteForm" method="post">
			<input type="hidden" name="review_id" value="<%=review_id%>" />
			<input type="hidden" name="page" value="<%=nowPage %>" />
			<table>
				<tr>
					<td><label>리뷰어: </label></td>
					<td><input type="text" name="review_form_id" required="required"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="삭제">&nbsp;&nbsp;
						<input type="button" value="돌아가기" onclick="history.back()">
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>





