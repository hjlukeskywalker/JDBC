package com.member.model.service;

import java.sql.Connection;
import java.util.List;

import com.common.JDBCTemplate;
import com.member.model.dao.MemberDao;
import com.member.model.vo.Member;

public class MemberService {
	private MemberDao dao=new MemberDao();
	//Connection 객체를 관리하는 기능, connection객체 반환, 트렌젝션처리를 함.
	
	public List<Member> selectAll(){
		//1. Connection객체 가져오기
		Connection conn=JDBCTemplate.getConnection();
		//가져온 connection을 dao에 전달.
		List<Member> list=dao.selectAll(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	
	public int insertMember(Member m) {
		Connection conn=JDBCTemplate.getConnection();
		int result=dao.insertMember(conn,m);
		
		//트렌젝션처리 commit() / rollback();
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	
	
}








