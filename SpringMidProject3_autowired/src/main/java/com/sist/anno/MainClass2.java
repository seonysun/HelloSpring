package com.sist.anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component("main")
public class MainClass2 {
	@Autowired
	@Qualifier("mysql") //MyDAO 상속받은 클래스가 2개 -> 특정 객체 지정
		//@Autowired + @Qualifier("mysql") = @Resource(name="mysql") : JDK 1.8 이하에서만 가능
	private MyDAO dao;
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		MainClass2 mc2=(MainClass2)app.getBean("main");
		mc2.dao.connect();
	}
}
