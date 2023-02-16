package com.sist.myapp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;

public class MainClass {
	public static void main(String[] args) {
//		MyDAO dao=new MyDAO("oracle.jdbc.driver.OracleDriver");
//		dao.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
//		dao.setUser("hr");
//		dao.setPassword("happy");
		
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		MyDAO dao=app.getBean("dao",MyDAO.class);
		
		dao.getConnection();
	}
}
