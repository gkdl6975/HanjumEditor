  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/style.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="plugin/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="js/smartediter.js"></script>
<script type="text/javascript" src="js/join.js"></script>
<title>회원가입</title>
<style>
	#editor_photo { display:none; }
	.check_list { display: inline-block; padding:10px;}
	.check_list span { margin-left: 10px;}
	.label-primary { color: #4986fc !important; }
	#editor_photo { display:none; }
	.profile_photo {max-width: 300px; max-height: 300px; width:80%;}
	
</style>
</head>
<body>
<div class="login-wrap p-4 p-md-5" style="margin-top:0px;">
              <h3 class="mb-4">편집자 가입</h3>
              <form action="JoinEditorPro.uo" method="post" name="fr_write" id="WriteForm" onsubmit="return check()">
                 <div class="form-group">
                    <label id="id_label" class="label" for="user_id">아이디</label>
                    <input type="text" id="user_id" class="form-control" name="user_id" required="required"/>
                	<div id="checkIdResult" class="offset-1"><!-- 자바스크립트에서 메세지 출력 공간 --></div>
                </div>
                <div class="form-group">
                 <label id="pass_label" class="label" for="password">비밀번호</label>
                 <input id="password" type="password" class="form-control" name="user_pass" required="required">
            	 <div id="checkPasswdResult" class="offset-1"><!-- 자바스크립트에서 메세지 출력 공간 --></div>	
            	</div>
            	<div class="form-group">
                    <label class="label" for="user_name">닉네임</label>
                    <input type="text" id="user_name" class="form-control" name="user_name" required="required"/>
                </div>
                <div class="form-group">
                    <label class="label" id="email_label" for="user_email">이메일</label>
                    <input type="text" id="user_email" style="min-width:100px; width:30%; display: inline-block;" class="form-control" name="user_email" required="required"/> @ 
                    <input type="text" class="form-control" style="width:35%; min-width:100px; display: inline-block;" name="user_email2" id="user_email2"/> 
							<select  class="form-control" style="width:30%; min-width:150px; display: inline-block;" onChange="selEmail(this.value)" id ="selectEmail">
								<option onselect="focus">직접입력</option>
								<option value="naver.com">naver.com</option>
								<option value="gmail.com">gmail.com</option>
								<option value="daum.net">daum.net</option>
						</select>
					<div id="checkEmailResult" class="offset-1"><!-- 자바스크립트에서 메세지 출력 공간 --></div>
                	
                	<div id="codeMessage" style="margin-top: 10px;"><!-- 코드번호 틀린거 맞는거 들어갈칸 -->
						</div>
						<div id="codeCheck" style="display: none;">
						<input type="text" class="form-control" name="email_code" id="email_code" style="width:30%; min-width:150px; display: inline-block;" placeholder="인증 코드">
						<input type="button" class="btn btn-primary" value="인증번호 재전송" name="code_check" id="code_check">
						</div>
						<div id="mailSet2" style="display: none;">
						<input type="text" name="user_email3" id="user_email3" readonly="readonly">
						</div>
                </div>
                <div class="form-group">
                    <label class="label" for="user_phone">휴대폰번호</label>
                    <input type="text" id="user_phone" class="form-control" name="user_phone" required="required"/>
                </div>
<!--                 <div class="form-group"> 프로필 사진 제거 -->
<!--                 <div id="photoArea" style="margin-bottom: 10px;"> -->
<!--                 <img id="photo" class="profile_photo" src="images/no-profile.png" title="profile"> -->
<!--                 </div> -->
<!--                 <label class="label has-focus" for="editor_photo_btn">프로필 사진</label> -->
<!--                 <input class="form-control" id="photo_content" type="text" readonly="readonly" style="min-width:100px; width:40%; display: inline-block;" >  -->
<!--                 <input type="button" class="btn btn-primary" id="editor_photo_btn" value="프로필 업로드">  -->
<!--                 <input type="file" id="editor_photo" name="editor_photo"/> -->
<!--                 </div> -->
				<input type="hidden" id="editor_photo" name="editor_photo" value="no-profile.png"/>
                <div class="form-group">
                <label class="label has-focus" for="editor_des">한줄 소개</label>
                <textarea id="editor_des" name="editor_des" cols="50" rows="3" ></textarea>
                </div>
                <div class="form-group">
                <label  class="label has-focus" for="DetailContent">본인 소개</label>
                <div class="form-control" style="height: auto;">
                	<div style="padding-top:10px;"></div>
                	<textarea id="DetailContent" name="editor_profile" style="padding: 20px; width:100%; min-width:260px; "></textarea>
                </div>
                </div>
                <div class="form-group">
                <label class="label has-focus label-primary">이용 프로그램</label>
	                <div class="check_list"><input type="checkbox" id="program1" name="editor_program" value="1"><span class="checkbox">프리미어</span></div>
					<div class="check_list"><input type="checkbox" id="program2" name="editor_program" value="2"><span class="checkbox">파이널컷</span></div>
					<div class="check_list"><input type="checkbox" id="program3" name="editor_program" value="3"><span class="checkbox">베가스</span></div>
					<div class="check_list"><input type="checkbox" id="program4" name="editor_program" value="4"><span class="checkbox">애프터 이펙트</span></div>
					<div class="check_list"><input type="checkbox" id="program5" name="editor_program" value="5"><span class="checkbox">기타</span></div>
                </div>
                <div class="form-group">
                <label class="label has-focus label-primary">최종 납품 해상도</label>
	                <div class="check_list"><input type="checkbox" id="solution1" name="editor_solution" value="1"><span class="checkbox">HD</span></div>
					<div class="check_list"><input type="checkbox" id="solution2" name="editor_solution" value="2"><span class="checkbox">FHD</span></div>
					<div class="check_list"><input type="checkbox" id="solution3" name="editor_solution" value="3"><span class="checkbox">UHD</span></div>
					<div class="check_list"><input type="checkbox" id="solution4" name="editor_solution" value="4"><span class="checkbox">맞춤 가능</span></div>
                </div>
                <div class="form-group">
                <label class="label has-focus label-primary">작업 가능 항목</label>
	                <div class="check_list"><input type="checkbox" id="inventory1" name="editor_inventory" value="1"><span class="checkbox">컷 편집</span></div>
					<div class="check_list"><input type="checkbox" id="inventory2" name="editor_inventory" value="2"><span class="checkbox">오디오 싱크</span></div>
					<div class="check_list"><input type="checkbox" id="inventory3" name="editor_inventory" value="3"><span class="checkbox">BGM 삽입</span></div>
					<div class="check_list"><input type="checkbox" id="inventory4" name="editor_inventory" value="4"><span class="checkbox">효과음 삽입</span></div>
					<div class="check_list"><input type="checkbox" id="inventory5" name="editor_inventory" value="5"><span class="checkbox">모션 그래픽</span></div>
					<div class="check_list"><input type="checkbox" id="inventory6" name="editor_inventory" value="6"><span class="checkbox">템플릿 작업</span></div>
                </div>
                <div class="form-group">
                <label class="label has-focus label-primary">납품 파일 공유 방식</label>
	                <div class="check_list"><input type="radio" id="upload1" name="editor_upload" value="1"><span class="checkbox">이메일</span></div>
					<div class="check_list"><input type="radio" id="upload2" name="editor_upload" value="2"><span class="checkbox">NAS</span></div>
					<div class="check_list"><input type="radio" id="upload3" name="editor_upload" value="3"><span class="checkbox">웹하드</span></div>
					<div class="check_list"><input type="radio" id="upload4" name="editor_upload" value="4"><span class="checkbox">모두 가능</span></div>
                </div>
                <input type="hidden" name="editor_work" value="1">
                <div class="form-group">
                <label class="label has-focus label-primary">실물 미팅</label>
	                <div class="check_list"><input type="radio" id="meeting1" name="editor_meeting" value="1"><span class="checkbox">가능</span></div>
					<div class="check_list"><input type="radio" id="meeting2" name="editor_meeting" value="2"><span class="checkbox">불가능</span></div>
                </div>
                <div class="form-group">
                <label class="label has-focus label-primary">선호 작업 유형</label>
	                <div class="check_list"><input type="radio" id="fort1" name="editor_fort" value="1"><span class="checkbox">건당 계약</span></div>
					<div class="check_list"><input type="radio" id="fort2" name="editor_fort" value="2"><span class="checkbox">단기 계약</span></div>
					<div class="check_list"><input type="radio" id="fort3" name="editor_fort" value="3"><span class="checkbox">장기 계약</span></div>
                </div>
                <div class="form-group">
                <label class="label has-focus label-primary">샘플 작업 가능 유무</label>
	                <div class="check_list"><input type="radio" id="sample1" name="editor_sample" value="1"><span class="checkbox">가능</span></div>
					<div class="check_list"><input type="radio" id="sample2" name="editor_sample" value="2"><span class="checkbox">불가능</span></div>
                </div>
                <div class="form-group">
                <label class="label has-focus label-primary" for="Content">예상 단가</label>
              	  <div class="input_price"><input class="form-control" style="width:30%; display: inline-block;" type="number" id="MinPrice" step="1000" min="0" name="editor_ed_min_price" value="0"> 원 ~ <input class="form-control" style="width:30%; display: inline-block;" type="number" id="MaxPrice" step="1000" min="0" name="editor_ed_max_price" value="5000"> 원</div>
                </div>
                <div class="form-group">
                <label class="label has-focus label-primary">거주지</label>
	                <select class="form-control"  name="editor_address">
	                <option>서울</option>
	                <option>부산</option>
	                <option>대구</option>
	                <option>대전</option>
	                <option>인천</option>
	                <option>광주</option>
					<option>울산</option>
					<option>세종</option>
					<option>경기</option>
					<option>강원</option>
					<option>충북</option>
					<option>충남</option>
					<option>경북</option>
					<option>경남</option>
					<option>전북</option>
					<option>전남</option>
					<option>제주</option>
					</select>
                </div>
             <div class="form-group d-flex justify-content-end mt-4">
                 <button type="submit"  id="WriteSubmit" class="btn btn-primary submit"><span class="fa fa-paper-plane"></span></button>
             </div>
         </form>
     </div>
     </body>
     </html>