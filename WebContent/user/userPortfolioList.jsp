<%@page import="com.hanjum.board.vo.EditorBean"%>
<%@page import="com.hanjum.user.vo.PortfolioBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <style>
    .tx_overflow {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    display: inline-block;
    }
    .portfolioBtnArea{
    	display : none;
    }
    .portfolioGroup:hover > .portfolioBtnArea{
    	display : inline;
    }
    </style>
        <script src="js/portInfo.js"></script>
    
    <%	
    	int pf_count = 0;
    	if(request.getAttribute("pf_count") != null){
    		pf_count = (int)request.getAttribute("pf_count");
    	}
    	ArrayList<PortfolioBean> portfolioList = (ArrayList<PortfolioBean>)request.getAttribute("portfolioList");
   		EditorBean editorBoard = null;
    	if(request.getAttribute("editorBoard") != null){
   			editorBoard = (EditorBean)request.getAttribute("editorBoard");
   		}
    	%>
    		<%
    		if(editorBoard != null){
    		%>
 			 <div class="form-group">
			 <span class="form-group-content">
			 	이력서 제목 <span class="float-r m-r-10"><span class="color-primary"><%=editorBoard.getBoard_subject() %> </span></span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	이력서 조회수 <span class="float-r m-r-10"><span class="color-primary"><%=editorBoard.getBoard_readcount() %></span></span>
			 </span>             
             </div>
             <%
    		}
             %>
             <div class="form-group">
			 <span class="form-group-content">
			 	포트폴리오 <span class="float-r m-r-10"><span class="color-primary" title="포트폴리오 보기"><%=pf_count %></span>개</span>
			 </span>             
             </div>
             <div id="portfolio_write">
<!--              포트폴리오 작성 폼 -->
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 <span class="m-r-10"><a class="m-l-10" id="portfolioWrite">포트폴리오 추가하기</a></span>
			 </span>             
             </div>
             <%
             for(int i = 0; i < portfolioList.size(); i++){
            	 PortfolioBean portfolioBean = portfolioList.get(i);
            	 String genre = portfolioBean.getEditor_pf_category()
							.replace("1", "유튜브")
							.replace("2", "홍보")
							.replace("3", "광고")
							.replace("4", "뮤직비디오")
							.replace("5", "드라마")
							.replace("6", "모션그래픽")
							.replace("7", "기타");
             %>
             <div id="portfolioForm<%=portfolioBean.getEditor_pf_id() %>">
             <div class="form-group">
			 <span class="form-group-content portfolioGroup">
			 	<span class="tx_overflow" style=" width: 80%;">장르 <span class="color-primary"><%=genre %></span>
			 	<span class="m-l-10"><%=portfolioBean.getEditor_pf_subject() %></span></span><span class="portfolioBtnArea" id="portfolioBtnArea<%=portfolioBean.getEditor_pf_id()%>"><a id="portfolioUpdate<%=portfolioBean.getEditor_pf_id()%>" class="portfolioUpdate">수정</a> <a class="m-l-10 portfolioDelete" id="portfolioDelete<%=portfolioBean.getEditor_pf_id()%>">삭제</a></span>
			 	<span class="float-r m-r-10" style="display: block; width: 90%;"><a href="<%=portfolioBean.getEditor_pf_link()%>" target="_brank"><%=portfolioBean.getEditor_pf_link()%></a></span>
			 </span>             
             </div>
             </div>
             <%
             }
             %> 			