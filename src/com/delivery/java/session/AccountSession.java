package com.delivery.java.session;

import java.sql.Time;
import java.sql.Timestamp;

public class AccountSession {
	private static int idx_a = 0;
	private static int grade = 0;
	private static String account = null;
	private static String password = null;
	private static String company = null;
	private static String address = null;
	private static String phone = null;
	private static int point = 0;
	private static Timestamp created_at = null;
	private static Timestamp updated_at = null;
	public static int getIdx_a() {
		return idx_a;
	}
	public static void setIdx_a(int idx_a) {
		AccountSession.idx_a = idx_a;
	}
	public static int getGrade() {
		return grade;
	}
	public static void setGrade(int grade) {
		AccountSession.grade = grade;
	}
	public static String getAccount() {
		return account;
	}
	public static void setAccount(String account) {
		AccountSession.account = account;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		AccountSession.password = password;
	}
	public static String getCompany() {
		return company;
	}
	public static void setCompany(String company) {
		AccountSession.company = company;
	}
	public static String getAddress() {
		return address;
	}
	public static void setAddress(String address) {
		AccountSession.address = address;
	}
	public static String getPhone() {
		return phone;
	}
	public static void setPhone(String phone) {
		AccountSession.phone = phone;
	}
	public static int getPoint() {
		return point;
	}
	public static void setPoint(int point) {
		AccountSession.point = point;
	}
	public static Timestamp getCreated_at() {
		return created_at;
	}
	public static void setCreated_at(Timestamp timestamp) {
		AccountSession.created_at = timestamp;
	}
	public static Timestamp getUpdated_at() {
		return updated_at;
	}
	public static void setUpdated_at(Timestamp updated_at) {
		AccountSession.updated_at = updated_at;
	}
	
	
}
