package com.sist.main;
import java.util.*;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sist.config.*;
import com.sist.dao.*;

public class MainClass2 {
	private StudentDAO dao;
	public void setDao(StudentDAO dao) {
		this.dao = dao;
	}
	public static void main(String[] args) {
		AnnotationConfigApplicationContext app=new AnnotationConfigApplicationContext(StudentConfig.class);
		MainClass2 mc=(MainClass2)app.getBean("mc");
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
	}
}
