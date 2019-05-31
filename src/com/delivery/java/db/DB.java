package com.delivery.java.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	
	private Connection conn = null;
	private Statement state = null;
	
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private String user = "hr";
	private String password = "hr";

	public DB() {
		try {
			conn = DriverManager.getConnection(url, user, password);
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			System.out.println("DB CONNECTED.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int mq(String q) {
		try {
			return state.executeUpdate(q);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public ResultSet mfs(String q) {
		try {
			return state.executeQuery(q);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int mn(String q) {
		ResultSet rs = this.mfs(q);
		try {
			rs.last();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return rs.getRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
