package com.sist.di4;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app4.xml");
		
		EmpDAO dao=(EmpDAO)app.getBean("dao");
			//DAO 객체 생성 시 XML 이용 필수 -> EmpDAO dao=new EmpDAO("oracle.jdbc.driver.OracleDriver");로 직접 생성하면 null point exception
		List<EmpVO> list=dao.empListData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "+vo.getEname()+" "+vo.getJob()+" "+vo.getHiredate()+" "+vo.getSal());
		}
	}
}
