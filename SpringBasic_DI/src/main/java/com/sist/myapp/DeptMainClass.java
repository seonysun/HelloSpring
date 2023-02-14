package com.sist.myapp;
import java.util.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;

public class DeptMainClass {
	private DeptDAO dao;
	public void setDao(DeptDAO dao) {
		this.dao = dao;
	}
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app-dept.xml");
		DeptMainClass dmc=(DeptMainClass)app.getBean("dmc");
		List<DeptVO> list=dmc.dao.deptListData();
		for(DeptVO vo:list) {
			System.out.println(vo.getDeptno()+" "+vo.getDname()+" "+vo.getLoc());
		}
	}
}
