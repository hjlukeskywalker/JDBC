package com.member.controller;

import java.util.List;

import com.member.model.service.MemberService;
import com.member.model.vo.Member;
import com.member.view.MemberView;

public class MemberController {

	private MemberService service=new MemberService();
	private MemberView view;
	
	public void mainMenu() {
		view=new MemberView();
		view.mainMenu(this);
	}
	
	public void selectAll() {
		
		List<Member> list=service.selectAll();
		
		view.printData(list);
	}
	
	public void insertMember() {
		Member m=view.inputMember();
		int result=service.insertMember(m);
		
	}
}







