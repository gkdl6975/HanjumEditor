/**
 * 
 */


$(document).ready(function(){
	$("#portfolioArea").load("UserPortfolioList.uo");
	$(document).on('click','.toggle_label',function(){
		var status = 0;
		var user_id = $("#user_id").val();
		if($("input:checkbox[name=editor_status]").is(":checked") == true) {
			status = 0;
		} else {
			status = 1;
		}
		$.ajax({
			cache : false,
			url : "ChangeStatus.uo?user_id="+user_id+"&editor_status="+status,
			type : "GET",
			success : function(data) {
				if(data.indexOf("true") != -1){
					if(status == 0){
						alert("편집자 정보가 비공개처리 되었습니다.");
					} else {
						alert("편집자 정보가 공개처리 되었습니다.");
					}
				} else {
					alert("상태변경에 실패했습니다 관리자에게 문의하세요.");
					if(status == 0){
						$("input:checkbox[name=editor_status]").prop("checked", true);
					} else {
						$("input:checkbox[name=editor_status]").prop("checked", false);
					}
				}
			},
			error : function() {
		                alert("상태변경에 실패했습니다 관리자에게 문의하세요.");
						if(status == 0){
							$("input:checkbox[name=editor_status]").prop("checked", true);
						} else {
							$("input:checkbox[name=editor_status]").prop("checked", false);
						}
		            }
		});
		
	})
	$(document).on('click','.edit_ready',function(){
		var target = $(this).attr("id").replace("edit_","");
		edit(target);		
	})
	
	$(document).on('click','.edit_commit',function(){
		var target = $(this).attr("id").replace("edit_","");
		edit_commit(target);
	})

})

function edit(target){
	
	if(target == "user_name"){
		var targetID = "#"+target;
		var Content = $(targetID).html();
		$(targetID).html("<input id='user_name_form' class='form-control edit-form' type='text' name='user_name' value='"+Content+"'>");
		$("#edit_"+target).addClass("edit_commit");
		$("#edit_"+target).removeClass("edit_ready");
		$("#edit_"+target).css({"background-image": "url(images/check.png)"});
	} else if(target == "price") {
		var content = $("#editor_"+target).html();
		var min_price = $("#editor_min_price_val").val();
		var max_price = $("#editor_max_price_val").val();
		$.ajax({
			cache : false,
			url : "editEditor.uo",
			type : "POST",
			data : {content:content, target:"editor_"+target, editor_ed_min_price:min_price, editor_ed_max_price:max_price},
			success : function(data) {
				$("#editor_"+target).html(data);
				$("#edit_"+target).addClass("edit_commit");
				$("#edit_"+target).removeClass("edit_ready");
				$("#edit_"+target).css({"background-image": "url(images/check.png)"});
			},
			error : function() {}
		});
	} else if(target == "photo") {
		var targetID = "#"+target;
		var content = $(targetID).attr("data-src");
		$.ajax({
			cache : false,
			url : "editEditor.uo",
			type : "POST",
			data : {content:content, target:"editor_"+target},
			success : function(data) {
				$("#editor_"+target).html(data);
				$("#edit_"+target).addClass("edit_commit");
				$("#edit_"+target).removeClass("edit_ready");
				$("#edit_"+target).css({"background-image": "url(images/check.png)"});
			},
			error : function() {}
		});
	} else {
		var content = $("#editor_"+target).html();
		$.ajax({
			cache : false,
			url : "editEditor.uo",
			type : "POST",
			data : {content:content, target:"editor_"+target},
			success : function(data) {
				$("#editor_"+target).html(data);
				$("#edit_"+target).addClass("edit_commit");
				$("#edit_"+target).removeClass("edit_ready");
				$("#edit_"+target).css({"background-image": "url(images/check.png)"});
			},
			error : function() {}
		});
	}
}

function edit_commit(target){
	var targetID = "#"+target;
	var user_id = $("#user_id").val();
	if(target == "user_name"){
		var content = $("#user_name_form").val();
		$.ajax({
			cache : false,
			url : "UserUpdatePro.uo",
			type : "POST",
			data : {user_id:user_id, content:content, target:target},
			success : function(data) {
				if(data.indexOf("true") != -1){
					$(targetID).html(content);
					$("#edit_"+target).css({"background-image": "url(images/pen.png)"});
					$("#edit_"+target).removeClass("edit_commit");
					$("#edit_"+target).addClass("edit_ready");
				} else {
					alert("수정에 실패했습니다.");
				}
			},
			error : function() {
		                alert("수정에 실패했습니다.");
		            }
		});
	} else if(target == "price") {
		var content = $("#editor_"+target).html();
		var min_price = $("input[name=editor_ed_min_price]").val();
		var user_id = $("#user_id").val();
		var max_price = $("input[name=editor_ed_max_price]").val();
		$.ajax({
			cache : false,
			url : "UserUpdateEditorPro.uo",
			type : "POST",
			data : {user_id:user_id, content:content, target:"editor_"+target, editor_ed_min_price:min_price, editor_ed_max_price:max_price},
			success : function(data) {
				if(data.indexOf("true") != -1){
					$("#member-container").empty();
					$("#member-container").load("UserMyInfo.uo");
				} else {
					alert("수정에 실패했습니다.");
				}
			},
			error : function() {}
		});
	} else if(target == "program" || target == "solution" || target == "inventory") {
		var contentList = [];
		$("input[name='editor_"+target+"']:checked").each(function(i){
			contentList.push($(this).val());
		})
		var content = contentList.join(',');
		var user_id = $("#user_id").val();
		$.ajax({
			cache : false,
			url : "UserUpdateEditorPro.uo",
			type : "POST",
			data : {user_id:user_id, content:content, target:"editor_"+target},
			success : function(data) {
				if(data.indexOf("true") != -1){
					$("#member-container").empty();
					$("#member-container").load("UserMyInfo.uo");
				} else {
					alert("수정에 실패했습니다.");
				}
			},
			error : function() {}
		});
	} else if(target == "address") {
		var content = $("select[name='editor_"+target+"']").val();
		var user_id = $("#user_id").val();
		$.ajax({
			cache : false,
			url : "UserUpdateEditorPro.uo",
			type : "POST",
			data : {user_id:user_id, content:content, target:"editor_"+target},
			success : function(data) {
				if(data.indexOf("true") != -1){
					$("#member-container").empty();
					$("#member-container").load("UserMyInfo.uo");
				} else {
					alert("수정에 실패했습니다.");
				}
			},
			error : function() {}
		});
	} else if(target == "photo") {
		$("#editor_photo").html("");
		$("#edit_"+target).css({"background-image": "url(images/pen.png)"});
		$("#edit_"+target).removeClass("edit_commit");
		$("#edit_"+target).addClass("edit_ready");
	} else {
		var content = $("input[name='editor_"+target+"']:checked").val();
		var user_id = $("#user_id").val();
		$.ajax({
			cache : false,
			url : "UserUpdateEditorPro.uo",
			type : "POST",
			data : {user_id:user_id, content:content, target:"editor_"+target},
			success : function(data) {
				if(data.indexOf("true") != -1){
					$("#member-container").empty();
					$("#member-container").load("UserMyInfo.uo");
				} else {
					alert("수정에 실패했습니다.");
				}
			},
			error : function() {}
		});
	}
}
