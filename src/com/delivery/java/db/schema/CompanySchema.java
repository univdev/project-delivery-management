package com.delivery.java.db.schema;

public class CompanySchema {

	private int idx = 0;
	private int uid = 0;
	private String name = null;
	
	public CompanySchema(int idx, int uid, String name) {
		super();
		this.idx = idx;
		this.uid = uid;
		this.name = name;
	}
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
