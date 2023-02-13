package com.sist.di3;

public class Member {
	private int mno;
	private String name,address,tel;
	public void print() {
		System.out.println("회원번호:"+mno);
		System.out.println("이름:"+name);
		System.out.println("주소:"+address);
		System.out.println("전화:"+tel);
	}
	//생성자로 멤버변수 초기화
	public Member(int mno, String name, String address, String tel) {
		super();
		this.mno = mno;
		this.name = name;
		this.address = address;
		this.tel = tel;
	}
}
