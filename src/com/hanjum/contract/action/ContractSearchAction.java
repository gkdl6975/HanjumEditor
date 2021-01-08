package com.hanjum.contract.action;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.contract.service.ContractSearchService;
import com.hanjum.contract.vo.ContractBean;
import com.hanjum.contract.vo.ContractSearchBean;
import com.hanjum.vo.ActionForward;
import com.hanjum.vo.PageInfo;


public class ContractSearchAction implements Action {


	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ContractSearchAction - 1");

		ActionForward forward = null;

		int page = 1;
		int limit = 10;


//		-------검색과 관련된 파라미터 -----------------
		Calendar cal_begin = Calendar.getInstance();
		Calendar cal_end = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String datecheck = null;   // 기간 검색  on / off 체크
		int period = 0;
//		LocalDateTime contract_begin_date = LocalDateTime.now();
//		LocalDateTime contract_end_date = LocalDateTime.now();;
		String[] region = null;
		String[] contract_status = null;
		
		int contract_pay1 = 0;
		int contract_pay2 = 0;  // null 방지를 위해 임의값 넣음
		
		String search_word = null;
		String searchtype = null;
		
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		if (request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}


		if (request.getParameter("period") != null) {
			period = Integer.parseInt(request.getParameter("period"));
		}

		if (request.getParameter("date_check") != null) {
			datecheck = request.getParameter("date_check");
		}

		if (request.getParameter("region") != null) {
			region = request.getParameterValues("region");
		}
		
		if (request.getParameter("contract_status") != null) {
			contract_status = request.getParameterValues("contract_status");
		}
		
		if (request.getParameter("contract_status") != null) {
			contract_status = request.getParameterValues("contract_status");
		}
		
		if (request.getParameter("contract_pay1") != null) {
			contract_pay1 = Integer.parseInt(request.getParameter("contract_pay1"));
		}
		
		if (request.getParameter("contract_pay2") != null) {
			contract_pay2 = Integer.parseInt(request.getParameter("contract_pay2"));
		}

	    if (request.getParameter("search_word") != null || request.getParameter("search_word").equals(""))   {
	        search_word = request.getParameter("search_word");
	    }
	    
		if(request.getParameter("searchtype") != null) {
			searchtype = request.getParameter("searchtype");
		}
			
		
		
		cal_begin.setTime(new Date());
		
		if(datecheck.equals("on")) {
			switch (period) {
			case 1:   // 오늘 
				
				break;
			case 2:   // 어제
				 cal_begin.add(Calendar.DATE,-1);
				break;
			case 3:   // 3일전
				cal_begin.add(Calendar.DATE,-3);
				break;
			case 4:   // 7일전
				cal_begin.add(Calendar.DATE,-7);
				break;
			case 5:   // 15일전
				cal_begin.add(Calendar.DATE,-15);
				break;
			case 6:   // 1개월 전
				cal_begin.add(Calendar.MONTH,-1);
				break;
			case 7:   // 3개월 전
				cal_begin.add(Calendar.MONTH,-3);
				break;
			case 8:   // 6개월 전
				cal_begin.add(Calendar.MONTH,-6);
				break;

			default:
				System.out.println("날짜 체크 오류");
				break;
			}			
		}
		
		System.out.println("수정 날짜는" + sdf.format(cal_begin.getTime()));
		
		
		
		
		ContractSearchBean csb = new ContractSearchBean();
		csb.setRegion(region);   // 지역선택 체크박스 
		csb.setDate_check(datecheck);
		csb.setSearch_begin_date(sdf.format(cal_begin.getTime()));  // 수정된 시작 시간 넣기
			cal_end.add(Calendar.DATE,+1);  //오늘날짜 까지 포함시키려면 오늘날 기준 +1일 해야함
			csb.setSearch_end_date(sdf.format(cal_end.getTime()));   // 오늘 날짜 넣기
		csb.setContract_status(contract_status); // 계약 조건
		csb.setContract_pay1(contract_pay1);   // 계약금 범위 1번값
		csb.setContract_pay2(contract_pay2);   // 계약금 범위 2번값
		csb.setSearchtype(searchtype);  // 검색 타입
		csb.setSearch_word(search_word);  // 검색어 명
		
		
		


		ContractSearchService csservice = new ContractSearchService();
		int listCount = csservice.getSearchListCount(csb);
		
		ArrayList<ContractBean> contractSearchList = new ArrayList<ContractBean>();
		
		contractSearchList = ContractSearchService.getSearchList(page,limit,csb);
		
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
		request.setAttribute("contractList", contractSearchList);
		request.setAttribute("pageInfo", pageInfo);
		
		
		forward = new ActionForward();
		forward.setPath("/admin/admin_contract2.jsp");  //실제 jsp 페이지 넣기!
		forward.setRedirect(false);  // 디폴트 false 이므로 생략 가능
		
		
		
		
			
		return forward;
	}

}
