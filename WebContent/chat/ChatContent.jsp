<%@page import="com.hanjum.contract.vo.ContractBean"%>
<%@page import="com.hanjum.user.vo.UserBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.hanjum.chat.vo.ChatBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%

UserBean userBean = (UserBean)session.getAttribute("userBean");
String user_id = userBean.getUser_id();
ContractBean contractBean = (ContractBean)request.getAttribute("contractBean");
String from_id = "";
if(user_id.equals(contractBean.getContract_creator())){
	from_id = contractBean.getContract_editor();
} else {
	from_id = contractBean.getContract_creator();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>한줌에디터</title>
	<script>
// 		$(function(){
// 			$("input[type='text']").keypress(function(e){  //엔터키를 눌렀을때 글이 들어가게 하는 JQury 임.
// 				if(e.keyCode == 13 && $(this).val().length){
// 					var _val = $(this).val();
// 					var _class = $(this).attr("class");
// 					$(this).val('');
// 					var _tar = $(".chat_wrap .inner").append('<div class="item '+_class+'"><div class="box"><p class="msg">'+_val+'</p><span class="time">'+currentTime()+'</span></div></div>');
// 				}
				
// 				setTimeout(function(){
// 					$(".chat_wrap .inner").find(".item:last").addClass("on");
// 				},10)
				
// 				$(".chat_wrap .inner").stop().animate({scrollTop:$(".chat_wrap .inner").height()},500);
				
// 			})
// 		});
		var timeout;
		var board_id = <%=contractBean.getBoard_id()%>;
		chatStart(board_id);
		$(function(){
			$("#chat_text_form").focus();
			
			$(document).on('click', '#chat_list', function(){
				$('.chat_side').css('width','100%');
				$('.chat_content').css('width','0px');
				$("#chat_content").empty();
				clearTimeout(timeout);
			})
		})
// 		var currentTime = function(){    //시간 실시간으로.
// 			var date = new Date();
// 			var hh = date.getHours();
// 			var mm = date.getMinutes();
// 			var apm = hh > 12 ? "오후":"오전";
// 			var ct = apm+" "+hh+":"+mm+"";
// 			return ct;
// 		};
		function chatSand() {
			var formData = $("#ChatWritePro").serialize();
	        
			$.ajax({
	            cache : false,
	            url : "ChatWritePro.ch", // 요기에
	            type : 'POST', 
	            data : formData, 
	            success : function(data) {
	        		$("#inner").load("ChatInner.ch?board_id="+board_id);
	            	$("#chat_text_form").val("");
	            }, // success 
	    
	            error : function(xhr, status) {
	                alert(xhr + " : " + status);
	            }
	        }); // $.ajax */
	        return false;
	    }
		function chatStart(board_id){
        	$("#inner").load("ChatInner.ch?board_id="+board_id);
        	$("#inner").scrollTop($("#inner")[0].scrollHeight);
    		timeout = setTimeout("chatStart('"+board_id+"')", 1000);
    	}
	</script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	


</head>
<body>
  <div class="chat_wrap">
  		<div class="chat-menu">
  		<div class="p-2"><span class="btn btn-light" id="chat_list">목록</span></div>
  		</div>
	<form action="ChatWritePro.ch" method="post" id="ChatWritePro" onsubmit="return chatSand()">
			<div class="inner" id="inner">
<!-- 				채팅창 들어올 공간 -->
				</div>
	
						<input type="hidden" name="chat_to_id" value="<%=user_id%>">
						<input type="hidden" name="chat_from_id" value="<%=from_id%>">
						<input type="hidden" name="board_id" value="<%=contractBean.getBoard_id()%>">	
			<input type="text" id="chat_text_form" class="mymsg" name="Chat_content">
			<input type="submit" id="chat_content" value="전송">
			
	
		</form>
		</div>
</body>
</html>