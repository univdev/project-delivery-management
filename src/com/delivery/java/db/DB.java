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
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			System.out.println("DB CONNECTED.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 쿼리만 날리는 함수
	public int mq(String q) {
		try {
			return state.executeUpdate(q);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	// 쿼리를 날리고 데이터를 받아오는 함수
	public ResultSet mfs(String q) {
		try {
			return state.executeQuery(q);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	// 쿼리를 보내고, 해당 쿼리로 인해서 받아온 데이터의 갯수를 리턴하는 함수
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
