package com.delivery.java.db.schema;

import java.sql.Timestamp;

public class StoreSchema {

	private int idx_s = 0;
	private int idx_a = 0;
	private String name = null;
	private Timestamp created_at = null;
	private Timestamp updated_at = null;
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
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Timestamp getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Timestamp updated_at) {
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
	public StoreSchema(int idx_s, int idx_a, String name, Timestamp created_at, Timestamp updated_at, String methods) {
		super();
		this.idx_s = idx_s;
		this.idx_a = idx_a;
		this.name = name;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.methods = methods;
	}
}
