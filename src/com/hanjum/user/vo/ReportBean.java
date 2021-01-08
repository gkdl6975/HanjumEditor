package com.hanjum.user.vo;

public class ReportBean {
	private int report_id;
	private String user_id;
	private int report_type;
	private String report_userId;
	private String report_content;
	public String getUser_id() {
		return user_id;
	}
	public int getReport_id() {
		return report_id;
	}
	public void setReport_id(int report_id) {
		this.report_id = report_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getReport_type() {
		return report_type;
	}
	public void setReport_type(int report_type) {
		this.report_type = report_type;
	}
	public String getReport_userId() {
		return report_userId;
	}
	public void setReport_userId(String report_userId) {
		this.report_userId = report_userId;
	}
	public String getReport_content() {
		return report_content;
	}
	public void setReport_content(String report_content) {
		this.report_content = report_content;
	}
}
