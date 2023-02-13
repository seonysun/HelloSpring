package com.sist.di4;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app4.xml");
		
		//DAO로 객체 생성 시 XML 이용해야 함 -> EmpDAO dao=new EmpDAO("oracle.jdbc.driver.OracleDriver");로 직접 생성하면 null point exception
		EmpDAO dao=(EmpDAO)app.getBean("dao");
					//getBean의 리턴형이 Object이므로 형변환 필요
		List<EmpVO> list=dao.empListData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "+vo.getEname()+" "+vo.getJob()+" "+vo.getHiredate()+" "+vo.getSal());
		}
	}
}
