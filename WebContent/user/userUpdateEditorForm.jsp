  
<%@page import="com.hanjum.user.vo.EditorBean"%>
<%@page import="com.hanjum.user.vo.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	UserBean userBean = (UserBean)request.getAttribute("userBean");
	EditorBean editorBean = (EditorBean)request.getAttribute("EditorBean");
	String email = userBean.getUser_email();
	String[] cutEmail = email.split("@");
	String user_type = null;
	switch (userBean.getUser_type()){
	case 1:
		user_type = "일반 회원";
		break;
	case 2:
		user_type = "편집자 회원";
		break;
	default:
		user_type = "관리자";
	}
	
	String[] editor_program = editorBean.getEditor_program().split(",");
	String[] editor_solution = editorBean.getEditor_solution().split(",");
	String[] editor_inventory = editorBean.getEditor_inventory().split(",");
	int upload = editorBean.getEditor_upload();
	int work = editorBean.getEditor_work();
	int meeting = editorBean.getEditor_meeting();
	int fort = editorBean.getEditor_fort();
	int sample = editorBean.getEditor_sample();
	int min_price = editorBean.getEditor_ed_min_price();
	int max_price = editorBean.getEditor_ed_max_price();
	String address = editorBean.getEditor_address();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="css/default.css" rel="stylesheet">
<link href="css/board.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="js/topMenu.js"></script>
<!-- <script type="text/javascript" src="plugin/js/service/HuskyEZCreator.js" charset="utf-8"></script> -->
<!-- <script type="text/javascript" src="js/smartediter.js"></script> -->
<script type="text/javascript">
$(document).ready(function(){
   $("#photo_sel").click(function(){
      $("#editor_photo").click();
   })
   $("#editor_photo").change(function(){
      var photo = $("#editor_photo").val();
      $("#photo_p").html(photo);
   })
})

function selEmail(email){
	if(email != "직접입력"){
		document.getElementById("user_email2").value = email;
		document.getElementById("user_email2").readOnly = true;
	}else{
		document.getElementById("user_email2").readOnly = false;
		document.getElementById("user_email2").value = "";
                document.getElementById("user_email2").focus();
	}
}
</script>
<title>한줌에디터</title>
</head>
<body>
<div id="wrap">
<jsp:include page="../inc/top.jsp"/>

<div class="write_form">
<div class="write_title"><h1>에디터 회원 수정</h1></div>
<form action="UserUpdateEditorPro.uo" method="post" name="fr_write" id="WriteForm" enctype="multipart/form-data">
<table class="write_table">
<tr><td class="td_name"><label for="Subject">아이디</label></td><td class="td_content"><input id="user_id" type="text" name="user_id" value="<%=userBean.getUser_id()%>" readonly="readonly"></td></tr>
<tr><td class="td_name"><label for="Content">비밀번호</label></td><td class="td_content"><input id="user_pass" name="user_pass"/></td></tr>
<tr><td class="td_name"><label for="Content">이름</label></td><td class="td_content"><input id="user_name" type="text" name="user_name" value="<%=userBean.getUser_name()%>" readonly="readonly"/></td></tr>
<tr><td class="td_name"><label for="Content">이메일</label></td><td class="td_content"><input id="user_email" type="text" name="user_email" value="<%=cutEmail[0]%>"/>
 @ <input type="text" name="user_email2" id="user_email2" value="<%=cutEmail[1]%>" onfocus="inInput(this)" onblur="outInput(this)"/>
<select onfocus="inInput(this)" onblur="outInput(this)" onChange="selEmail(this.value)">
	<option onselect="focus">직접입력</option>
	<option value="naver.com" 
	<%if(cutEmail[1].equals("naver.com")){%> selected ="selected" <%} %>>naver.com</option>
	<option value="gmail.com"
	<%if(cutEmail[1].equals("gmail.com")){%> selected ="selected" <%} %>>gmail.com</option>
	<option value="daum.net"
	<%if(cutEmail[1].equals("daum.net")){%> selected ="selected" <%} %>>daum.net</option>
</select></td></tr>
<tr><td class="td_name"><label for="Content">휴대 전화</label></td><td class="td_content"><input id="user_phone" type="text" name="user_phone" value="<%=userBean.getUser_phone()%>"/></td></tr>
<tr><td class="td_name"><label for="Content">레벨</label></td><td class="td_content">Lv <%=userBean.getUser_level() %></td></tr>
<tr><td class="td_name"><label for="Content">경험치</label></td><td class="td_content"><%=userBean.getUser_lv_exp() %> exp</td></tr>
<tr><td class="td_name"><label for="Content">총 거래 건수</label></td><td class="td_content"><%=userBean.getUser_project_count() %> 건</td></tr>
<tr><td class="td_name"><label for="Content">내 평점</label></td><td class="td_content"><%=userBean.getUser_score() %> 점</td></tr>
<tr><td class="td_name"><label for="Content">회원 타입</label></td><td class="td_content"><%=user_type %></td></tr>
<tr><td class="td_name"><label for="Content">로그인 횟수</label></td><td class="td_content"><%=userBean.getUser_login_count() %> 회</td></tr>
<tr><td class="td_name"><label for="Content">프로필 사진</label></td><td class="td_content">
<input type="file" id="editor_photo" name="editor_photo" style="display: none;"/>
<input type="button" id="photo_sel" value="파일선택"><p id="photo_p"><%=editorBean.getEditor_photo() %></p></td></tr>
<tr><td class="td_name"><label for="Content">한줄 소개</label></td><td class="td_content"><textarea id="editor_des" name="editor_des" cols="100" rows="5"><%=editorBean.getEditor_des()%></textarea></td></tr>
<tr><td class="td_name"><label for="DetailContent">본인 소개</label></td><td class="td_content">
<textarea id="DetailContent" name="editor_profile" ><%=editorBean.getEditor_profile() %></textarea>
</td></tr>
<tr><td class="td_name">이용 프로그램</td><td class="td_content">
<div class="check_list"><input type="checkbox" id="program1" name="editor_program" value="1" <%for(String a : editor_program){if(a.equals("1")){%>checked="checked"<%}}%>><label for="program1"></label><span class="checkbox">프리미어</span></div>
<div class="check_list"><input type="checkbox" id="program2" name="editor_program" value="2" <%for(String a : editor_program){if(a.equals("2")){%>checked="checked"<%}}%>><label for="program2"></label><span class="checkbox">파이널컷</span></div>
<div class="check_list"><input type="checkbox" id="program3" name="editor_program" value="3" <%for(String a : editor_program){if(a.equals("3")){%>checked="checked"<%}}%>><label for="program3"></label><span class="checkbox">베가스</span></div>
<div class="check_list"><input type="checkbox" id="program4" name="editor_program" value="4" <%for(String a : editor_program){if(a.equals("4")){%>checked="checked"<%}}%>><label for="program4"></label><span class="checkbox">애프터 이펙트</span></div>
<div class="check_list"><input type="checkbox" id="program5" name="editor_program" value="5" <%for(String a : editor_program){if(a.equals("5")){%>checked="checked"<%}}%>><label for="program5"></label><span class="checkbox">기타</span></div>
</td></tr>
<tr><td class="td_name">최종 납품 해상도</td><td class="td_content">
<div class="check_list"><input type="checkbox" id="solution1" name="editor_solution" value="1" <%for(String a : editor_solution){if(a.equals("1")){%>checked="checked"<%}}%>><label for="solution1"></label><span class="checkbox">HD</span></div>
<div class="check_list"><input type="checkbox" id="solution2" name="editor_solution" value="2" <%for(String a : editor_solution){if(a.equals("2")){%>checked="checked"<%}}%>><label for="solution2"></label><span class="checkbox">FHD</span></div>
<div class="check_list"><input type="checkbox" id="solution3" name="editor_solution" value="3" <%for(String a : editor_solution){if(a.equals("3")){%>checked="checked"<%}}%>><label for="solution3"></label><span class="checkbox">UHD</span></div>
<div class="check_list"><input type="checkbox" id="solution4" name="editor_solution" value="4" <%for(String a : editor_solution){if(a.equals("4")){%>checked="checked"<%}}%>><label for="solution4"></label><span class="checkbox">맞춤 가능</span></div>
</td></tr>
<tr><td class="td_name">작업 가능 항목</td><td class="td_content">
<div class="check_list"><input type="checkbox" id="inventory1" name="editor_inventory" value="1" <%for(String a : editor_inventory){if(a.equals("1")){%>checked="checked"<%}}%>><label for="inventory1"></label><span class="checkbox">컷 편집</span></div>
<div class="check_list"><input type="checkbox" id="inventory2" name="editor_inventory" value="2" <%for(String a : editor_inventory){if(a.equals("2")){%>checked="checked"<%}}%>><label for="inventory2"></label><span class="checkbox">오디오 싱크</span></div>
<div class="check_list"><input type="checkbox" id="inventory3" name="editor_inventory" value="3" <%for(String a : editor_inventory){if(a.equals("3")){%>checked="checked"<%}}%>><label for="inventory3"></label><span class="checkbox">BGM 삽입</span></div>
<div class="check_list"><input type="checkbox" id="inventory4" name="editor_inventory" value="4" <%for(String a : editor_inventory){if(a.equals("4")){%>checked="checked"<%}}%>><label for="inventory4"></label><span class="checkbox">효과음 삽입</span></div>
<div class="check_list"><input type="checkbox" id="inventory5" name="editor_inventory" value="5" <%for(String a : editor_inventory){if(a.equals("5")){%>checked="checked"<%}}%>><label for="inventory5"></label><span class="checkbox">모션 그래픽</span></div>
<div class="check_list"><input type="checkbox" id="inventory5" name="editor_inventory" value="5" <%for(String a : editor_inventory){if(a.equals("6")){%>checked="checked"<%}}%>><label for="inventory5"></label><span class="checkbox">템플릿 작업</span></div>
</td></tr>
<tr><td class="td_name">납품 파일 공유 방식</td><td class="td_content">
<div class="check_list"><input type="radio" id="upload1" name="editor_upload" value="1" <%if(upload==1){%>checked="checked"<%}%>><label for="upload1" ></label><span class="checkbox">이메일</span></div>
<div class="check_list"><input type="radio" id="upload2" name="editor_upload" value="2" <%if(upload==2){%>checked="checked"<%}%>><label for="upload2"></label><span class="checkbox">NAS</span></div>
<div class="check_list"><input type="radio" id="upload3" name="editor_upload" value="3" <%if(upload==3){%>checked="checked"<%}%>><label for="upload3"></label><span class="checkbox">웹하드</span></div>
<div class="check_list"><input type="radio" id="upload4" name="editor_upload" value="4" <%if(upload==4){%>checked="checked"<%}%>><label for="upload4"></label><span class="checkbox">모두 가능</span></div>
</td></tr>
<tr><td class="td_name">녹화에 이용된 캠</td><td class="td_content">
<div class="check_list"><input type="radio" id="work1" name="editor_work" value="1" <%if(work==1){%>checked="checked"<%}%>><label for="work1"></label><span class="checkbox">1캠</span></div>
<div class="check_list"><input type="radio" id="work2" name="editor_work" value="2" <%if(work==2){%>checked="checked"<%}%>><label for="work2"></label><span class="checkbox">2캠</span></div>
<div class="check_list"><input type="radio" id="work3" name="editor_work" value="3" <%if(work==3){%>checked="checked"<%}%>><label for="work3"></label><span class="checkbox">3캠</span></div>
<div class="check_list"><input type="radio" id="work4" name="editor_work" value="4" <%if(work==4){%>checked="checked"<%}%>><label for="work4"></label><span class="checkbox">4캠 이상</span></div>
</td></tr>
<tr><td class="td_name">실물 미팅</td><td class="td_content">
<div class="check_list"><input type="radio" id="meeting1" name="editor_meeting" value="1" <%if(meeting==1){%>checked="checked"<%}%>><label for="meeting1"></label><span class="checkbox">쌉가능</span></div>
<div class="check_list"><input type="radio" id="meeting2" name="editor_meeting" value="2" <%if(meeting==2){%>checked="checked"<%}%>><label for="meeting2"></label><span class="checkbox">불가능</span></div>
</td></tr>
<tr><td class="td_name">선호 작업 유형</td><td class="td_content">
<div class="check_list"><input type="radio" id="fort1" name="editor_fort" value="1" <%if(fort==1){%>checked="checked"<%}%>><label for="fort1"></label><span class="checkbox">건당 계약</span></div>
<div class="check_list"><input type="radio" id="fort2" name="editor_fort" value="2" <%if(fort==2){%>checked="checked"<%}%>><label for="fort2"></label><span class="checkbox">단기 계약</span></div>
<div class="check_list"><input type="radio" id="fort3" name="editor_fort" value="3" <%if(fort==3){%>checked="checked"<%}%>><label for="fort3"></label><span class="checkbox">장기 계약</span></div>
</td></tr>
<tr><td class="td_name">샘플 작업 가능 유무</td><td class="td_content">
<div class="check_list"><input type="radio" id="sample1" name="editor_sample" value="1" <%if(sample==1){%>checked="checked"<%}%>><label for="sample1"></label><span class="checkbox">가능</span></div>
<div class="check_list"><input type="radio" id="sample2" name="editor_sample" value="2" <%if(sample==2){%>checked="checked"<%}%>><label for="sample2"></label><span class="checkbox">불가능</span></div>
</td></tr>
<tr><td class="td_name"><label for="MinPrice">예상 단가</label></td><td class="td_content">
<div class="input_price"><input type="number" id="MinPrice" step="1000" min="1000" name="editor_ed_min_price" value="<%=min_price%>"> 원 ~ <input type="number" id="MaxPrice" step="1000" min="1000" name="editor_ed_max_price" value="<%=max_price%>"> 원</div>
</td></tr>
<tr><td class="td_name">거주지</td><td class="td_content">
<select name="editor_address" >
<option <%if(address.equals("서울")){%>selected="selected"<%}%>>서울</option>
<option <%if(address.equals("부산")){%>selected="selected"<%}%>>부산</option>
<option <%if(address.equals("대구")){%>selected="selected"<%}%>>대구</option>
<option <%if(address.equals("대전")){%>selected="selected"<%}%>>대전</option>
<option <%if(address.equals("인천")){%>selected="selected"<%}%>>인천</option>
<option <%if(address.equals("광주")){%>selected="selected"<%}%>>광주</option>
<option <%if(address.equals("울산")){%>selected="selected"<%}%>>울산</option>
<option <%if(address.equals("세종")){%>selected="selected"<%}%>>세종</option>
<option <%if(address.equals("경기")){%>selected="selected"<%}%>>경기</option>
<option <%if(address.equals("강원")){%>selected="selected"<%}%>>강원</option>
<option <%if(address.equals("충북")){%>selected="selected"<%}%>>충북</option>
<option <%if(address.equals("충남")){%>selected="selected"<%}%>>충남</option>
<option <%if(address.equals("경북")){%>selected="selected"<%}%>>경북</option>
<option <%if(address.equals("경남")){%>selected="selected"<%}%>>경남</option>
<option <%if(address.equals("전북")){%>selected="selected"<%}%>>전북</option>
<option <%if(address.equals("전남")){%>selected="selected"<%}%>>전남</option>
<option <%if(address.equals("제주")){%>selected="selected"<%}%>>제주</option>
</select>
</td></tr>
<!-- <tr><td class="td_name"><label for="Ref1">레퍼런스 링크</label></td><td class="td_content"> -->
<!-- <div class="link_alert"><img src="img/linkAlert.png"> 표시된 곳만 작성해주세요</div> -->
<!-- <div class="input_link"><input type="text" name="board_creator_cre_ref[]" id="Ref1" placeholder="링크를 업로드 하세요."></div> -->
<!-- <div class="input_link"><input type="text" name="board_creator_cre_ref[]" id="Ref2" placeholder="링크를 업로드 하세요."></div> -->
<!-- <div class="input_link"><input type="text" name="board_creator_cre_ref[]" id="Ref3" placeholder="링크를 업로드 하세요."></div> -->
<!-- </td></tr> -->
</table>
<div class="write_form_submit"><input type="submit" id="WriteSubmit" value="수정 완료"></div>
</form>
</div>

<jsp:include page="../inc/bottom.jsp"/>
</div>
</body>
</html>