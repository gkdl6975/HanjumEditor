<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
<title>한줌에디터</title>
<link rel="stylesheet" href="css/style.css">
<script src="js/jquery.min.js"></script>
    <script src="js/email.js"></script>
	</head>
	<body>
	<div class="login-wrap p-4 p-md-5">
              <h3 class="mb-4">암호찾기</h3>
              <form action="changePass.uo" class="signup-form" method="post" name="Userform" onsubmit="return check()" >
                 <input type="hidden" id="prefPage" name="prefPage" value="<%=request.getParameter("prefPage")%>">
                 <div id = "searchSet">
                 <div class="form-group">
                    <label class="label" for="userid_field">아이디</label>
                    <input type="text" id="userid_field" class="form-control" name="user_id" required="required"/>
                </div>
                <div class="form-group">
                    <label class="label" id="email_label" for="user_email">이메일</label>
                    <input type="text" id="user_email" style="min-width:100px; width:30%; display: inline-block;" class="form-control" name="user_email" required="required"/> @ 
                    <input type="text" class="form-control" style="width:35%; min-width:100px; display: inline-block;" name="user_email2" id="user_email2"/> 
							<select  class="form-control" style="width:30%; min-width:150px; display: inline-block;" onChange="selEmail(this.value)">
								<option onselect="focus">직접입력</option>
								<option value="naver.com">naver.com</option>
								<option value="gmail.com">gmail.com</option>
								<option value="daum.net">daum.net</option>
						</select>
<!-- 					<div id="checkEmailResult" class="offset-1">자바스크립트에서 메세지 출력 공간</div> -->
                	
                	<div id="codeMessage" style="margin-top: 10px;"><!-- 코드번호 틀린거 맞는거 들어갈칸 -->
						</div>
						<div id="codeCheck" style="display: none;">
						<input type="text" class="form-control" name="email_code" id="email_code" style="width:30%; min-width:150px; display: inline-block;" placeholder="인증 코드">
						<input type="button" class="btn btn-primary" value="인증번호 재전송" name="code_check" id="code_check">
						</div>
						</div>
                </div>
                <div id="passSet" style="display: none;">
                <div class="form-group">
                 <label class="label" id="changePass_label" for="password-field">변경할 비밀번호</label>
                 <input id="password-field" type="password" class="form-control" name="user_changePass" required="required" onkeyup="checkPasswd()">
                 <div id="checkPasswdResult" class="offset-1"><!-- 자바스크립트에서 메세지 출력 공간 --></div>
                 </div>
                 <div class="form-group">
                 <label class="label" id="changePass_label2"for="password-field2">비밀번호 확인</label>
                 <input id="password-field2" type="password" class="form-control" name="user_changePass2" required="required" onkeyup="checkPasswd()">
             	<div id="checkPasswdResult2" class="offset-1"><!-- 자바스크립트에서 메세지 출력 공간 --></div>
	             </div>
             </div>
             
             <div class="form-group d-flex justify-content-end mt-4" id ="checkEmailResult" >가입할 때 입력하신 아이디와 이메일을 입력 해주세요.</div>
             <div class="form-group d-flex justify-content-end mt-4" >
                 <button type="submit" class="btn btn-primary submit" value="비밀번호 변경" id = "subbtn" style="display: none;" >비밀번호 변경</button>
             </div>
         </form>
         <p class="text-center">오늘 처음 오셨나요? <a id="join_btn" href="My.uo?fr=join">Sign Up</a></p>
     </div>
     </body>
     </html>