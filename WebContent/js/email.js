/**
 *  이메일 인증을 위한 스크립트
 */
var checkPasswdResult = false, checkPasswdResult2 = false;
$(document).ready(function() {
	
	$(document).on("keyup","#user_email",function(){
		var email = $('#user_email').val();
		var msg = $("#checkEmailResult");
		var Regex = /^[A-Za-z0-9]+$/;

		if(email != ""){
			if(!Regex.test(email)){
				msg.css("color","#ff471a");
				check_false("#email_label","#user_email");
				msg.html("올바른 이메일 아이디가 아닙니다.");
			} else {
				if($("#user_email2").val() != ""){
				mailBtn();
				}
			}
				
		} else {
			
			msg.css("color","#ff471a");
			check_false("#email_label","#user_email");
			msg.html("이메일을 입력해 주세요 :)");
		}
	
	});
	$('#user_email2').on('blur',function(){
		if($("#user_email").val() != ""){
			mailBtn();
		}
	});
	
	$(document).on("keyup","#password-field",function() {
		var passwd = $('#password-field').val();
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
			
				
				
				if (count == 4){
					msg.css("color","#00e673");
					check_true("#changePass_label","#password-field");
					msg.html("안전");
					checkPasswdResult = true;
				} else if (count== 3){
					msg.css("color","#ff9900");
					check_true("#changePass_label","#password-field");
					msg.html("보통 - 대소문자+특수문자+숫자 조합을 추천합니다");
					checkPasswdResult = true;
				} else if (count== 2){
					msg.css("color","#ff9900");
					check_true("#changePass_label","#password-field");
					msg.html("위험 - 대소문자+특수문자+숫자 조합을 추천합니다");
					checkPasswdResult = true; 
				} else {
					msg.css("color","#ff471a");
					check_false("#changePass_label","#password-field");
					msg.html("대소문자+특수문자+숫자 조합으로 작성해주세요.");
					checkPasswdResult = false;
				}
			} else {
					msg.css("color","#ff471a");
					check_false("#changePass_label","#password-field");
					msg.html("비밀번호는 영문 숫자 조합으로 4~16글자만 가능합니다.");
					checkPasswdResult = false;
			}	
		} else {
			msg.css("color","#ff471a");
			check_false("#pass_label","#password");
			msg.html("비밀번호를 입력해 주세요 :)");
			checkPasswdResult = false;
		}
			
	});
	
//	(document).on("keyup","#password-field2",function() {
//		var passwd = $('#password-field').val();
//		var checkPasswd = $('#password-field2').val();
//		var msg = $("#checkPasswdResult2");
//		if (checkPasswd != "") {
//				
//				if (passwd == checkPasswd){
//					msg.css("color","#00e673");
//					check_true("#pass_label","#password2");
//					msg.html("비밀번호 일치");
//					checkPasswdResult = true;
//				} else {
//					msg.css("color","#ff471a");
//					check_false("#pass_label","#password2");
//					msg.html("비밀번호 틀림 - 다시 확인해주세요.");
//					checkPasswdResult = false;
//				}
//			} else {
//					msg.css("color","#ff471a");
//					check_false("#pass_label","#password2");
//					msg.html("비밀번호를 확인해주세요.");
//					checkPasswdResult = false;
//			}	
//		
//			
//	});
	
	
	
	// 이메일 전송 코드
	$(document).on('click','#mail_check',function() {
	var email = $('#user_email').val() + "@" + $('#user_email2').val();
	var searchUser_id = $('#userid_field').val();
	$("#checkEmailResult").html("이메일로 코드를 발송중입니다.");
	alert('가입하신 이메일로 인증코드가 전송됩니다.');
	$.ajax({
		url : 'mailSend?receiver='+ email +'&user_id='+searchUser_id,
		type : 'get',
		success : function(data) {
			if(data == 1) {
			$('#mailSet').hide();
			$("#checkEmailResult").html("");
			$('#codeCheck').show();
			}
			else {
			alert('해당 회원 정보가 없습니다. 다시 확인 해주세요.');
			location.href='My.uo?fr=pass';
			}
		}// 석세스 종료
	}); // ajax종료	
	});
	
	// 이메일 재전송 코드
	$('#code_check').click(function() {
		var email = $('#user_email').val() + "@" + $('#user_email2').val();
		var searchUser_id = $('#userid_field').val();
		alert("인증번호를 " + email + "로 재발송합니다.");
		$.ajax({
			url : 'mailSend?receiver='+ email +'&user_id='+searchUser_id,
			type : 'get'
		}); // ajax종료
	}); // click 종료
	
	
	// 이메일 중복체크 코드
	$('#email_code').keyup(function() {
	var email = $('#user_email').val() + "@" + $('#user_email2').val();
	var code = $('#email_code').val();
	$.ajax({
		url : 'CodeCheck.uo?email='+ email + '&code=' + code,
		type : 'get',
		success : function(data) {
				if(data == 1) {
				alert('메일 인증 완료! 변경할 비밀번호를 입력해주세요!');
				$('#searchSet').hide();
				$('#passSet').show();
				$('#subbtn').show();
				} else if(data == 0) {
				$('#codeMessage').css("color","#ff471a");
				$('#codeMessage').html("코드가 올바르지 않습니다. <br>");
				}
			}// 석세스 종료
		}); // ajax종료
	}); // click 종료
	
//	$("#updatePass").click(function(){
//	var user_id = $('#searchUser_id').val();
//	var user_changePass = $('#user_updatePass').val();
//	$.ajax({
//		url : '${pageContext.request.contextPath}/changePass.uo?user_id=' + user_id + '&user_changePass=' + user_changePass,
//		type : "get",
//		success : function(data){
//			if(data == 1) {
//				alert('비밀번호 변경 성공! 다시 로그인 하여 주세요.');
//				location.href='UserLoginForm.uo';
//			}
//			else {
//				alert('비밀번호 변경 실패! 다시 시도해 주세요.');
//				location.href='UserLoginForm.uo';
//			}
//		}
//	}) // ajax종료
//});

});

function check_true(label, box) {
			$(label).css("color","#00e673");
			$(box).css("border-color","#00e673");
			$(box).css("box-shadow","1px 1px 3px #00e673");
		}


function check_normal(label, box) {
			$(label).css("color","rgba(0, 0, 0, 0.3)");
			$(box).css("border-color","");
			$(box).css("box-shadow","");
		}
function check_false(label, box) {
			$(label).css("color","#ff471a");
			$(box).css("border-color","#ff471a");
			$(box).css("box-shadow","1px 1px 3px #ff471a");
		}

function mailBtn(){
		var msg = $("#checkEmailResult");
		msg.css("color","#ff471a");
		check_normal("#email_label","#user_email");
		msg.html(" 이메일 인증이 필요합니다. <input class='btn btn-primary' id='mail_check' type='button' value='인증 번호 전송' name='mail_check' id='mail_check'>");
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
	
function checkPasswd() {
	var passwd = document.getElementById('password-field').value;
	var checkPasswd = document.getElementById('password-field2').value
	var element = document.getElementById('checkPasswdResult2');
	
	if(passwd==checkPasswd){
		element.innerHTML = "비밀번호 일치";
		check_true("#changePass_label2","#password-field2");
		element.style.color = "#00e673";
		checkPasswdResult2 = true;
	} else {
		element.innerHTML = "비밀번호 틀림 - 다시 확인해주세요.";
		check_false("#changePass_label2","#password-field2");
		element.style.color = "#ff471a";
		checkPasswdResult2 = false;
	}
	
}

function check() {
	if(!checkPasswdResult) {
		alert('비밀번호가 올바르지 않습니다.');
		$('#password-field').focus();
		return false;
	} else if (!checkPasswdResult2) {
		alert('비밀번호 틀림 - 다시확인해주세요');
		$('#password-field2').focus();
		return false;
	} else {
		return true;
	}
}