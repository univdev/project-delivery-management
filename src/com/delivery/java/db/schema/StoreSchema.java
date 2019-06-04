package com.delivery.java.db.schema;

public class StoreSchema {

	private int idx_s = 0;
	private int idx_a = 0;
	private String name = null;
	private int created_at = 0;
	private int updated_at = 0;
	private String methods = null;
	public int getIdx_s() {
		return idx_s;
	}
	public void setIdx_s(int idx_s) {
		this.idx_s = idx_s;
	}
	public int getIdx_a() {
		return idx_a;
	}
	public void setIdx_a(int idx_a) {
		this.idx_a = idx_a;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getMethods() {
		return methods;
	}
	public void setMethods(String methods) {
		this.methods = methods;
	}
	
	public Object[] getObject() {
		Object[] result = { this.idx_s, this.idx_a, this.name, this.methods, this.created_at, this.updated_at };
		return result;
	}
}
