package com.sist.main;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.config.*;
import com.sist.dao.*;

public class MainClass {
	private EmpDAO dao;
	public void setDao(EmpDAO dao) {
		this.dao = dao;
	}
	public static void main(String[] args) {
//		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		AnnotationConfigApplicationContext app=new AnnotationConfigApplicationContext(EmpConfig.class);
        MainClass mc=(MainClass)app.getBean("mc");
        List<EmpVO> list=mc.dao.empListData();
        for(EmpVO vo:list) {
        	System.out.println(vo.getEmpno()+" "+vo.getEname()+" "+vo.getJob());
        }
	}
}
