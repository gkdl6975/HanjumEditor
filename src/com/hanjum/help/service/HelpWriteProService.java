package com.hanjum.help.service;

import java.sql.Connection;

import com.hanjum.help.dao.HelpDAO;
import com.hanjum.help.vo.HelpBean;
import static com.hanjum.db.JdbcUtil.*;
public class HelpWriteProService {
	
	public boolean registArticle(HelpBean helpbean) {
		System.out.println("HelpWriteProService - registArticle()");
		
		boolean isWriteSuccess = false;
		
		Connection con = getConnection();
		
		HelpDAO helpDAO = HelpDAO.getInstance();
		
		helpDAO.setConnection(con);
		
		int insertCount = helpDAO.insertHelp(helpbean);
		if(insertCount > 0) {
			commit(con); 
			isWriteSuccess = true; 
		} else {
			rollback(con);
		}
		
		close(con);

		return isWriteSuccess;
		
		
	}

}
