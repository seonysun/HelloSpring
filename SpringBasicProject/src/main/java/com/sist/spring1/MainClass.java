package com.sist.spring1;

public class MainClass {
	public static void main(String[] args) {
		Hello hello=new Hello();
					//new로 생성 -> 결합성이 높은 프로그램(Hello에 의존) -> 비추
		String msg=hello.sayHello("홍길동");
		System.out.println(msg);
	}
}
