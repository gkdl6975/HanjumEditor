/**
 *  유저 정보 js
 */
$(document).ready(function(){
	$(document).on('click','#portfolioWrite',function(){
		$('#portfolio_write').load('PfWrite.uo');
		$('#portfolioWrite').html('포트폴리오 작성완료');
		$('#portfolioWrite').attr('id','portfolioSubmit');
	})
	$(document).on('click','#portfolioSubmit',function(){
		var formData = $("#pf_form").serialize();
		$.ajax({
			cache : false,
			url : "UserPortfolioInsert.uo",
			type : "POST",
			data : formData,
			success : function(data) {
				$('#portfolioArea').empty();
				$('#portfolioArea').load('UserPortfolioList.uo');
			},
			error : function(xhr, status) {
		                alert(xhr + " : " + status);
		            }
		});
	});
	$(document).on('click','.portfolioUpdate',function(){
		var thisID = $(this).attr('id').replace("portfolioUpdate","");
		$('#portfolioForm'+thisID).load('UserPortfolioInfo.uo?editor_pf_id='+thisID);
		
	});
	$(document).on('click','.portfolioDelete',function(){
		var thisID = $(this).attr('id').replace("portfolioDelete","");
		$.ajax({
			cache : false,
			url : "UserPortfolioDelete.uo?editor_pf_id="+thisID,
			type : "GET",
			success : function(data) {
				$('#portfolioArea').empty();
				$('#portfolioArea').load('UserPortfolioList.uo');
			},
			error : function(xhr, status) {
		                alert(xhr + " : " + status);
		            }
		});
	});
	$(document).on('click','#portfolioUpdateSubmit',function(){
		var formData = $("#pf_form_update").serialize();
		$.ajax({
			cache : false,
			url : "UserPortfolioUpdate.uo",
			type : "POST",
			data : formData,
			success : function(data) {
				$('#portfolioArea').empty();
				$('#portfolioArea').load('UserPortfolioList.uo');
			},
			error : function(xhr, status) {
		                alert(xhr + " : " + status);
		            }
		});
	})
})