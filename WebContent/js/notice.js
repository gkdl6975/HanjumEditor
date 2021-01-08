/**
 * 
 */

	
	// 수락 시 주소 이동
	// mouseover - click  <-OldNotice
	
	$(document).ready(function () {
		var user_id = $('#user_id').val();
		
		$('#btn').mouseover(function () {
			$.getJSON('getNewNotice.nt?user_id='+user_id, function (rdata) {
				$('#new_notice_list *').remove(); // 초기화
				$.each(rdata, function (index, item) {
					
					if(item.notice_content == 1){
						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="updateStatus.nt?notice_id='+item.notice_id+'"><b>'+item.user_id+' </b>님 '+item.notice_from_id+' 님께 프로젝트를<br> 성공적으로 요청하였습니다</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					else if(item.notice_content == 2 && item.notice_read == 0){
						$('#new_notice_list').append('<tr><td id="new_notice_td">'+item.notice_from_id+'님이 프로젝트를 요청하였습니다 (희망금액 : '+item.notice_price+')</a><br><div id="btns"><a href="matchNotice.nt?notice_id='+item.notice_id+'" id="accept">수락</a>　*　<a href="declineNotice.nt?notice_id='+item.notice_id+'"id="decline">거절</a></div><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					else if(item.notice_content == 3){
						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="updateStatus.nt?notice_id='+item.notice_id+'"><b>'+item.user_id+'</b> 님, '+item.notice_from_id+' 님의 프로젝트 요청을 수락하였습니다</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					else if(item.notice_content == 4){
						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="updateStatus.nt?notice_id='+item.notice_id+'">'+item.notice_from_id+' </b>님이 프로젝트 요청을 수락하였습니다</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					else if(item.notice_content == 5){
						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="updateStatus.nt?notice_id='+item.notice_id+'"><b>'+item.user_id+' </b>님, '+item.notice_from_id+'의 프로젝트 요청을 거절하였습니다</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					else if(item.notice_content == 6){
						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="updateStatus.nt?notice_id='+item.notice_id+'">'+item.notice_from_id+' </b>님이 프로젝트 요청을 거절하였습니다</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
//					else if(item.notice_content == 7){
//						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="updateStatus.nt?notice_id='+item.notice_id+'">'+item.user_id+' </b>님이 '+item.board_id+' 프로젝트 지원을 취소하였습니다</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
//					}
					else if(item.notice_content == 8){
						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="updateStatus.nt?notice_id='+item.notice_id+'">'+item.user_id+' </b>님, '+item.notice_from_id+' 님께서 '+item.board_id+' 프로젝트 지원을 취소하였습니다</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					else if(item.notice_content == 9){
						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="updateStatus.nt?notice_id='+item.notice_id+'">'+item.user_id+' </b>님 '+item.board_id+' 프로젝트 계약을 취소하였습니다</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					else if(item.notice_content == 10){
						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="updateStatus.nt?notice_id='+item.notice_id+'">'+item.user_id+' </b>님, '+item.notice_from_id+' 님께서 '+item.board_id+' 프로젝트 계약을 취소하였습니다</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					else if(item.notice_content == 11){
						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="updateStatus.nt?notice_id='+item.notice_id+'">'+item.user_id+' </b>님 '+item.board_id+' 프로젝트 계약을 취소하였습니다</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					else if(item.notice_content == 12){
						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="updateStatus.nt?notice_id='+item.notice_id+'">'+item.user_id+' </b>님, '+item.notice_from_id+' 님께서 '+item.board_id+' 프로젝트 계약을 취소하였습니다</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					else if(item.notice_content == 13){
						// 크리에이터가 리뷰쓰러감..? 
						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="updateStatus.nt?notice_id='+item.notice_id+'">'+item.user_id+' </b>님, 축하합니다! '+item.board_id+' 프로젝트가 완성되었습니다.</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> 리뷰주소값?(ref num?)</a><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					else if(item.notice_content == 14){
						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="updateStatus.nt?notice_id='+item.notice_id+'">'+item.user_id+' </b>님, 축하합니다! '+item.board_id+' 프로젝트가 완성되었습니다.</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					else if(item.notice_content == 15){
						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="updateStatus.nt?notice_id='+item.notice_id+'">'+item.user_id+' </b>님, '+item.notice_from_id+' 님이 작성한 리뷰를 확인해보세요!</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					
					
				});	
			}); //getJSON
		}); //click - 새알람
		
		$('#get_notice_btn').mouseover(function () {
			$.getJSON('getOldNotice.nt?user_id='+user_id , function (rdata) {
				$('#old_notice_list *').remove();
				$.each(rdata, function (index, item) {
					
					if(item.notice_content == 1){
						$('#old_notice_list').append('<tr><td id="old_notice_td"><b>'+item.user_id+' </b>님 '+item.notice_from_id+' 님께 프로젝트를<br> 성공적으로 요청하였습니다<br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					else if(item.notice_content == 2){
						$('#old_notice_list').append('<tr><td id="old_notice_td">'+item.notice_from_id+'님이 프로젝트를 요청하였습니다  (희망금액 : '+item.notice_price+')<br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					else if(item.notice_content == 3){
						$('#old_notice_list').append('<tr><td id="old_notice_td"><b>'+item.user_id+' 님, '+item.notice_from_id+' 님의 프로젝트를 요청을 수락하였습니다<br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					else if(item.notice_content == 4){
						$('#old_notice_list').append('<tr><td id="old_notice_td">'+item.notice_from_id+' </b>님이 프로젝트를 요청을 수락하였습니다<br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					else if(item.notice_content == 5){
						$('#old_notice_list').append('<tr><td id="old_notice_td"><b>'+item.user_id+' </b>님의 프로젝트를 요청을 거절하였습니다<br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					else if(item.notice_content == 6){
						$('#old_notice_list').append('<tr><td id="old_notice_td">'+item.notice_from_id+' </b>님이 프로젝트를 요청을 거절하였습니다<br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
//					else if (item.notice_content == 7){
//						$('#old_notice_list').append('<tr><td id="old_notice_td">'+item.user_id+' </b>님이 '+item.board_id+' 프로젝트 지원을 취소하였습니다<br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
//					}
					else if(item.notice_content == 8){
						$('#old_notice_list').append('<tr><td id="old_notice_td">'+item.user_id+' </b>님, '+item.notice_from_id+' 님께서 '+item.board_id+' 프로젝트 지원을 취소하였습니다<br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					else if(item.notice_content == 9){
						$('#old_notice_list').append('<tr><td id="old_notice_td">'+item.user_id+' </b>님 '+item.board_id+' 프로젝트 계약을 취소하였습니다<br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					else if(item.notice_content == 10){
						$('#old_notice_list').append('<tr><td id="old_notice_td">'+item.user_id+' </b>님, '+item.notice_from_id+' 님께서 '+item.board_id+' 프로젝트 계약을 취소하였습니다<br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					else if(item.notice_content == 11){
						$('#old_notice_list').append('<tr><td id="old_notice_td">'+item.user_id+' </b>님 '+item.board_id+' 프로젝트 계약을 취소하였습니다<br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					else if(item.notice_content == 12){
						$('#old_notice_list').append('<tr><td id="old_notice_td">'+item.user_id+' </b>님, '+item.notice_from_id+' 님께서 '+item.board_id+' 프로젝트 계약을 취소하였습니다<br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					else if(item.notice_content == 13){
						$('#old_notice_list').append('<tr><td id="old_notice_td">'+item.user_id+' </b>님, 축하합니다 ! '+item.board_id+' 프로젝트가 완성되었습니다.<br><a href="주소값넣을지..?'+item.notice_id+'"> 리뷰주소값?(ref num?)</a><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					else if(item.notice_content == 14){
						$('#old_notice_list').append('<tr><td id="old_notice_td">'+item.user_id+' </b>님, 축하합니다 ! '+item.board_id+' 프로젝트가 완성되었습니다<br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					else if(item.notice_content == 15){
						$('#old_notice_list').append('<tr><td id="old_notice_td">'+item.user_id+' </b>님, '+item.notice_from_id+' 님이 작성한 리뷰를 확인해보세요!<br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
					
				});	
			}); //getJSON
		}); //click - 새알람
}); //ready	




