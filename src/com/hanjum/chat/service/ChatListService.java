package com.hanjum.chat.service;

import static com.hanjum.db.JdbcUtil.*; 

import java.sql.Connection;
import java.util.ArrayList;

import com.hanjum.chat.dao.ChatDAO;
import com.hanjum.chat.vo.ChatBean;
import com.hanjum.chat.vo.ChatListBean;


public class ChatListService {

	public ArrayList<ChatBean> getListCount(int board_id) throws Exception {
		System.out.println("ChatListService - getListCount()");
		int listCount = 0;
		ArrayList<ChatBean> list = new ArrayList<ChatBean>();
		
		// 1(공통). Connection 객체 가져오기
		Connection con = getConnection();
				
		// 2(공통). BoardDAO 객체 가져오기
		ChatDAO chatDAO = ChatDAO.getInstance();
				
		// 3(공통). BoardDAO 객체에 Connection 객체 전달
		chatDAO.setConnection(con);
				
		// 4. BoardDAO 객체의 selectListCount() 메서드 호출하여
		//    전체 게시물 수 가져오기
		list = chatDAO.selectListCount(board_id);
				
		// 5(공통). Connection 객체 반환하기
		close(con);
				
		// 6. 전체 게시물 수 리턴
		return list;
		
	}

	public ArrayList<ChatListBean> getChatlist(String user_id) {
		// 1(공통). Connection 객체 가져오기
		Connection con = getConnection();
						
		// 2(공통). BoardDAO 객체 가져오기
		ChatDAO chatDAO = ChatDAO.getInstance();
						
		// 3(공통). BoardDAO 객체에 Connection 객체 전달
		chatDAO.setConnection(con);		
		
		ArrayList<ChatListBean> list = chatDAO.selectChatList(user_id);
		
		close(con);
		return list;
	}
	
	
	

}
