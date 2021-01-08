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
					<!-- DOC: To remove the search box from the= sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
					<li class="sidebar-search-wrapper">
						<!-- BEGIN RESPONSIVE QUICK SEARCH FORM --> <!-- DOC: Apply "sidebar-search-bordered" class the below search form to have bordered search box -->
						<!-- DOC: Apply "sidebar-search-bordered sidebar-search-solid" class the below search form to have bordered & solid search box -->

						<!-- END RESPONSIVE QUICK SEARCH FORM -->
					</li>
					<li class="nav-item start active open"><a href="javascript:;"
						class="nav-link nav-toggle"> <span class="title">관리자
								페이지</span> <span class="selected"></span>
					</a>
						<ul class="sub-menu">
								<li class="nav-item start"><a href="UserManage.uo"
									class="nav-link "> <span class="title">회원 관리</span> 
								</a></li>
								<li class="nav-item start "><a href="ContractList.ct" class="nav-link ">
										<span class="title">계약 관리 </span>
								</a></li>
								<li class="nav-item start "><a href="CategoryList.cg" class="nav-link ">
										<span class="title">Category 관리</span>
								</a></li>
								<li class="nav-item start "><a href="helpList.hp" class="nav-link ">
										<span class="title">Help 관리</span>
																			<span class="selected"></span>

							</a></li>

						</ul></li>
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
					계약 관리 수정/삭제 <small>계약상태 변경 또는 계약 내역을 삭제 할 수 있습니다.</small>
				</h1>



					<h1>계약 리스트</h1>

					<%
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					%>




							<input type="radio"
							name="showlist" id="sl5" value="5" 
							onclick="location.href='ContractUpdate.ct?limit=50'"><label
							for="sl5" class="sl_radio">50개씩보기</label>
							
							<input type="radio"
							name="showlist" id="sl4" value="4"
							onclick="location.href='ContractUpdate.ct?limit=30'"><label
							for="sl4" class="sl_radio">30개씩보기</label> 
							
							<input type="radio"
							name="showlist" id="sl3" value="3" 
							onclick="location.href='ContractUpdate.ct?limit=20'"><label
							for="sl3" class="sl_radio">20개씩보기</label> 
							
							<input type="radio"
							name="showlist" id="sl2" value="2"
							onclick="location.href='ContractUpdate.ct?limit=15'"><label
							for="sl2" class="sl_radio">15개씩보기</label> 

						<input type="radio" name="showlist" id="sl1" value="1"
							onclick="location.href='ContractUpdate.ct?limit=10'">
							<label
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
								<form action="ContractUpdatePro.ct" method=post">
							<tr align="center">
								<td><input type ="hidden" name="contract_id" value="<%=contractList.get(i).getContract_id()%>"><%=contractList.get(i).getContract_id()%></td>
								<td><%=contractList.get(i).getBoard_subject()%></td>
								<td><input type="text" name ="contract_price" value="<%=contractList.get(i).getContract_price()%>">원</td>							
								<td><%=contractList.get(i).getContract_creator()%></td>
								<td><%=contractList.get(i).getContract_editor()%></td>
								<td><%=contractList.get(i).getContract_address()%></td>
								<td><%=sdf.format(contractList.get(i).getContract_begin_date())%></td>
								<td><%=sdf.format(contractList.get(i).getContract_end_date()) %><td>					
									

							<select name ="contract_status">
							<option value="1" <%if(contractList.get(i).getContract_status()==1){%>selected="selected"<%}%>>계약 대기</option>
							<option value="2" <%if(contractList.get(i).getContract_status()==2){%>selected="selected"<%}%>>프로젝트 진행 중</option>
							<option value="3" <%if(contractList.get(i).getContract_status()==3){%>selected="selected"<%}%>>검토 중</option>								
							<option value="4" <%if(contractList.get(i).getContract_status()==4){%>selected="selected"<%}%>>프로젝트 종료</option>
							<option value="5" <%if(contractList.get(i).getContract_status()==5){%>selected="selected"<%}%>>프로젝트 중단</option>
							</select> 

									
								</td>
								<td><%=contractList.get(i).getBoard_id()%></td>
								<td><%=contractList.get(i).getWating()%>
								<td><input type="submit" class="btn" value="수정" >
								</td>
								<td><input type="button" class="btn" value="삭제" onclick="location.href='ContractDeletePro.ct?contract_id=<%=contractList.get(i).getContract_id()%>'"></td>
								</form>
	

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
	

	<hr>

	<div class="row">

		<div class="portlet light portlet-fit bordered">



			<!-- END CONTENT -->

		</div>
		<!-- END CONTAINER -->

	</div>

</body>


</html>