package com.member.model.service;

import static com.member.common.JDBCTemplate.close;
import static com.member.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.member.model.dao.MemberDao;
import com.member.model.vo.Member;


public class MemberService {
//DB연결을 위해 필요한 Connection객체를 생성하고, Connection을 관리, 트렌젝션처리(commit,rollback)
	
	private MemberDao dao=new MemberDao();
	
	public List<Member> selectAll(){
		//connection 객체 가져오기
		Connection conn=getConnection();
		List<Member> list=dao.selectAll(conn);
		
		close(conn);
		
		return list;
		
	}
	
	
	public List<Member> searchName(String name){
		Connection conn=getConnection();
		List<Member> list=dao.searchName(conn, name);
		close(conn);
		return list;
		
	}
	
	public List<Member> choiceSearch(Map<String,String> param){
		Connection conn=getConnection();
		List<Member> list=dao.choiceSearch(conn,param);
		close(conn);
		return list;
	}
	
}








