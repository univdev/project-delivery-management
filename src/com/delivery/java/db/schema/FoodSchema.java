package com.delivery.java.db.schema;

public class FoodSchema {
	// 상품의 idx
	private int idx = 0;
	// 상품이 등록된 업소의 idx
	private int uid = 0;
	// 상품 이름
	private String name = null;
	// 상품 가격
	private int price = 0;
	
	public FoodSchema(int idx, int uid, String name, int price) {
		super();
		this.idx = idx;
		this.uid = uid;
		this.name = name;
		this.price = price;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Object[] getObject() {
		Object[] result = { this.idx, this.uid, this.name, this.price };
		return result;
	}
}
