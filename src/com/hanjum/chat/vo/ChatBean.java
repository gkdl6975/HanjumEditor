package com.hanjum.chat.vo;

import java.sql.Timestamp;

public class ChatBean {
	
	private int chat_id;
	private String chat_to_id;
	private String chat_from_id;
	private String chat_content;
	private Timestamp chat_date;
	private int board_id;
	
	public ChatBean() {}
	
	public ChatBean(int chat_id, String chat_to_id, String chat_from_id, String chat_content,
			Timestamp chat_date, int board_id) {
		super();
		this.chat_id = chat_id;
		this.chat_to_id = chat_to_id;
		this.chat_from_id = chat_from_id;
		this.chat_content = chat_content;
		this.chat_date = chat_date;
		this.board_id = board_id;
	}
	
	
	
	public int getChat_id() {
		return chat_id;
	}
	public void setChat_id(int chat_id) {
		this.chat_id = chat_id;
	}
	public String getChat_to_id() {
		return chat_to_id;
	}
	public void setChat_to_id(String chat_to_id) {
		this.chat_to_id = chat_to_id;
	}
	public String getChat_from_id() {
		return chat_from_id;
	}
	public void setChat_from_id(String chat_from_id) {
		this.chat_from_id = chat_from_id;
	}
	public String getChat_content() {
		return chat_content;
	}
	public void setChat_content(String chat_content) {
		this.chat_content = chat_content;
	}
	public Timestamp getChat_date() {
		return chat_date;
	}
	public void setChat_date(Timestamp chat_date) {
		this.chat_date = chat_date;
	}
	
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}


	
	

}
