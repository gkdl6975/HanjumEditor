package com.hanjum.contract.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.contract.service.ContractListService;
import com.hanjum.contract.vo.ContractBean;
import com.hanjum.vo.ActionForward;
import com.hanjum.vo.PageInfo;



public class ContractUpdateFormAction implements Action{
	

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("ContractListAction");
		
		
		ActionForward forward = null;
		
		int page = 1; // 현재 페이지 번호 저장할 변수
		int limit = 15; // 페이지 당 표시할 게시물 수를 결정하는 변수
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		if(request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		
		ContractListService contractlist = new ContractListService();
		int listCount = contractlist.getListCount();

		
		ArrayList<ContractBean> contractList = new ArrayList<ContractBean>();
		
		
		contractList = ContractListService.getArticleList(page, limit);
		
		// 페이지 계산 작업 수행
		// 1. 전체 페이지 수 계산
		//    (총 게시물 수 / 페이지 당 게시물 수 + 0.95) -> 정수로 변환
		int maxPage = (int)((double)listCount / limit + 0.95);
		
		// 2. 현재 페이지에서 보여줄 시작 페이지 번호(1, 11, 21 페이지 등)
		int startPage = ((int)((double)page / 10 + 0.9) - 1) * 10 + 1;
		
		// 3. 현재 페이지에서 보여줄 마지막 페이지 번호(10, 20, 30 페이지 등)
		int endPage = startPage + 10 - 1;
		
		// 4. 마지막 페이지가 현재 페이지에서 표시할 
		//    최대 페이지(전체 페이지) 수 보다 클 경우
		//    마지막 페이지 번호를 전체 페이지 번호로 대체
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 계산된 모든 페이지 정보를 PageInfo 객체에 저장
		PageInfo pageInfo = new PageInfo(
				page, maxPage, startPage, endPage, listCount);

		// request 객체의 setAttribute() 메서드를 호출하여
		// 게시물 목록 정보(ArrayList)와 페이지 정보(PageInfo) 객체를 저장
		request.setAttribute("contractList", contractList);
		request.setAttribute("pageInfo", pageInfo);
		
//		System.out.println("ContractListService 객체 생성 완료");
		forward = new ActionForward();
		forward.setPath("/board/admin_contract_update.jsp");  //실제 jsp 페이지 넣기!
		forward.setRedirect(false);  // 디폴트 false 이므로 생략 가능
		
		
		
		return forward;
		
		
	}

}
