<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String rePath = request.getServletPath();
if(session.getAttribute("user_id") == null){
	%>
		<script type="text/javascript">
			var confirmLogin = confirm('로그인이 필요한 페이지입니다. 로그인 하시겠습니까?');
			if(confirmLogin == true){
				location.href = 'UserLoginForm.uo?rePath=<%=rePath%>';
			}
		</script>
	<%
}

%>