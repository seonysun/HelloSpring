package com.sist.myapp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;

import lombok.Setter;

public class MainClass {
	@Setter
	private MovieDAO dao;
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		MovieDAO dao=(MovieDAO)app.getBean("dao");
		dao.select();
		dao.insert();
		dao.update();
		dao.delete();
	}
}
