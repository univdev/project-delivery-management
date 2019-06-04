package com.delivery.java.db.schema;

public class AccountSchema {
	private int idx_a = 0;
	private int grade = 0;
	private String account = null;
	private String password = null;
	private String company = null;
	private String address = null;
	private String phone = null;
	private int point = 0;
	private int created_at = 0;
	private int updated_at = 0;
	public int getIdx_a() {
		return idx_a;
	}
	public void setIdx_a(int idx_a) {
		this.idx_a = idx_a;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getCreated_at() {
		return created_at;
	}
	public void setCreated_at(int created_at) {
		this.created_at = created_at;
	}
	public int getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(int updated_at) {
		this.updated_at = updated_at;
	}
	public AccountSchema(int idx_a, int grade, String account, String password, String company, String address,
			String phone, int point, int created_at, int updated_at) {
		super();
		this.idx_a = idx_a;
		this.grade = grade;
		this.account = account;
		this.password = password;
		this.company = company;
		this.address = address;
		this.phone = phone;
		this.point = point;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public Object[] getObject() {
		Object[] result = { this.idx_a, this.grade, this.account, this.password, this.company, this.address, this.phone, this.point, this.created_at, this.updated_at};
		return result;
	}
}
