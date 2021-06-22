package com.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	//DAO에서 공통적으로 사용하는
	//Connection생성, Statement 반환, ResultSet반환, connection 반환, 트렌젝션처리 메소드
	//공통으로 관리하는 클래스
	//각 메소드는 모두 애플리케이션 안에서 공통으로 사용하기 때문에
	//static으로 관리를 한다. -> 매소드는 모두 static으로 선언
	
	//Connection생성 매소드
	public static Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
					"student","student");
			//트렌젝션 오토커밋방지
			conn.setAutoCommit(false);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//객체 반환메소드
	public static void close(Connection conn) {
		try {
			if(conn!=null  && !conn.isClosed()) {
					conn.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();			
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt!=null && !stmt.isClosed()) stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs!=null && !rs.isClosed()) rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//트렌젝션처리하기
	public static void commit(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.commit();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void rollback(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.rollback();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
