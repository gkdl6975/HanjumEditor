package com.hanjum.contract.service;

import static com.hanjum.db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.hanjum.contract.dao.ContractDAO;
import com.hanjum.contract.vo.ContractBean;

public class ContractDeleteProService {
	
	public boolean removeContract(int contract_id) {
		// 글 수정 작업 요청 수행
		boolean isDeleteSuccess = false;
		
		// 1(공통). Connection 객체 가져오기
		Connection con = getConnection();
		
		// 2(공통). BoardDAO 객체 가져오기
		ContractDAO CDAO = ContractDAO.getinstance();
		
		// 3(공통). BoardDAO 객체에 Connection 객체 전달
		CDAO.setConnection(con);
		
		// 4. BoardDAO 클래스의 deleteArticle() 메서드를 호출하여 글 삭제
		//    => 파라미터 : 글번호(board_num), 리턴타입 : int(deleteCount)
		int deleteCount = CDAO.deleteContract(contract_id);
		
		// 5. 글 삭제 결과에 대한 판별 작업 수행
		// => deleteCount 가 0보다 크면 commit 수행, isDeleteSuccess 를 true 변경
		// => 아니면 rollback 수행
		if(deleteCount > 0) {
			commit(con);
			isDeleteSuccess = true;
		} else {
			rollback(con);
		}
		
		// 6(공통). Connection 객체 반환하기
		close(con);
		
		// 7. 결과 리턴
		return isDeleteSuccess;
	}

}
