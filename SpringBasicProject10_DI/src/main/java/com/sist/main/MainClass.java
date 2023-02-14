package com.sist.main;
import com.sist.dao.*;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	private StudentDAO dao;
	public void setDao(StudentDAO dao) {
		this.dao = dao;
	}
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext(new String[]{"application-context.xml","application-datasource.xml"});
		MainClass mc=(MainClass)app.getBean("mc");
		//데이터 삽입
//		StudentVO vo=new StudentVO();
//		vo.setName("심청이");
//		vo.setKor(92);
//		vo.setEng(80);
//		vo.setMath(82);
//		mc.dao.studentInsert(vo);
//		System.out.println("저장완료");
		
		//데이터 목록 출력
		List<StudentVO> list=mc.dao.studentListData();
		for(StudentVO vo:list) {
			System.out.println(vo.getHakbun()+" "
					+vo.getName()+" "
					+vo.getKor()+" "
					+vo.getEng()+" "
					+vo.getMath()+" "
					+vo.getTotal()+" "
					+vo.getAvg());
		}
		
		//데이터 상세 출력
		StudentVO vo=mc.dao.studentDetailData(1);
		System.out.println(vo.getHakbun()+" "
				+vo.getName()+" "
				+vo.getKor()+" "
				+vo.getEng()+" "
				+vo.getMath()+" "
				+vo.getTotal()+" "
				+vo.getAvg());
		
	}
}
