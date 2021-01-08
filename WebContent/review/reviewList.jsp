<%@page import="com.hanjum.user.vo.UserBean"%>
<%@page import="com.hanjum.vo.Constant"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.hanjum.vo.PageInfo"%>
<%@page import="com.hanjum.review.vo.ReviewBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<ReviewBean> articleList = (ArrayList<ReviewBean>)request.getAttribute("articleList");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
	int listCount = pageInfo.getListCount();
	
	UserBean userBean = (UserBean)session.getAttribute("userBean");
	int successCount = (int)request.getAttribute("SuccessCount");
	int reviewWriteCount = (int)request.getAttribute("reviewWriteCount");
%>    
<!DOCTYPE html>
<html>
<head>
<script src="js/jquery.min.js"></script>
 
  <script type="text/javascript">
 	$(function(){
 		$(document).on('click','.reviewUpdateBtn',function(){
 			var review_id = $(this).attr('id').replace('update','');
 			reviewUpdate(review_id,<%=nowPage%>);
 		});
 		
 	
 	})
	    function reviewUpdate(id, pageNum){
	 		$("#reviewWriteForm").load('ReviewUpdateForm.rv?review_id='+id+'&page='+pageNum);
	 		$("#reviewForm").attr("onsubmit","return reviewUpdateSubmit()");
	 		$("#reviewForm").attr("id","reviewUpdateForm");
	    }
 		
 	
    </script>
    <link rel="stylesheet" href="css/review.css">

</head>



<body> 
	<%
	if(articleList != null && listCount > 0) {
		for(int i = 0; i < articleList.size(); i++) {
			ReviewBean reviewBean = articleList.get(i);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 a hh:mm");
			String date = sdf.format(reviewBean.getReview_date());
			int score = (reviewBean.getReview_speciality()+reviewBean.getReview_satisfaction()
			+reviewBean.getReview_positivity()+reviewBean.getReview_communication())/4;
		%>	
	<div class="form-group mt-4 d-flex">
		<div class="review_item_1">
			<div class="review_content">
			<p class="star">
			<%
			for(int j = 0; j < 5; j++){
				if(score-2<0){ // 점수에서 2점을 뺄때 0보다 낮으면 판별시작 그외에는 1개 출력후 2빼기
					if(score-1<0){ // 반개도 못채울경우 0개 출력
					%>
	            <span class="fa fa-star-o"></span> 
					<%
					} else { // 1점이라도 남아있으면 반개 출력후 1 빼기
					score -= 1;
					%>	
				<span class="fa fa-star-half-o"></span>
					<%
					}
				} else { // 2점 남아있으면 1개 출력후 2 빼기
					%>
				<span class="fa fa-star"></span>	
					<%
					score -= 2;
				}
			}
			%>
	        </p>
			<span class="review_from_id"><%=reviewBean.getReview_from_id() %></span>
			<span class="review_score"></span>
			</div>		
			<div class="review_content">
			<%=reviewBean.getReview_content() %>
			</div>		
		</div>
		<div class="review_item_2">
			<div class="flex-col">
				<div class="review_col_content">전문성 <span class="float-r m-r-10"><progress value="<%=reviewBean.getReview_speciality()%>" max="10"></progress></span></div>
				<div class="review_col_content">만족도 <span class="float-r m-r-10"><progress value="<%=reviewBean.getReview_satisfaction()%>" max="10"></progress></span></div>
				<div class="review_col_content">적극성 <span class="float-r m-r-10"><progress value="<%=reviewBean.getReview_positivity()%>" max="10"></progress></span></div>
				<div class="review_col_content">소통 <span class="float-r m-r-10"><progress value="<%=reviewBean.getReview_communication()%>" max="10"></progress></span></div>
				<div class="review_col_content txt-right">
				<%
				if(reviewBean.getReview_from_id().equals(userBean.getUser_id())){
				%>
				<input type="button" class="btn btn-primary reviewUpdateBtn" value="수정" id="update<%=articleList.get(i).getReview_id() %>" />
				<input type="button" class="btn btn-white reviewDeleteBtn" value="삭제" id="delete<%=articleList.get(i).getReview_id() %>"  />
				<%
				}
				%>
				<%=date %>
				</div>
			</div>
		</div>
	</div>
	<hr>
		<%} %>
		<div class="row mt-5">
						<div class="col">
							<div class="block-27 text-center">
								<ul>
								<%
								int pageSize = Constant.REVIEW_PAGE_SIZE;
								String pageUrl = "ReviewList.rv?user_id="+articleList.get(0).getUser_id();
								if(startPage > pageSize){ %>
									<li><a class="page-move" data-href="<%=pageUrl%>&page=<%=startPage-pageSize %>">&lt;</a></li>
									<% 
										}
									for(int i=startPage; i<=endPage; i++){
										if(i == nowPage){
											%>
									<li class="active"><span><%=i %></span></li>
									<% } else { %>
									<li><a class="page-move" data-href="<%=pageUrl%>&page=<%=i%>"><%=i %></a></li>
									<% } 
									}
									if(endPage < maxPage){ 
									%>
									<li><a class="page-move" data-href="<%=pageUrl%>&page=<%=startPage+pageSize %>">&gt;</a></li>
									<%
									}
									%>
								</ul>
							</div>
						</div>
					</div>
		
		<%
		} else {
	%>
	<div class="form-group mt-4 d-flex">
	등록된 리뷰가 없습니다
	</div>
	<%
	}
	%>
	<hr>
	<form action="" method="post" id="reviewForm" onsubmit="return reviewWrite()">
	<input type="hidden" name="writer_id" value="<%=request.getAttribute("writer_id")%>">
	<div class="form-group mt-4 d-flex" id="reviewWriteForm">
	<%
		if(successCount > 0 && reviewWriteCount == 0){
	%>
		<div class="review_item_1">
			<div class="review_content">
				<label class="label" for="reviewContent">리뷰쓰기</label>
			    <input type="text" id="reviewContent" style="width: 90%; height: 100px;" class="form-control" name="review_content" required="required"/>
			</div>		
		</div>
		<div class="review_item_2">
			<div class="flex-col">
				<div class="review_col_content">전문성 
				<span class="float-r m-r-10">
				<input type="range" name="review_speciality" min="0" max="10" step="1" value="0" oninput="document.getElementById('value1').innerHTML=this.value;" required="required">
       			<span id="value1"></span>
       			</span>
				</div>
				<div class="review_col_content">만족도
				<span class="float-r m-r-10">
				<input type="range" name="review_satisfaction" min="0" max="10" step="1" value="0" oninput="document.getElementById('value2').innerHTML=this.value;" required="required">
       			<span id="value2"></span>
       			</span>
       			</div>
				<div class="review_col_content">적극성
				<span class="float-r m-r-10">
				<input type="range" name="review_positivity" min="0" max="10" step="1" value="0" oninput="document.getElementById('value3').innerHTML=this.value;" required="required">
       			<span id="value3"></span>
       			</span>
       			</div>
				<div class="review_col_content">소통
				<span class="float-r m-r-10">
				<input type="range" name="review_communication" min="0" max="10" step="1" value="0" oninput="document.getElementById('value4').innerHTML=this.value;" required="required">
       			<span id="value4"></span>
       			</span>
       			</div>
       			<div class="review_col_content">
       			<span class="float-r m-r-10">
       			<input type="submit" class="btn btn-primary" value="작성"/>
       			</span>
       			</div>
			</div>
		</div>
		<%
		}
		%>
	</div>
	</form>
	<hr>
</body>
</html>
