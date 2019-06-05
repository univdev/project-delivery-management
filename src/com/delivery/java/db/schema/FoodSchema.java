package com.delivery.java.db.schema;

import java.sql.Timestamp;

public class FoodSchema {
	private int idx_f = 0;
	private int idx_s = 0;
	private String name = null;
	private int price = 0;
	private Timestamp created_at = null;
	private Timestamp updated_at = null;
	public int getIdx_f() {
		return idx_f;
	}
	public void setIdx_f(int idx_f) {
		this.idx_f = idx_f;
	}
	public int getIdx_s() {
		return idx_s;
	}
	public void setIdx_s(int idx_s) {
		this.idx_s = idx_s;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
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
	public FoodSchema(int idx_f, int idx_s, String name, int price, Timestamp created_at, Timestamp updated_at) {
		super();
		this.idx_f = idx_f;
		this.idx_s = idx_s;
		this.name = name;
		this.price = price;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	public String toString() {
		return String.format("%s - %d원", name, price);
	}
	
}
