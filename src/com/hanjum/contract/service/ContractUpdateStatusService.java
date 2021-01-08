package com.hanjum.contract.service;

import static com.hanjum.db.JdbcUtil.close;
import static com.hanjum.db.JdbcUtil.commit;
import static com.hanjum.db.JdbcUtil.getConnection;
import static com.hanjum.db.JdbcUtil.rollback;

import java.sql.Connection;

import com.hanjum.contract.dao.ContractDAO;
import com.hanjum.contract.vo.ContractBean;

public class ContractUpdateStatusService {
	
	public boolean updateContractStatus(int board_id, int contract_status) throws Exception {
		System.out.println("ContractInsertProService - registArticle()");
		
		boolean isSuccess = false;
		
		Connection con = getConnection();
		
		ContractDAO CDAO = ContractDAO.getinstance();
		
		CDAO.setConnection(con);
		
		int updateCount = CDAO.updateStatus(board_id, contract_status);
		if(updateCount > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);

		return isSuccess;
	}
}
