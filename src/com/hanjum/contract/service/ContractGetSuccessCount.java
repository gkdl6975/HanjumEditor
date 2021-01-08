package com.hanjum.contract.service;

import static com.hanjum.db.JdbcUtil.*;

import java.sql.Connection;

import com.hanjum.contract.dao.ContractDAO;
import com.hanjum.contract.vo.ContractBean;

public class ContractGetSuccessCount {

	public int getSuccessCount(String user_id) {
		System.out.println("ContractGetSuccessCount - getSuccessCount");
		Connection con = getConnection();
		ContractDAO CDAO = ContractDAO.getinstance();
		CDAO.setConnection(con);
		
		int count = CDAO.getContractSuccessCount(user_id);
		close(con);
		
		return count;

	}
}
