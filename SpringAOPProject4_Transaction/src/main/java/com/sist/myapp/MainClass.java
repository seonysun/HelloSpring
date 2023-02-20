package com.sist.myapp;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.*;

@Component
public class MainClass {
	@Autowired
	private StudentDAO dao;
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		MainClass mc=(MainClass)app.getBean("mainClass");
		List<StudentVO> list=mc.dao.studentListData();
		for(StudentVO vo:list) {
			System.out.println(vo.getHakbun()+"."+vo.getName()+":"
					+vo.getKor()+" "+vo.getEng()+" "+vo.getMath());
		}
		
		StudentVO vo1=new StudentVO();
		vo1.setHakbun(5);
		vo1.setName("강감찬");
		vo1.setKor(80);
		vo1.setEng(82);
		vo1.setMath(74);
		StudentVO vo2=new StudentVO();
		vo2.setHakbun(6);
		vo2.setName("을지문덕");
		vo2.setKor(93);
		vo2.setEng(92);
		vo2.setMath(84);
		mc.dao.studentInsert(vo1, vo2);
	}
}
