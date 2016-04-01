package com.estsoft.guestbook.vo;

public class GuestbookVO {
	private Long no;
	private String name;
	private String reg_date;
	private String message;
	private String password;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "GuestbookVO [no=" + no + ", name=" + name + ", reg_date="
				+ reg_date + ", message=" + message + ", password=" + password
				+ "]";
	}
	

	
}

