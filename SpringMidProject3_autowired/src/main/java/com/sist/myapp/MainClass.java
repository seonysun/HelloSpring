package com.sist.myapp;
import java.util.*;
import com.sist.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component("mc") //메모리 할당
public class MainClass {
	@Autowired
	private SeoulDAO dao;
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		MainClass mc=(MainClass)app.getBean("mc");
		List<SeoulVO> list=mc.dao.seoulListData();
		for(SeoulVO vo:list) {
			System.out.println(vo.getNo()+"."+vo.getTitle());
		}
	}
}
