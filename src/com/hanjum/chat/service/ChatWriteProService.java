package com.hanjum.chat.service;

import java.sql.Connection;

import com.hanjum.chat.dao.ChatDAO;
import com.hanjum.chat.vo.ChatBean;
import static com.hanjum.db.JdbcUtil.*;


public class ChatWriteProService {
		
	public boolean registChat(ChatBean chatBean) {
     
		System.out.println("ChatWriteProService - registChat()");
		
		boolean isWriteSuccess = false;
		
		Connection con = getConnection();
		
		ChatDAO chatDAO = ChatDAO.getInstance();
		
		chatDAO.setConnection(con);
		
		int insertCount = chatDAO.insertArticle(chatBean);
		
		if(insertCount > 0) {
        	commit(con);
        	isWriteSuccess = true;
        }else{
        	rollback(con);
        }
		
	        
	   close(con);
	   return isWriteSuccess;
       
	}
	

}