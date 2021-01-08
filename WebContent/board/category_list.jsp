<%@page import="java.util.ArrayList"%>
<%@page import="com.hanjum.category.vo.CategoryBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    ArrayList<CategoryBean> categoryList =(ArrayList<CategoryBean>) request.getAttribute("categoryList");
	
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function add_txt(){
	 document.getElementById("texts").innerHTML +=
	"<form action ='CategoryWritePro.cg' method='post'>"
	 + "<input type='text' name='category_content'>"
	 + "<input type='submit' value='추가'><br>"
	 + "</form>"
	}
function insert(){
	location.href="CategoryWritePro.cg";
}
</script>
</head>
<body>
<%for(int i= 0; i < categoryList.size(); i++){
	CategoryBean categoryBean = categoryList.get(i);
	int category_id= categoryBean.getCategory_id();
	String category_content = categoryBean.getCategory_content();%>
	<form method="post">
	<input type="submit" value="↑" formaction="idUp.cg"><input type="submit" value="↓" formaction="idDown">
	<input type="text" value="<%=category_id %>. <%=category_content%>" name="category_content" 
	onclick="this.value=''">
	<input type="hidden" name="category_id" value="<%=category_id%>">
	<input type='submit' value='삭제' formaction="CategoryDelete.cg">
	<input type="submit" value='수정' formaction="CategoryUpdate.cg"><br>
	</form>
	<% 
	}%>
	<input type="button" value="추가" onclick="add_txt()"><br>
	<div id="texts">
	</div>

</body>
</html>