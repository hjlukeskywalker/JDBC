package com.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.member.model.vo.Member;

//DB접속정보를 가지고 DB에 접속해서 SQL구문을 실행하는 기능을 하는 클래스
//SQL실행결과를 호출한 쪽으로 반환해줌.
public class MemberDao {

	public List<Member> selectAll() {
		//DB연결해서 Resultset으로 데이터 받아오세요!
		//1. 객체선언하기
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		//반환형에 대한 선언
		List<Member> result=new ArrayList();
		
		try{
			//드라이브 등록하기
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe"
					,"student","student");
			
			//2. connection sql실행객체 가져오기 ->
			//stmt=new Statement();//interface
			stmt=conn.createStatement();
			//3. 실행할 sql문 작성하기
			String sql="SELECT * FROM MEMBER";
			//4. SQL문 실행하기 / 결과받기
			rs=stmt.executeQuery(sql);
			//stmt.executeQuery("SELECT * FROM MEMBER");
			//조회한 데이터 출력하기
			//객체의 값을 설정할때는 while밖에 설정하면 안됨. -> 데이터의 마지막 row값만 출력하게 됨.
			//Member m=new Member();
			
			while(rs.next()) {
				Member m=new Member();
//				System.out.println(rs.getString("member_id")+" "+rs.getString("member_pwd"));
				//가져온 데이터 result객체에 넣기 -> 오늘 숙제
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("gender"));
				m.setAge(rs.getInt("age"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrollDate(rs.getDate("enroll_date"));
				//컬렉션에 받아온
				result.add(m);
			}
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null && !rs.isClosed()) rs.close();
				if(stmt!=null && !stmt.isClosed()) stmt.close();
				if(conn!=null && !conn.isClosed()) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;		
		
	}
	
	public Member searchId(String memberId) {
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		Member m=null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			stmt=conn.createStatement();
			//String sql="SELECT * FROM MEMBER WHERE MEMBER_ID='memberId'";
			String sql="SELECT * FROM MEMBER WHERE MEMBER_ID='"+memberId+"'";
			//SELECT * FROM MEMBER WHERE MEMBER_ID='ADMIN';
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				//찾은것에 대한 데이터 저장
				m=new Member();
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("gender"));
				m.setAge(rs.getInt("age"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrollDate(rs.getDate("enroll_date"));
			}			
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null&&!rs.isClosed()) rs.close();
				if(stmt!=null&&!stmt.isClosed()) stmt.close();
				if(conn!=null&&!conn.isClosed()) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return m;// 회원정보있는것 / null
		
		
	}
	
	public List<Member> searchName(String name){
		//DB에 매개변수로 받은 값이랑 부분일치하는
		//값 조회
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		List<Member> result=new ArrayList();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			stmt=conn.createStatement();
			String sql="SELECT * FROM MEMBER WHERE MEMBER_NAME LIKE '%"+name+"%'";
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				Member m=new Member();
				m.setMemberId(rs.getString(1));
				m.setMemberPwd(rs.getString(2));
				m.setMemberName(rs.getString(3));
				m.setGender(rs.getString(4));
				m.setAge(rs.getInt(5));
				m.setEmail(rs.getString(6));
				m.setPhone(rs.getString(7));
				m.setAddress(rs.getString(8));
				m.setHobby(rs.getString(9));
				m.setEnrollDate(rs.getDate(10));
				result.add(m);
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null&&!rs.isClosed()) rs.close();
				if(stmt!=null&&!stmt.isClosed()) stmt.close();
				if(conn!=null&&!conn.isClosed()) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	public int insertMember(Member m) {
		Connection conn=null;
		Statement stmt=null;
		int result=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
											"student","student");
			conn.setAutoCommit(false);//트렌젝션처리(commit, rollback) 개발자가 설정할 수 있게함.
			stmt=conn.createStatement();
			String sql="INSERT INTO MEMBER VALUES('"+m.getMemberId()+"','"
													+m.getMemberPwd()+"','"
													+m.getMemberName()+"','"
													+m.getGender()+"',"
													+m.getAge()+",'"
													+m.getEmail()+"','"
													+m.getPhone()+"','"
													+m.getAddress()+"','"
													+m.getHobby()+"',"
													+"SYSDATE)";
			result=stmt.executeUpdate(sql);
			//insert -> 트렌젝션처리해야함.
			//Connection 객체에 트렌젝션을 처리해주는 메소드가 있음.
			//commit() / rollback() 메소드!
			if(result>0) conn.commit();
			else conn.rollback();
					
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt!=null&&!stmt.isClosed()) stmt.close();
				if(conn!=null&&!conn.isClosed()) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	
	
	
}
