/**
 *  회원가입 항목 유효성 검사
 */
	var checkIdResult = false, checkPasswdResult = false, checkEmailResult = false;
	$(document).ready(function() {
		$(document).on("keyup","#user_id",function() {
			var user_id = $('#user_id').val();
			var msg = $("#checkIdResult");
			var regex = /^[A-Za-z][A-Za-z0-9]{3,11}$/g;
			$.ajax({
				url : 'UserCheck.uo?user_id='+ user_id,
				type : 'get',
				success : function(data) {
					$("#checkIdResult").html(data);
					if (data == 1) {
						msg.css("color","#ff471a");
						check_false("#id_label","#user_id");
						msg.html("이미 사용 중인 아이디 입니다.");
						checkIdResult = false;
					} else if (user_id == "") {
						msg.css("color","#ff471a");
						check_false("#id_label","#user_id");
						msg.html("아이디를 입력해 주세요 :)");
						checkIdResult = false;
					} else if (!regex.exec(user_id)){
						msg.css("color","#ff471a");
						check_false("#id_label","#user_id");
						msg.html("아이디는 영문 숫자 조합으로 3~11글자만 가능합니다.");
						checkIdResult = false;
					}	else {
						msg.css("color","#00e673");
						check_true("#id_label","#user_id");
						msg.html("사용 가능한 아이디 입니다.");
						checkIdResult = true;
					}
				}
			});
		});
	
	
	$(document).on("keyup","#password",function() {
		var passwd = $('#password').val();
		var msg = $("#checkPasswdResult");
		var langthRegex = /^[A-Za-z0-9!@#$%]{4,16}$/;
		var upperCaseRegex = /[A-Z]/;
		var lowerCaseRegex = /[a-z]/;
		var digitRegex = /[0-9]/;
		var specRegex = /[!@#$%]/;
		if (passwd != "") {
				
			if (langthRegex.exec(passwd)) {
				var count = 0;
				if (upperCaseRegex.exec(passwd)) {count++;}
				if (lowerCaseRegex.exec(passwd)) {count++;}
				if (digitRegex.exec(passwd)) {count++;}
				if (specRegex.exec(passwd)) {count++;}
				if (count == 4){msg.css("color","#00e673");
					check_true("#pass_label","#password");
					msg.html("안전");
					checkPasswdResult = true;}
					else if (count== 3){msg.css("color","#ff9900");
					check_true("#pass_label","#password");
					msg.html("보통 - 대소문자+특수문자+숫자 조합을 추천합니다");
					checkPasswdResult = true;}
					else if (count== 2){msg.css("color","#ff9900");
					check_true("#pass_label","#password");
					msg.html("위험 - 대소문자+특수문자+숫자 조합을 추천합니다");
					checkPasswdResult = true;}
					else {msg.css("color","#ff471a");
					check_false("#pass_label","#password");
					msg.html("대소문자+특수문자+숫자 조합으로 작성해주세요.");
					checkPasswdResult = false;}}
					else {msg.css("color","#ff471a");
					check_false("#pass_label","#password");
					msg.html("비밀번호는 영문 숫자 조합으로 4~16글자만 가능합니다.");
					checkPasswdResult = false;}}
					else {msg.css("color","#ff471a");
			check_false("#pass_label","#password");
			msg.html("비밀번호를 입력해 주세요 :)");
			checkPasswdResult = false;}});
	
	
	$(document).on("keyup","#user_email",function(){
		var email = $('#user_email').val();
		var msg = $("#checkEmailResult");
		var Regex = /^[A-Za-z0-9]+$/;

		if(email != ""){
			if(!Regex.test(email)){
				msg.css("color","#ff471a");
				check_false("#email_label","#user_email");
				msg.html("올바른 이메일 아이디가 아닙니다.");
				checkEmailResult = false;
			} else {
				if($("#user_email2").val() != ""){
				mailBtn();
				}
			}
				
		} else {
			
			msg.css("color","#ff471a");
			check_false("#email_label","#user_email");
			msg.html("이메일을 입력해 주세요 :)");
			checkEmailResult = false;
		}
	
	});
	$('#user_email2').on('blur',function(){
		if($("#user_email").val() != ""){
			mailBtn();
		}
	});
		
		
		// 이메일 인증번호 전송
			$(document).on('click','#mail_check',function() {
			var email = $('#user_email').val() + "@" + $('#user_email2').val();
			$("#checkEmailResult").html("이메일로 코드를 발송중입니다.");
			alert('입력하신 이메일로 인증코드가 전송됩니다.');
			$.ajax({ // 회원가입 폼에서는 email만 가지고갑니다. 회원가입 페이지에서 제어를 하기때문(id값이 필요없음)
				url : 'mailSend?receiver='+ email, 
				type : 'get',
				success : function(data) {
					$('#mailSet').hide();
					$("#checkEmailResult").hide();
					$('#codeCheck').show();
				}// success 종료
			}); // ajax종료
		}); // click 종료
	
		// 이메일 인증번호 재전송
		$('#code_check').click(function() {
		var email = $('#user_email').val() + "@" + $('#user_email2').val();
		alert("인증번호를 " + email + "로 재발송합니다.");
		$.ajax({
			url : 'mailSend?receiver='+ email,
			type : 'get'
		}); // ajax종료
	}); // click 종료
		
		
			// 이메일 인증번호 체크
			$('#email_code').change(function() {
			var email = $('#user_email').val() + "@" + $('#user_email2').val();
			var code = $('#email_code').val();
			$.ajax({
				url : 'CodeCheck.uo?email='+ email + '&code=' + code,
				type : 'get',
				success : function(data) {
					if(data == 1) {
					$('#codeMessage').css("color","#00e673");	
					$('#codeMessage').html("메일 인증을 성공적으로 마쳤습니다. <br>");
					$('#codeCheck').hide();
					$('#user_email').attr("readonly",true);
					$('#user_email2').attr("readonly",true);
					$('#selectEmail').attr("readonly",true);
//					$('#user_email3').hide();
//					$('#mailSet2').show();
					checkEmailResult = true;
					} else if(data == 0) {
					$('#codeMessage').css("color","#ff471a");
					$('#codeMessage').html("코드가 올바르지 않습니다. <br>");
					}
				}// 석세스 종료
			}); // ajax종료
		}); // click 종료
		
		
	});
	function mailBtn(){
		var msg = $("#checkEmailResult");
		msg.css("color","#ff471a");
		check_normal("#email_label","#user_email");
		msg.html("<input class='btn btn-primary' id='mail_check' type='button' value='인증 번호 전송' name='mail_check' id='mail_check'> 이메일 인증이 필요합니다");
	}
	function check_true(label, box) {
			$(label).css("color","#00e673");
			$(box).css("border-color","#00e673");
			$(box).css("box-shadow","1px 1px 3px #00e673");
		}
		
		function check_false(label, box) {
			$(label).css("color","#ff471a");
			$(box).css("border-color","#ff471a");
			$(box).css("box-shadow","1px 1px 3px #ff471a");
		}
		function check_normal(label, box) {
			$(label).css("color","rgba(0, 0, 0, 0.3)");
			$(box).css("border-color","");
			$(box).css("box-shadow","");
		}
	function check() {
		if(!checkIdResult){
			alert('아이디가 올바르지 않습니다.');
			$('#user_id').focus();
			return false;
		} else if(!checkPasswdResult){
			alert('비밀번호가 올바르지 않습니다.');
			$('#password').focus();
			return false;
		} else if(!checkEmailResult){
			alert('이메일을 인증까지 완료해 주시기 바랍니다.');
			$('#user_email').focus();
			return false;
		} else {
			return true;
		}
	}
	function selEmail(email) {
		if (email != "직접입력") {
			document.getElementById("user_email2").value = email;
			document.getElementById("user_email2").focus();
			document.getElementById("user_email2").readOnly = true;
		} else {
			document.getElementById("user_email2").readOnly = false;
			document.getElementById("user_email2").value = "";
			document.getElementById("user_email2").focus();
		}
	}
