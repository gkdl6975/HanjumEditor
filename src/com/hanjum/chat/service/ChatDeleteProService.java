package com.hanjum.chat.service;

import java.sql.Connection;
import static com.hanjum.db.JdbcUtil.*;
import com.hanjum.chat.dao.ChatDAO;

public class ChatDeleteProService {

	public static boolean isArticleWriter(String chat_editor_id, String chat_creator_id) {
		
		System.out.println("ChatDeleteProService");
		boolean isArticleWriter = false;
		
		Connection con = getConnection();
		
		ChatDAO chatDAO = ChatDAO.getInstance();
		
		chatDAO.setConnection(con);
		
		isArticleWriter = chatDAO.isArticleChatWriter(chat_editor_id,chat_creator_id); 
		
		close(con);
		
		return isArticleWriter;
	}
	
	public boolean deleteChat(int board_id) {
		System.out.println("deleteChat");
		boolean isSuccess = false;
		Connection con = getConnection();
		
		ChatDAO chatDAO = ChatDAO.getInstance();
		
		chatDAO.setConnection(con);
		
		int deleteCount = chatDAO.deleteChat(board_id);
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
