<%@page import="com.hanjum.vo.Constant"%>
<%@page import="com.hanjum.vo.PageInfo"%>
<%@page import="com.hanjum.help.vo.HelpBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <style>
		.form-group>div { height: 0px; overflow: hidden; transition: 2.5s all;}
		.form-group:hover>div {
			height: auto;
		}
		.color-primary {
			color: #007bff;
		}
	</style>
    <%
    ArrayList<HelpBean> list = (ArrayList<HelpBean>)request.getAttribute("list");
    PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
    int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
	int listCount = pageInfo.getListCount();
    %>
	<div class="login-wrap p-4 p-md-5" style="width:100%;">
		<h3>자주 묻는 질문</h3>
		<hr>
		<%
		if(list.size() != 0) {
			for(int i = 0; i < list.size(); i++){
				HelpBean helpBean = list.get(i);
		%>
		<div class="form-group">
		<span class="form-group-content color-primary">
			<%=helpBean.getHelp_question() %>
		</span>
		<div class="form-group-content">
			<%=helpBean.getHelp_answer() %>
		</div>             
		</div>
		
		<%
			} %>
			
			
		<div class="row mt-5">
			<div class="col">
				<div class="block-27 text-center">
					<ul>
					<%
					int pageSize = 10;
					String search = request.getParameter("search");
					String pageUrl = "CenterHelp.hp?";
					if(search != null){
						pageUrl = "CenterSearchHelp.hp?search="+search+"&";
					} 
					
					if(startPage > pageSize){ %>
						<li><a class="page-move" data-href="<%=pageUrl%>page=<%=startPage-pageSize %>">&lt;</a></li>
					<% 
					}
					for(int i=startPage; i<=endPage; i++){
						if(i == nowPage){
					%>
						<li class="active"><span><%=i %></span></li>
					<% } else { %>
						<li><a class="page-move" data-href="<%=pageUrl%>page=<%=i%>"><%=i %></a></li>
					<% } 
					}
					if(endPage < maxPage){ 
					%>
						<li><a class="page-move" data-href="<%=pageUrl%>page=<%=startPage+pageSize %>">&gt;</a></li>
					<%
					}
					%>
					</ul>
				</div>
			</div>
		</div>	
			<%
		} else {
			%>
		<div class="form-group">
		<span class="form-group-content">
			자주 묻는 질문이 없습니다.
		</span>
		</div>	
			<%
		}
		%>
   	</div>