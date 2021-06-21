package com.member.controller;

import java.util.Arrays;
import java.util.List;

import com.member.model.dao.MemberDao;
import com.member.model.vo.Member;
import com.member.view.MemberView;

public class MemberController {
	private MemberView view;
	private MemberDao dao=new MemberDao();
	
	public void mainMenu() {
		//메인메뉴를 호출하는 기능
		view=new MemberView();
		view.mainMenu(this);
	}
	
	public void selectAll() {
		//DB student계정의 member테이블에 있는 전체 데이터 가져오는 서비스
		List<Member> result=dao.selectAll();
		//view에서 받은 데이터 출력하기 -> 오늘 숙제
		//System.out.println(result);
//		for(Member m : result) {
//			System.out.println(m);
//		}
//		result.get(0).setMemberName("박찬혁");
//		for(Member m : result) {
//			System.out.println(m);
//		}
		//view에서 데이터를 출력하는 매소드를 호출
		//console창에 전달받은 데이터를 출력해주고 마무의ㄹㅣ!
		System.out.println(view);
		view.printData(result);
		
	}
	
	public void searchId() {
		//1. 검색할 아이디를 입력받는다. -> view에 요청
		//2. 입력받은 값을 받으면 그 값이 db에 있는지 확인한다. -> dao
		//3. dao가 준 결과를 출력한다. -> view에 요청
		String memberId=view.inputData("아이디");
		
		Member m=dao.searchId(memberId);
		//Arrays.asList(new Member[] {m}) -> 일반 배열을 List 변경해주는 매소드
		view.printData(m);
		
		
	}
	
	
	public void searchName() {
		//1. 검색할 이름 입력받기 -> view
		
		String name=view.inputData("이름");
		//2. 받은 이름 db에서 조회하기 -> dao
		List<Member> result=dao.searchName(name);
		
		//3. db 조회결과 출력하기 -> view
		view.printData(result);
	}
	
	public void insertMember() {
		Member m=view.inputMember();
		int result=dao.insertMember(m);
		view.printMsg(result>0?"회원을 정상적으로 등록했습니다.":"회원등록을 실패했습니다.");
	}
	
}








