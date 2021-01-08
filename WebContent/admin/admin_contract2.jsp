<%@page import="com.hanjum.contract.vo.ContractSearchBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.hanjum.vo.PageInfo"%>
<%@page import="com.hanjum.contract.vo.ContractBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>


<%
	ArrayList<ContractBean> contractList = (ArrayList<ContractBean>) request.getAttribute("contractList");
String status = null;

PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
int nowPage = pageInfo.getPage();
int maxPage = pageInfo.getMaxPage();
int startPage = pageInfo.getStartPage();
int endPage = pageInfo.getEndPage();
int listCount = pageInfo.getListCount();

ContractSearchBean csb = new ContractSearchBean();

String search_word = csb.getSearch_word();
%>


<html>
<!--<![endif]-->
<!-- BEGIN HEAD -->

<head>
<meta charset="utf-8" />
<title>한줌 관리자 페이지</title>

<link href="css/manager.css" rel="stylesheet" type="text/css" />
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link href="css/themes.css" rel="stylesheet" type="text/css"
	id="style_color" />
<link href="css/contract.css" rel="stylesheet" type="text/css" />




<script src="js/jquery-3.5.1.js"></script>
<script src="js/manager.js"></script>
</head>
<!-- END HEAD -->

<body>
	<jsp:include page="../inc/admin_top.jsp" />



	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN SIDEBAR -->
		<div class="page-sidebar-wrapper">
			<!-- BEGIN SIDEBAR -->
			<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
			<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
			<div class="page-sidebar navbar-collapse collapse">
				<!-- BEGIN SIDEBAR MENU -->
				<!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
				<!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
				<!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
				<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
				<!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
				<!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
				<ul class="page-sidebar-menu  page-header-fixed "
					data-keep-expanded="false" data-auto-scroll="true"
					data-slide-speed="200" style="padding-top: 20px">
					<!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<!-- END SIDEBAR TOGGLER BUTTON -->
					<!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
					<li class="sidebar-search-wrapper">
						<!-- BEGIN RESPONSIVE QUICK SEARCH FORM --> <!-- DOC: Apply "sidebar-search-bordered" class the below search form to have bordered search box -->
						<!-- DOC: Apply "sidebar-search-bordered sidebar-search-solid" class the below search form to have bordered & solid search box -->

						<!-- END RESPONSIVE QUICK SEARCH FORM -->
					</li>
					<li class="nav-item start active open">
					<a href="javascript:;" class="nav-link nav-toggle"> 
					<span class="title" style="font-weight: bold;">관리자 페이지</span> 
					</a>
<!-- 							<ul class="sub-menu"> -->
<!-- 									<li class="nav-item start"><a href="UserManage.uo" class="nav-link ">  -->
<!-- 										<span class="title">회원 관리</span>  -->
<!-- 									</a></li> -->
<!-- 									<li class="nav-item start "><a href="ContractList.ct" class="nav-link "> -->
<!-- 										<span class="title">계약 관리 </span> -->
<!-- 									</a></li> -->
<!-- 									<li class="nav-item start"><a href="CategoryList.cg" class="nav-link"> -->
<!-- 										<span class="title">Category 관리</span> -->
<!-- 									</a></li> -->
<!-- 									<li class="nav-item start "><a href="helpList.hp" class="nav-link "> -->
<!-- 										<span class="title">Help 관리</span> -->
<!-- 									</a></li> -->
<!-- 							</ul> -->
					</li>
					<li class="nav-item start"><a href="UserManage.uo" class="nav-link "> 
						<span class="title">회원 관리</span> 
					</a></li>
					<li class="nav-item start active"><a href="ContractList.ct" class="nav-link ">
						<span class="title">계약 관리 </span>
						<span class="selected"></span>
					</a></li>
					<li class="nav-item start"><a href="CategoryList.cg" class="nav-link">
						<span class="title">Category 관리</span>
						</a></li>
					<li class="nav-item start"><a href="helpList.hp" class="nav-link ">
						<span class="title">Help 관리</span>
						</a></li>
					<li class="nav-item start"><a href="UserReportManage.uo" class="nav-link ">
						<span class="title">신고 관리</span>
						</a></li>
				</ul>
			</div>
			<!-- END SIDEBAR -->

		</div>

		<!-- END SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<!-- BEGIN CONTENT BODY -->

			<div class="page-content">
				<!-- BEGIN PAGE HEADER-->

				<!-- BEGIN PAGE BAR -->
				<div class="page-bar">
					<ul class="page-breadcrumb">
						<li><a href="index.html">관리자 페이지</a> <i class="fa fa-circle"></i>
						</li>
						<li><span>계약 관리</span></li>
					</ul>

				</div>
				<!-- END PAGE BAR -->
				<!-- BEGIN PAGE TITLE-->
				<h1 class="page-title">
					계약 관리 <small>설정을 통해 원하는 조건의 계약을 검색 할 수 있습니다.</small>
				</h1>
				<!-- END PAGE TITLE-->
				<!-- END PAGE HEADER-->

				<div id="wrap">


					<form action="ContractSeartch.ct" method="post"
						name="contractSearch">
						<table id="t1">
							<tr>
								<th>검색어</th>
								<td><select name="searchtype">
										<!-- 	<option> 검색 조건을 선택해주세요 </option> -->
										<option value="contract_id">계약 번호</option>
										<option value="board_subject">게시글 명</option>
										<option value="contract_creator">크리에이터 명</option>
										<option value="contract_editor">편집자 명</option>
										<option value="board_id">게시글 번호</option>
								</select> <input type="text" name="search_word" value=""> <!-- 본인이 검색한 결과가뜨게 해야함 -->
								</td>
							</tr>
							<tr>
								<th>기간</th>

								<td><input type="radio" name="period" id="ra1" value="1"
									checked="checked"><label for="ra1" class="period_radio">오늘</label>
									<input type="radio" name="period" id="ra2" value="2"><label
									for="ra2" class="period_radio">어제</label> <input type="radio"
									name="period" id="ra3" value="3"><label for="ra3"
									class="period_radio">3일</label> <input type="radio"
									name="period" id="ra4" value="4"><label for="ra4"
									class="period_radio">7일</label> <input type="radio"
									name="period" id="ra5" value="5"><label for="ra5"
									class="period_radio">15일</label> <input type="radio"
									name="period" id="ra6" value="6"><label for="ra6"
									class="period_radio">1개월</label> <input type="radio"
									name="period" id="ra7" value="7"><label for="ra7"
									class="period_radio">3개월</label> <input type="radio"
									name="period" id="ra8" value="8"><label for="ra8"
									class="period_radio">6개월</label> <input type="date"
									id="date_begin" name=date_begin value=""> ~ <input
									type="date" id="date_end" name=date_end value=""> <input
									type="radio" name="date_check" value="on"> on <input
									type="radio" name="date_check" value="off" checked="checked">
									off</td>

							</tr>

							<tr>
								<th>지역</th>
								<td><input type="checkbox" name="region" value="서울">서울
									<input type="checkbox" name="region" value="부산">부산 <input
									type="checkbox" name="region" value="대구">대구 <input
									type="checkbox" name="region" value="대전">대전 <input
									type="checkbox" name="region" value="인천">인천 <input
									type="checkbox" name="region" value="광주">광주 <input
									type="checkbox" name="region" value="울산">울산 <input
									type="checkbox" name="region" value="세종">세종 <input
									type="checkbox" name="region" value="경기">경기 <input
									type="checkbox" name="region" value="강원">강원 <input
									type="checkbox" name="region" value="충북">충북 <input
									type="checkbox" name="region" value="충남">충남 <input
									type="checkbox" name="region" value="경북">경북 <input
									type="checkbox" name="region" value="경남">경남 <input
									type="checkbox" name="region" value="전북">전북 <input
									type="checkbox" name="region" value="전남">전남 <input
									type="checkbox" name="region" value="제주">제주</td>
							</tr>
							<tr>
								<th>계약 상태</th>
								<td><input type="checkbox" name="contract_status" value="1">계약
									대기 <input type="checkbox" name="contract_status" value="2">프로젝트
									진행 중 <input type="checkbox" name="contract_status" value="3">프로젝트
									제출 완료(검토 중) <input type="checkbox" name="contract_status"
									value="4">프로젝트 종료 <input type="checkbox"
									name="contract_status" value="5">프로젝트 중단</td>
							</tr>
							<tr>
								<th>계약단가 설정</th>
								<td><input type="text" name="contract_pay1" value="0">원
									~ <input type="text" name="contract_pay2" value="10000000">원
								</td>

							</tr>
							<!-- 				<th>회원 레벨 구분</th> -->
							<!-- 				<td rowspan="2"> -->
							<!-- 				<input type="text" name="contract_payment" value="1"> ~  -->
							<!-- 					<input type="text" name="contract_payment" value="10">  -->
							<!-- 				</td> -->
						</table>
						<input type="submit" value="검색" class="btn"> &nbsp;&nbsp;
						<input type="reset" value="검색조건 초기화" class="btn"> &nbsp;&nbsp;

					</form>


					<h1>계약 리스트</h1>

					<%
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					%>


<!-- 					<form action="ContractInsertPro.ct" method="post" -->
<!-- 						name="contarctInsert"> -->
<!-- 						<input type="submit" value="계약체결  => contract insert 발생"> -->
<!-- 					</form> -->
					<!-- 				----------------------------------------------------------------------		 -->
					<form action="ContractUpdate.ct" method="post" >
						<input type="submit" value="수정/삭제" class="sd btn"><br>
						
					</form>

					<input type="radio" name="showlist" id="sl5" value="5"
						<%if (request.getServletPath().equals("ContractList.ct?limit=50")) {%>
						checked="checked" <%}%>
						onclick="location.href='ContractList.ct?limit=50'"><label
						for="sl5" class="sl_radio">50개씩보기</label> 
						<input type="radio"
						name="showlist" id="sl4" value="4"
						onclick="location.href='ContractList.ct?limit=30'"><label
						for="sl4" class="sl_radio">30개씩보기</label> 
						<input type="radio"
						name="showlist" id="sl3" value="3" 
						onclick="location.href='ContractList.ct?limit=20'"><label
						for="sl3" class="sl_radio">20개씩보기</label> 
						<input type="radio"
						name="showlist" id="sl2" value="2"
						onclick="location.href='ContractList.ct?limit=15'"><label
						for="sl2" class="sl_radio">15개씩보기</label> 
						
						<input type="radio"
						name="showlist" id="sl1" value="1"
						onclick="location.href='ContractList.ct?limit=10'"> <label
						for="sl1" class="sl_radio">10개씩보기</label>


					<div id="side">
						<table>
							<tr>
								<th>계약 번호</th>
								<th>게시글 명</th>
								<th>계약 단가</th>
								<th>크리에이터</th>
								<th>편집자</th>
								<th>지역</th>
								<th>프로젝트 시작일</th>
								<th>프로젝트 종료일</th>
								<th>계약 상태</th>
								<th>게시글 번호</th>
								<th>지원자 수</th>

							</tr>
							<%
								for (int i = 0; i < contractList.size(); i++) {
							%>
							<tr align="center">
								<td><%=contractList.get(i).getContract_id()%></td>
								<td><%=contractList.get(i).getBoard_subject()%></td>
								<td><%=contractList.get(i).getContract_price()%>원</td>
								<td><%=contractList.get(i).getContract_creator()%></td>
								<td><%=contractList.get(i).getContract_editor()%></td>
								<td><%=contractList.get(i).getContract_address()%></td>
								<td><%=sdf.format(contractList.get(i).getContract_begin_date())%>
								<td><%=sdf.format(contractList.get(i).getContract_end_date())%>
								<td>
									<%
										switch (contractList.get(i).getContract_status()) {
									case 1:
										status = "계약 대기";

										break;
									case 2:
										status = "프로젝트 진행 중";
										break;
									case 3:
										status = "검토 중";
										break;
									case 4:
										status = "프로젝트 종료";
										break;
									case 5:
										status = "프로젝트 중단";
										break;
									default:
										status = " ?? ";

									}
									%>
								<%=status %>
								</td>
								<td><%=contractList.get(i).getBoard_id()%></td>
								<td><%=contractList.get(i).getWating()%></td>

							</tr>

							<%
								}
							%>

						</table>


					</div>

					<section id="pageList" style="text-align: center;">
						<%
							if (nowPage <= 1) {
						%>
						<input type="button" value="이전" class="btn">&nbsp;
						<%
							} else {
						%>
						<input type="button" value="이전" class="btn"
							onclick="location.href='ContractList.ct?page=<%=nowPage - 1%>'">&nbsp;
						<%
							}
						%>

						<%
							for (int i = startPage; i <= endPage; i++) {
							if (i == nowPage) {
						%>
						[<%=i%>]&nbsp;
						<%
							} else {
						%>
						<a href="ContractList.ct?page=<%=i%>">[<%=i%>]
						</a>&nbsp;
						<%
							}
						%>
						<%
							}
						%>

						<%
							if (nowPage >= maxPage) {
						%>
						<input type="button" value="다음" class="btn">
						<%
							} else {
						%>
						<input type="button" value="다음" class="btn"
							onclick="location.href='ContractList.ct?page=<%=nowPage + 1%>'">
						<%
							}
						%>
					</section>


				</div>
			</div>
		</div>
	</div>

	<hr>

	<div class="row">

		<div class="portlet light portlet-fit bordered">



			<!-- END CONTENT -->

		</div>
		<!-- END CONTAINER -->

	</div>

</body>


</html>