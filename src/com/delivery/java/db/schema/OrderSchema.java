package com.delivery.java.db.schema;

public class OrderSchema {
	// 주문 idx
	private int idx = 0;
	// 주문자(고객)의 idx
	private int uid = 0;
	// 업체의 idx
	private int company = 0;
	// 음식의 idx를 콤마로 구분한 문자
	// ex) 1,3,7
	private String foods = null;
	// 사장이 주문을 컨펌하면 등록되는 배달 소요시간
	// 최소 10분, 최대 60분, 간격은 10분
	private int duration = 0;
	// 주문을 등록할 때 고객이 별도의 코멘트를 이 곳에 작성 가능
	private String comment = null;
	// 주문을 등록할 때 결제 수단을 이 곳에 작성 가능
	// 카드, 현금, 포인트, 카카오페이
	// 카드: card
	// 현금: cash
	// 포인트: point
	// 카카오페이: kakao
	private String method = null;
	
	public OrderSchema(int idx, int uid, int company, String foods, int duration, String comment, String method) {
		super();
		this.idx = idx;
		this.uid = uid;
		this.company = company;
		this.foods = foods;
		this.duration = duration;
		this.comment = comment;
		this.method = method;
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
	public int getCompany() {
		return company;
	}
	public void setCompany(int company) {
		this.company = company;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	
}
