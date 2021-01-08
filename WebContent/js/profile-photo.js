
// 프로필 사진 업로드
$(document).ready(function(){
	$(document).on('click','#editor_photo_btn',function(){
		$("#editor_photo_form").click();
	})
	$(document).on('change',"#editor_photo_form",function(){
		var user_id = $("#user_id").val();
		var formData = new FormData($("#WriteForm")[0]);
		var photo_content = $("#editor_photo_form").val();
		$("#photo_content").val(photo_content.substring(Number(photo_content.lastIndexOf("\\")+Number(1))));
		$.ajax({
			cache : false,
			url : "UserPhotoUpdate.uo?user_id="+user_id,
			type : "POST",
			enctype: 'multipart/form-data',
			data: formData,
			processData: false,
        	contentType: false,
			success : function(data) {
				if(data != ""){
					$("#photo").attr("src","editorUserPhotoUpload/"+data);
					
				} else {
					alert("업로드에 실패했습니다.")
				}
			},
			error : function() {}
		});
	})
})
