package com.hanjum.contract.vo;

import java.sql.Timestamp;
import java.util.Date;

public class ContractBean {
	
	private int contract_id;
	private String board_subject;
	private String contract_creator;
	private String contract_editor;
	private Timestamp contract_begin_date;
	private Timestamp contract_end_date;
	private int contract_price;
	private int contract_status;
	private int board_id;
	private String board_ed_subject;
	private String board_ed_address;
	private String contract_address;
	private int wating;
	

	

	


	public int getWating() {
		return wating;
	}







	public void setWating(int wating) {
		this.wating = wating;
	}







	public ContractBean() {}
	
	





	public ContractBean(int contract_id, String board_subject, String contract_creator, String contract_editor,
			Timestamp contract_begin_date, Timestamp contract_end_date, int contract_price, int contract_status,
			int board_id, String board_ed_subject, String board_ed_address, String contract_address) {
		super();
		this.contract_id = contract_id;
		this.board_subject = board_subject;
		this.contract_creator = contract_creator;
		this.contract_editor = contract_editor;
		this.contract_begin_date = contract_begin_date;
		this.contract_end_date = contract_end_date;
		this.contract_price = contract_price;
		this.contract_status = contract_status;
		this.board_id = board_id;
		this.board_ed_subject = board_ed_subject;
		this.board_ed_address = board_ed_address;
		this.contract_address = contract_address;
	}







	public String getContract_address() {
		return contract_address;
	}

	public void setContract_address(String contract_address) {
		this.contract_address = contract_address;
	}

	public String getBoard_ed_subject() {
		return board_ed_subject;
	}
	public void setBoard_ed_subject(String board_ed_subject) {
		this.board_ed_subject = board_ed_subject;
	}
	public String getBoard_subject() {
		return board_subject;
	}
	
	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}
	public String getBoard_ed_address() {
		return board_ed_address;
	}
	public void setBoard_ed_address(String board_ed_address) {
		this.board_ed_address = board_ed_address;
	}
	public int getContract_id() {
		return contract_id;
	}
	public void setContract_id(int contract_id) {
		this.contract_id = contract_id;
	}
	public String getContract_creator() {
		return contract_creator;
	}
	public void setContract_creator(String contract_creator) {
		this.contract_creator = contract_creator;
	}
	public String getContract_editor() {
		return contract_editor;
	}
	public void setContract_editor(String contract_editor) {
		this.contract_editor = contract_editor;
	}
	public Timestamp getContract_begin_date() {
		return contract_begin_date;
	}
	public void setContract_begin_date(Timestamp contract_begin_date) {
		this.contract_begin_date = contract_begin_date;
	}
	public Timestamp getContract_end_date() {
		return contract_end_date;
	}
	public void setContract_end_date(Timestamp contract_end_date) {
		this.contract_end_date = contract_end_date;
	}
	public int getContract_price() {
		return contract_price;
	}
	public void setContract_price(int contract_price) {
		this.contract_price = contract_price;
	}
	public int getContract_status() {
		return contract_status;
	}
	public void setContract_status(int contract_status) {
		this.contract_status = contract_status;
	}
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	
	


}
