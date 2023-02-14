package com.sist.myapp;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;

public class EmpMainClass {
	private EmpDAO dao;
	public void setDao(EmpDAO dao) {
		this.dao = dao;
	}
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app-emp.xml");
		EmpMainClass mc=(EmpMainClass)app.getBean("mc");
		List<EmpVO> list=mc.dao.empListData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "+vo.getEname()+" "+vo.getJob());
		}
	}
}
