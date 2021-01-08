/**
 * 
 */
$(document).ready(function(){
	$(document).on('focus','.form-control',function(){
		var inputId = $(this).attr("id");
		if(!$("label[for='"+inputId+"']").hasClass('has-focus')){
			$("label[for='"+inputId+"']").addClass('has-focus');
		}
	});
})