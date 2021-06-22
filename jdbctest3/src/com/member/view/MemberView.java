package com.member.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.member.controller.MemberController;
import com.member.model.vo.Member;

public class MemberView {
	//애플리케이션을 사용하는 사용자에게 알맞은 화면을 출력해주는 역할
	//화면별 출력하는 기능(매소드)
	private MemberController mc=new MemberController();
	
	public void mainMenu() {
		//메뉴를 출력하고 메뉴를 선택하면 서비스가 구현될 수 있도록 하는 기능
		while(true) {
			System.out.println("===== 회원관리 v.2 =====");
			System.out.println("1. 전체회원조회");
			System.out.println("2. 이름으로 조회");
			System.out.println("3. 아이디로 조회");
			System.out.println("4. 회원등록");
			System.out.println("5. 회원수정");
			System.out.println("6. 회원삭제");
			System.out.println("7. 원하는 조건으로 검색하기");
			System.out.println("0. 프로그램종료");
			Scanner sc=new Scanner(System.in);
			System.out.print("선택 : ");
			int cho=sc.nextInt();
			switch(cho) {
				case 1 : mc.selectAll();break;
				case 2 : mc.searchName();break;
				case 3 : break;
				case 4 : break;
				case 5 : break;
				case 6 : break;
				case 7 : mc.choiceSearch();break;
				case 0 : System.out.println("프로그램을 종료합니다.");return;
			}
		}
		
	}
	
	public void printData(List<Member> list) {
		System.out.println("====== 조회결과 ======");
		for(Member m : list) {
			System.out.println(m);
		}
	}
	
	//사용자가 입력하는 화면출력기능
	public String inputData() {
		Scanner sc=new Scanner(System.in);
		System.out.println("===== 검색할 이름 입력 =====");
		System.out.println("입력 : ");
		return sc.nextLine();
	}
	
	public Map<String,String> colvalInput(){
		//검색을 원하는 컬럼, 값을 입력받기
		Map<String,String> param=new HashMap();
		Scanner sc=new Scanner(System.in);
		System.out.println("==== 검색을 원하는 컬럼명, 값을 입력 ====");
		System.out.print("컬럼명 : ");
		String col=sc.nextLine();
		System.out.print("값 : ");
		String val=sc.nextLine();
		param.put(col, val);
		return param;
		
	}
}












