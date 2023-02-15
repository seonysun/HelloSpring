package com.sist.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.DeptDAO;
import com.sist.dao.EmpDAO;

@Component("mc")
public class MainClass {
	@Autowired
	private EmpDAO edao;
	@Autowired
	private DeptDAO ddao;
	ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
	MainClass mc=(MainClass)app.getBean("mc");
}
