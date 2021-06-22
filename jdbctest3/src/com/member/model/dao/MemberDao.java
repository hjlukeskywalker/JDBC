package com.member.model.dao;

import static com.member.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.member.model.vo.Member;

public class MemberDao {
	
	private Properties prop=new Properties();
	
	public MemberDao() {
		try {
			prop.load(new FileReader("resources/sql/member_sql.properties"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//전달받은 DB정보를 활용해서 DB에 접속한 후 해당하는 서비스의 데이터를 조회, 조작하는 것
	public List<Member> selectAll(Connection conn){
		//sql문을 실행하는 객체 -> Statement, PreparedStatement
		PreparedStatement pstmt=null;
		//DB에서 가져온 데이터를 보관할 객체
		ResultSet rs=null;
		List<Member> list=new ArrayList();
		//preparedstatement는 sql과 같이 생성해서 sql문을 매소드로 완성후 실행하는 객체임
		//String sql="SELECT * FROM MEMBER";
		//위치홀더가 없어도 사용이 가능 -> 값설정 없이 그냥 사용
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectAll"));
			//? 표기사 매개변로 들어온 쿼리문에 있으면 set000으로 값을 설정하고,
			//없으면 그냥 실행하면 됨.
			rs=pstmt.executeQuery();
			//DB에서 가져오는 row가 1개이상이면 while
			//1개 이하 if처리하면됨.
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
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public List<Member> searchName(Connection conn, String name){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		//String sql="SELECT * FROM MEMBER WHERE MEMBER_NAME LIKE ?";
		List<Member> list=new ArrayList();
		//String sql="SELECT * FROM MEMBER WHERE MEMBER_NAME LIKE '%'||?||'%'";
		//SELECT * FROM MEMBER WHERE MEMBER_NAME LIKE '%유%'
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectName"));
			pstmt.setString(1,"%"+name+"%");
			//pstmt.setString(1,name);
			rs=pstmt.executeQuery();
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
			close(rs);
			close(pstmt);
		}
		return list;
	}

	
	public List<Member> choiceSearch(Connection conn, Map<String,String> map){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> list=new ArrayList();
		Set<Map.Entry<String,String>> entry=map.entrySet();
		String col="";
		String val="";
		for(Map.Entry<String,String> e : entry) {
			col=e.getKey();
			val=e.getValue();
		}
		String sql=prop.getProperty("choiceSelect");
//		sql="SELECT * FROM MEMBER WHERE # LIKE ?";
		sql=sql.replace("#", col);
		try {
			pstmt=conn.prepareStatement(sql);
			//pstmt.setString(1, col);
			pstmt.setString(1, "%"+val+"%");
			rs=pstmt.executeQuery();
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
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	
	
	
	
	
}













