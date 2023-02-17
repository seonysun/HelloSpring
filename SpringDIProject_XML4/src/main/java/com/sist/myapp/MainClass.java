package com.sist.myapp;
import com.sist.sawon.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		SawonSystem ss=(SawonSystem)app.getBean("ss");
        ss.print();
	}
}
