package com.hanjum.contract.service;

import static com.hanjum.db.JdbcUtil.*;

import java.sql.Connection;

import com.hanjum.contract.dao.ContractDAO;
import com.hanjum.contract.vo.ContractBean;

public class ContractGetInfoService {

	public ContractBean getContractInfo(int board_id) {
		System.out.println("ContractGetInfoService - getContractInfo");
		Connection con = getConnection();
		ContractDAO CDAO = ContractDAO.getinstance();
		CDAO.setConnection(con);
		
		ContractBean contractBean = CDAO.selectContractInfo(board_id);
		close(con);
		
		return contractBean;

	}
}
