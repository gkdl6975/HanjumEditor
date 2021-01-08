$(document).ready(function(){
	
	var now = new Date();
	var year = now.getFullYear();
    var month = now.getMonth() + 1;    //1월이 0으로 되기때문에 +1을 함.
    var date = now.getDate();
		if(month<10){
			month = "0"+month;
		}
		if(date<10){
			date = "0"+date;
		}
	var currentDate = year+"-"+month+"-"+date;

	$("#date_end").val(currentDate);
	$("#date_begin").val(currentDate);
	
	function date_common(changeDate){
		year = changeDate.getFullYear();
		month = changeDate.getMonth() +1; 
		date = changeDate.getDate(); 

		
		if(month<10){
			month = "0"+month;
		}
		if(date<10){
			date = "0"+date;
		}
		
		
		currentDate = year+"-"+month+"-"+date;		
		$("#date_begin").val(currentDate);

	}
	
	
	
	
	
	$('input[type=radio][id=ra1]').change(function() {
		
		var changeDate = new Date();
			
			changeDate.setDate(changeDate.getDate()); //id=ra1 => 오늘
			
			date_common(changeDate);
	
	});


	
	$('input[type=radio][id=ra2]').change(function() {
		
		var changeDate = new Date();
			
			changeDate.setDate(changeDate.getDate()-1);  // id=ra2 => 어제
			
			date_common(changeDate);
	
	});
	
	$('input[type=radio][id=ra3]').change(function() {
		
		var changeDate = new Date();
			
			changeDate.setDate(changeDate.getDate()-3);	// 3일전
			
			date_common(changeDate);
	
	});
	
	$('input[type=radio][id=ra4]').change(function() {
		
		var changeDate = new Date();
			
			changeDate.setDate(changeDate.getDate()-7);	//일주일전
			
			date_common(changeDate);
	
	});
	
	$('input[type=radio][id=ra5]').change(function() {
		
		var changeDate = new Date();
			
			changeDate.setDate(changeDate.getDate()-15);		// 15일전
			
	
			date_common(changeDate);

	
	});
	
$('input[type=radio][id=ra6]').change(function() {
		
		var changeDate = new Date();
			
			changeDate.setDate(changeDate.getDate()-30);		// 한달전
			
	
			date_common(changeDate);

	
	});
	
$('input[type=radio][id=ra7]').change(function() {
	
	var changeDate = new Date();
		
		changeDate.setDate(changeDate.getDate()-90);		// 3달전
		

		date_common(changeDate);


});

$('input[type=radio][id=ra8]').change(function() {
	
	var changeDate = new Date();
		
		changeDate.setDate(changeDate.getDate()-180);		// 6개월전
		

		date_common(changeDate);


});
			



});
