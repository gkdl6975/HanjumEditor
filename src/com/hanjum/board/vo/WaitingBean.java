package com.hanjum.board.vo;

import java.sql.Timestamp;

public class WaitingBean {
	private int waiting_id;
	private String waiting_editor;
	private Timestamp waiting_date; 
	private String user_id;
	private int board_id;
	private int waiting_price;
	
	public int getWaiting_price() {
		return waiting_price;
	}
	public void setWaiting_price(int waiting_price) {
		this.waiting_price = waiting_price;
	}
	public int getWaiting_id() {
		return waiting_id;
	}
	public void setWaiting_id(int waiting_id) {
		this.waiting_id = waiting_id;
	}
	public String getWaiting_editor() {
		return waiting_editor;
	}
	public void setWaiting_editor(String waiting_editor) {
		this.waiting_editor = waiting_editor;
	}
	public Timestamp getWaiting_date() {
		return waiting_date;
	}
	public void setWaiting_date(Timestamp waiting_date) {
		this.waiting_date = waiting_date;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	
}
