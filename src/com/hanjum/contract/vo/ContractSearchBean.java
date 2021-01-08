package com.hanjum.contract.vo;

public class ContractSearchBean {

	 
	private String[] region;
	private String Search_begin_date;
	private String Search_end_date;
	private String date_check;
	private String[] contract_status;
	private int contract_pay1;
	private int contract_pay2;
	private String searchtype;
	private String search_word;

	public ContractSearchBean() {}


	
	public ContractSearchBean(String[] region, String search_begin_date, String search_end_date, String date_check,
			String[] contract_status, int contract_pay1, int contract_pay2, String searchtype, String search_word) {
		super();
		this.region = region;
		Search_begin_date = search_begin_date;
		Search_end_date = search_end_date;
		this.date_check = date_check;
		this.contract_status = contract_status;
		this.contract_pay1 = contract_pay1;
		this.contract_pay2 = contract_pay2;
		this.searchtype = searchtype;
		this.search_word = search_word;
	}



	public String[] getRegion() {
		return region;
	}
	public void setRegion(String[] region) {
		this.region = region;
	}
	public String getSearch_begin_date() {
		return Search_begin_date;
	}
	public void setSearch_begin_date(String search_begin_date) {
		Search_begin_date = search_begin_date;
	}
	public String getSearch_end_date() {
		return Search_end_date;
	}
	public void setSearch_end_date(String search_end_date) {
		Search_end_date = search_end_date;
	}
	public String getDate_check() {
		return date_check;
	}
	public void setDate_check(String date_check) {
		this.date_check = date_check;
	}
	public String[] getContract_status() {
		return contract_status;
	}

	public void setContract_status(String[] contract_status) {
		this.contract_status = contract_status;
	}

	public int getContract_pay1() {
		return contract_pay1;
	}


	public void setContract_pay1(int contract_pay1) {
		this.contract_pay1 = contract_pay1;
	}


	public int getContract_pay2() {
		return contract_pay2;
	}


	public void setContract_pay2(int contract_pay2) {
		this.contract_pay2 = contract_pay2;
	}
	

	public String getSearchtype() {
		return searchtype;
	}

	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}
	public String getSearch_word() {
		return search_word;
	}

	public void setSearch_word(String search_word) {
		this.search_word = search_word;
	}

}
