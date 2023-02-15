package com.sist.myapp;
import java.util.*;

import com.sist.config.SeoulConfig;
import com.sist.dao.*;
import com.sist.vo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MainClass {
	@Autowired
	private SeoulDAO dao;
	public static void main(String[] args) {
//		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		AnnotationConfigApplicationContext app=new AnnotationConfigApplicationContext(SeoulConfig.class);
		
		System.out.println("==========메뉴==========");
		System.out.println("1.명소");
		System.out.println("2.자연&관광");
		System.out.println("3.쇼핑");
		System.out.println("4.호텔");
		System.out.println("5.게스트하우스");
		String[] table= {"","location","nature","shop","hotel","guest"};
		Scanner scan=new Scanner(System.in);
		System.out.print("> 메뉴 선택: ");
		int menu=scan.nextInt();
		
		Map map=new HashMap();
		map.put("seoul_tbl", "seoul_"+table[menu]);

		MainClass mc=(MainClass)app.getBean("mainClass");
		List<SeoulVO> list=mc.dao.seoulListData(map);
		for(SeoulVO vo:list) {
			System.out.println(vo.getNo()+"."+vo.getTitle()+"("+vo.getAddress()+")");
		}
	}
}
