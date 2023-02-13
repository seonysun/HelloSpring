package com.sist.main;
import java.util.*;
import com.sist.manager.*;
import com.sist.dao.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		//import로 xml 파일 한 곳에서 합쳐서 삽입
		ApplicationContext app=new ClassPathXmlApplicationContext("application-context.xml");

		Member mem=(Member)app.getBean("mem", Member.class);
		System.out.println("회원번호:"+mem.getMno());
		System.out.println("이름:"+mem.getName());
		System.out.println("주소:"+mem.getAddress());
		System.out.println("전화:"+mem.getTel());
		System.out.println("나이:"+mem.getAge());
		
		EmpDAO dao=(EmpDAO)app.getBean("dao");
		List<EmpVO> list=dao.empListData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getJob()+" "
					+vo.getHiredate().toString()+" "
					+vo.getSal());
		}
	}
}
