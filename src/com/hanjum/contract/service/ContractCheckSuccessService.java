package com.hanjum.contract.service;

import static com.hanjum.db.JdbcUtil.close;
import static com.hanjum.db.JdbcUtil.commit;
import static com.hanjum.db.JdbcUtil.getConnection;
import static com.hanjum.db.JdbcUtil.rollback;

import java.sql.Connection;

import com.hanjum.contract.dao.ContractDAO;
import com.hanjum.contract.vo.ContractBean;

public class ContractCheckSuccessService {
	
	public int checkContractStatus(String editor_id, String creator_id) throws Exception {
		System.out.println("ContractInsertProService - registArticle()");
		
		int check = 0; // contract테이블에 insert 여부 
		
		Connection con = getConnection();
		
		ContractDAO CDAO = ContractDAO.getinstance();
		
		CDAO.setConnection(con);
		
		check = CDAO.checkSuccessEditor(editor_id, creator_id);
		
		close(con);

		return check;
	}
}
