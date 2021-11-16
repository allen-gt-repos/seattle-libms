package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DButil {

	// Set database login info	
	public static final String URL = "jdbc:mysql://localhost:3306/lib_db?useSSL=false";
	public static final String USER = "root";
	public static final String PASSWORD = "666";
	
	
	public void dbDemo(Connection conn) throws Exception {
		// query the database
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT Name, User_id FROM user WHERE Name='Percy Reyes'");
		// if query result is not void, rs return true
		while(rs.next()){
			System.out.println(rs.getString("Name")+" ID："+rs.getString("User_id"));
		}
	}
	
	public void closeCon(Connection conn)throws Exception{
		if(conn!=null)
		{
			conn.close();
		}
	}

	public Connection getCon()throws Exception{
		// Load database driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		// Connect database
		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		return conn;
	}
	
	
	public static void main(String[] args) throws Exception {
		
		DButil dbu = new DButil();
		
		try {
			Connection conn = dbu.getCon();
			System.out.println("Database Connected！");
			if (conn!=null) 
			{
				dbu.dbDemo(conn);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("Connection Failed");
		}
		
	}

}
