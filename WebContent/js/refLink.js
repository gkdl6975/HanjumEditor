$(document).ready(function() {
	
	$(document).on("click", "#ref_insert", function() {
		var count = $("input[name=board_creator_cre_ref]").length;
		if(count>=20){
			alert('레퍼런스 링크는 20개 까지 업로드 할 수 있습니다.');			
		} else {
			count++;
			$("#ref_area").append('<div id="divRef'+count+'" class="input_link m-tb-15"><input type="text" class="form-control" name="board_creator_cre_ref" id="Ref'+count+'" placeholder="YOUTUBE 영상링크를 작성해주세요.">')
		}
	});
	
	$(document).on("click", "#ref_delete", function() {
		var count = $("input[name=board_creator_cre_ref]").length;
		if(count==0){
			alert('레퍼런스 링크가 없습니다.');
		} else {
			$("#divRef"+count).remove();
		}
	});
	$(document).on("change","input[name=board_creator_cre_ref]",function(){
		var refId = $(this).attr('id');
		$("#div"+refId).children('input[name=board_creator_cre_ref]').prop("type","hidden");
		var refContent = $("#div"+refId).children('input[name=board_creator_cre_ref]').val();
		var refContentId = refContent.substring(Number(refContent.indexOf("v="))+Number(2));
		$("#div"+refId).append('<iframe width="560" height="315" src="https://www.youtube.com/embed/'+refContentId+'" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>');
	});
});
