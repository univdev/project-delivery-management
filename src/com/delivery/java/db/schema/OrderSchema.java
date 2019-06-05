package com.delivery.java.db.schema;

import java.sql.Timestamp;

public class OrderSchema {
	private int idx_o;
	private int idx_a;
	private int idx_s;
	private String storeName;
	private String foods;
	private int duration;
	private String comments;
	private String method;
	private Timestamp created_at;
	private Timestamp updated_at;
	public int getIdx_o() {
		return idx_o;
	}
	public void setIdx_o(int idx_o) {
		this.idx_o = idx_o;
	}
	public int getIdx_a() {
		return idx_a;
	}
	public void setIdx_a(int idx_a) {
		this.idx_a = idx_a;
	}
	public int getIdx_s() {
		return idx_s;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public void setIdx_s(int idx_s) {
		this.idx_s = idx_s;
	}
	public String getFoods() {
		return foods;
	}
	public void setFoods(String foods) {
		this.foods = foods;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
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
	public OrderSchema(int idx_o, int idx_a, int idx_s, String foods, int duration, String comments, String method,
			Timestamp created_at, Timestamp updated_at) {
		super();
		this.idx_o = idx_o;
		this.idx_a = idx_a;
		this.idx_s = idx_s;
		this.foods = foods;
		this.duration = duration;
		this.comments = comments;
		this.method = method;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	public OrderSchema(int idx_o, int idx_a, int idx_s, String storeName, String foods, int duration, String comments,
			String method, Timestamp created_at, Timestamp updated_at) {
		super();
		this.idx_o = idx_o;
		this.idx_a = idx_a;
		this.idx_s = idx_s;
		this.storeName = storeName;
		this.foods = foods;
		this.duration = duration;
		this.comments = comments;
		this.method = method;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
}
