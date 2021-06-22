package com.member.controller;

import java.util.List;
import java.util.Map;

import com.member.model.service.MemberService;
import com.member.model.vo.Member;
import com.member.view.MemberView;

//member서비스를 관리하는 객체
//client에게 특정 서비스 기능을 요청받아처리
//  필요한 화면(view) / DB데이터를 가져오는것(model) 
public class MemberController {

	private MemberService service=new MemberService();	
	
	public void mainMenu() {
		//메인화면 보여달라는 요청
		new MemberView().mainMenu();
	}
	
	public void selectAll() {
		//DB의 student계정의 member테이블 전체데이터를 가져와 출력해주는 기능
		//DB에 접근해야할때 Service객체에 요청
		List<Member> list=service.selectAll();
		
		new MemberView().printData(list);
	}
	
	public void searchName() {
		//사용자가 검색할 이름을 입력하면 그 값이 DB에 있는지 확인하고
		//그결과를 출력해주는 서비스
		String name=new MemberView().inputData();
		List<Member> list=service.searchName(name);
		
		new MemberView().printData(list);
		
	}
	
	public void choiceSearch() {
		
		Map<String,String> param=new MemberView().colvalInput();
		List<Member> list=service.choiceSearch(param);
		
		new MemberView().printData(list);
	}
	
	
}








