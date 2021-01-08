<%@page import="com.hanjum.user.vo.UserBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.hanjum.chat.vo.ChatBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ArrayList<ChatBean> list = (ArrayList<ChatBean>)request.getAttribute("list");
UserBean userBean = (UserBean)session.getAttribute("userBean");
String user_id = userBean.getUser_id();
%>
					<%
					if(list != null){

						
					for(int i = 0; i < list.size(); i++) { 
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
						String date = sdf.format(list.get(i).getChat_date());
						%>
					<div class="item on <%if(user_id.equals(list.get(i).getChat_to_id())){%>mymsg<%}%>">
					<div class="msgBox">
	<!-- 					<div class="msgBox"> -->
							<p class="msg">
								<%=list.get(i).getChat_content()%>
							</p>
							<span class="time">
								<%=date %>
							</span>
	<!-- 					</div> -->
					</div>
	<%-- 				<%=list.get(i).getChat_creator_id()%> --%>
					</div>
					<%
						}
					}
					%>