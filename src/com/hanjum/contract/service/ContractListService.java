package com.hanjum.contract.service;



import java.sql.Connection;
import java.util.ArrayList;

import com.hanjum.contract.dao.ContractDAO;
import com.hanjum.contract.vo.ContractBean;


import static com.hanjum.db.JdbcUtil.*;




public class ContractListService {
	
	public int getListCount() throws Exception {
//		System.out.println("BoardListService - getListCount()");
		int listCount = 0;
		
		// 1(공통). Connection 객체 가져오기
		Connection con = getConnection();
		
		// 2(공통). BoardDAO 객체 가져오기
		ContractDAO CDAO = ContractDAO.getinstance();
				
		// 3(공통). BoardDAO 객체에 Connection 객체 전달
		CDAO.setConnection(con);
		
		// 4. BoardDAO 객체의 selectListCount() 메서드 호출하여
		//    전체 게시물 수 가져오기
		listCount = CDAO.selectListCount();
		
		// 5(공통). Connection 객체 반환하기
		close(con);
		
		// 6. 전체 게시물 수 리턴
		return listCount;
	}
	
	public static ArrayList<ContractBean> getArticleList(int page, int limit) throws Exception{
		
		ArrayList<ContractBean> contractList = null;
		
		// 1. 공통 Connetion 객체 가져오기
		Connection con = getConnection();
		// 2.(공통) BoardDAO 객체 가져오기
		ContractDAO CDAO = ContractDAO.getinstance();
				
		// 3.(공통) BoardDAO 객체에 Connection 객체 전달
		CDAO.setConnection(con);
		//4단계
		contractList = CDAO.selectContractList(page, limit);
		close(con);
		return contractList;
	}


}
