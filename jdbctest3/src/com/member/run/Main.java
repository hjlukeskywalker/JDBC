package com.member.run;

import com.member.controller.MemberController;

public class Main {
	public static void main(String[] args) {
		
		new MemberController().mainMenu();
		//View객체에서 바로 호출하면 되잖아요!!!!
		//browser url창 주소 -> request(요청)-controller
	}
}
