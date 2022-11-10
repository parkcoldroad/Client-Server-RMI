package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig {
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url ="jdbc:mysql://localhost:3306/LMSDB?serverTimezone=Asia/Seoul&useSSL=false";
	private static final String id = "root";
	private static final String pw = "asdasd123";
	
	private static Connection conn; // one connection connects all tables

	static {
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return conn;
	}
}
