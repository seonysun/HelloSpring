package com.sist.di3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app3.xml");
		Member m1=(Member)app.getBean("m1");
		m1.print();
		System.out.println();
		Member m2=(Member)app.getBean("m2");
		m2.print();
		System.out.println();
		Member m3=(Member)app.getBean("m3");
		m3.print();
	}
}
