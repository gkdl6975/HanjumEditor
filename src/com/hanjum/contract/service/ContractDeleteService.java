package com.hanjum.contract.service;

import static com.hanjum.db.JdbcUtil.close;
import static com.hanjum.db.JdbcUtil.commit;
import static com.hanjum.db.JdbcUtil.getConnection;
import static com.hanjum.db.JdbcUtil.rollback;

import java.sql.Connection;

import com.hanjum.contract.dao.ContractDAO;
import com.hanjum.contract.vo.ContractBean;

public class ContractDeleteService {
	
	public boolean deleteContract(int board_id, String contract_editor) throws Exception {
		System.out.println("ContractInsertProService - registArticle()");
		boolean isSuccess = false;
		Connection con = getConnection();
		ContractDAO CDAO = ContractDAO.getinstance();
		CDAO.setConnection(con);
		
		int deleteCount = CDAO.deleteContract(board_id, contract_editor);
		if(deleteCount > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);

		return isSuccess;
	}
	public boolean deleteContractOther(int board_id, String contract_editor) throws Exception {
		System.out.println("ContractInsertProService - registArticle()");
		boolean isSuccess = false;
		Connection con = getConnection();
		ContractDAO CDAO = ContractDAO.getinstance();
		CDAO.setConnection(con);
		
		int deleteCount = CDAO.deleteContractOther(board_id, contract_editor);
		if(deleteCount > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);

		return isSuccess;
	}
}
