package com.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.common.JDBCTemplate;
import com.member.model.vo.Member;

public class MemberDao {

	public List<Member> selectAll(Connection conn){
		Statement stmt=null;
		ResultSet rs=null;
		List<Member> list=new ArrayList();
		try {
			String sql="SELECT * FROM MEMBER";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				Member m=new Member();
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
				list.add(m);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(stmt);
		}
		return list;
	}
	
	public int insertMember(Connection conn, Member m) {
		//Statement stmt=null;
		PreparedStatement pstmt=null;
		//위치홀더(?)표시를 이용해서 데이터의 자료형표시를 대신해줌.
		//특정메소드로(setString,setInt) 해당 위치홀더 값만 대입하면 됨.
		
		
		int result=0;
		
		try {
			//stmt=conn.createStatement();
			//PreparedStatement를 생성할때는 connection의 prepareStatement()매소드를 이용함.
			//prepareStatement()매개변수 sql구문을 넣어줌.
			String sql="INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?,?,?,SYSDATE)";
			//SELECT * FROM MEMBER WHERE MEMBER_NAME LIKE ?;
			pstmt=conn.prepareStatement(sql);
			//위치홀더로 선언된 곳에 값 대입하기
			pstmt.setString(1,m.getMemberId());
			pstmt.setString(2,m.getMemberPwd());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4,m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			//pstmt에서 쿼리문을 실행할때는 executeQuery() / executeUpdate();
			//매개변수가 없다.
			result=pstmt.executeUpdate();
			
			
//			String sql="INSERT INTO MEMBER VALUES('"+m.getMemberId()+"',"
//												+"'"+m.getMemberPwd()+"',"
//												+"'"+m.getMemberName()+"',"
//												+"'"+m.getGender()+"',"
//												+m.getAge()+","
//												+"'"+m.getEmail()+"',"
//												+"'"+m.getPhone()+"',"
//												+"'"+m.getAddress()+"',"
//												+"'"+m.getHobby()+"',"
//												+"SYSDATE)";
			//result=stmt.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}
}


















