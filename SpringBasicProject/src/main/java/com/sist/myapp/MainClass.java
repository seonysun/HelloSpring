package com.sist.myapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/*
 * XML, Container 호출
 * 	1) 스프링에 등록할 클래스 제작 : Model, DAO, Manager
 * 	2) 사용자 정의 데이터형은 등록 제외 : VO, DTO, Bean
 * 	3) XML 이용해서 등록
 * 	4) 스프링 라이브러리에서 ApplicationContext에 등록 처리
 * 	5) 등록된 클래스 ApplicationContext에서 얻어와서 구현, 조립
 * 	6) 등록된 클래스 소멸
 * */
public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
			//스프링 라이브러리 -> 경로 src/main/java까지 인식, 다른 패키지 안에 생성할 경우 경로 추가로 줘야 함
		AModel a=(AModel)app.getBean("a");
		a.display();
		AModel a1=(AModel)app.getBean("a");
		a1.display();
	}
}
