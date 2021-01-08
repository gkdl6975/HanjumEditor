<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/default.css" rel="stylesheet">
<script src="js/jquery-3.5.1.js"></script>
<script src="js/topMenu.js"></script>
<title>Insert title here</title>
</head>
<body>
<div id="wrap">
<jsp:include page="../inc/top.jsp"/>
<h1>멀로 가입할래?</h1>
<input type="button" value="일반회원" onclick="location.href='UserInsertForm.uo'">
<input type="button" value="편집자회원" onclick="location.href='UserInsertEditorForm.uo'">
<jsp:include page="../inc/bottom.jsp"/>
</div>
</body>
</html>