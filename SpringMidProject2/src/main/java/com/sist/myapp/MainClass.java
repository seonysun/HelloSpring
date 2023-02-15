package com.sist.myapp;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;

import lombok.Setter;

public class MainClass {
	@Setter
	private SeoulDAO dao;
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		MainClass mc=(MainClass)app.getBean("mc");
		
		String[] table= {"","seoul_location","seoul_nature","seoul_shop"};
		Scanner scan=new Scanner(System.in);
		System.out.print("테이블 선택: ");
		int no=scan.nextInt();
		Map map=new HashMap();
		map.put("seoul_tbl", table[no]);
		List<SeoulVO> list=mc.dao.seoulListData(map);
		for(SeoulVO vo:list) {
			System.out.println(vo.getNo()+"."+vo.getTitle());
		}
		
		System.out.print("\n상세보기 번호 입력:");
		int i=scan.nextInt();
		map.put("seoul_tbl", table[no]);
		map.put("no", i);
		SeoulVO vo=mc.dao.seoulDetailData(map);
		System.out.println("번호:"+vo.getNo());
		System.out.println("제목:"+vo.getTitle());
		System.out.println("주소:"+vo.getAddress());
		System.out.println("설명:"+vo.getMsg());
	}
}
