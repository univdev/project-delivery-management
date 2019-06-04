package com.delivery.java.session;

import java.sql.Timestamp;

public class StoreSession {

	private static int idx_s = 0;
	private static int idx_a = 0;
	private static String name = null;
	private static Timestamp created_at = null;
	private static Timestamp updated_at = null;
	private static String methods = null;
	
	public static int getIdx_s() {
		return idx_s;
	}



	public static void setIdx_s(int idx_s) {
		StoreSession.idx_s = idx_s;
	}



	public static int getIdx_a() {
		return idx_a;
	}



	public static void setIdx_a(int idx_a) {
		StoreSession.idx_a = idx_a;
	}



	public static String getName() {
		return name;
	}



	public static void setName(String name) {
		StoreSession.name = name;
	}



	public static Timestamp getCreated_at() {
		return created_at;
	}



	public static void setCreated_at(Timestamp created_at) {
		StoreSession.created_at = created_at;
	}



	public static Timestamp getUpdated_at() {
		return updated_at;
	}



	public static void setUpdated_at(Timestamp timestamp) {
		StoreSession.updated_at = timestamp;
	}



	public static String getMethods() {
		return methods;
	}



	public static void setMethods(String methods) {
		StoreSession.methods = methods;
	}



	public static Object[] getObject() {
		Object[] result = { idx_s, idx_a, name, methods, created_at, updated_at };
		return result;
	}
}
