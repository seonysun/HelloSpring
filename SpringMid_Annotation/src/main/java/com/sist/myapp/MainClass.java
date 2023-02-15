package com.sist.myapp;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.vo.*;
import com.sist.config.GoodsConfig;
import com.sist.dao.*;

@Component
public class MainClass {
	@Autowired
	private GoodsDAO dao;
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
//		AnnotationConfigApplicationContext app=new AnnotationConfigApplicationContext(GoodsConfig.class);
		MainClass mc=(MainClass)app.getBean("mainClass");
		
		String[] table= {"","all","best","new","special"};
		Scanner scan=new Scanner(System.in);
		System.out.println("1. 전체 상품");
		System.out.println("2. 베스트 상품");
		System.out.println("3. 신상품");
		System.out.println("4. 특별 상품");
		System.out.print("> 상품 탭을 선택하세요: ");
		int type=scan.nextInt();
		Map map=new HashMap();
		map.put("goods_tbl", "goods_"+table[type]);
		
		List<GoodsVO> list=mc.dao.goodsListData(map);
		for(GoodsVO vo:list) {
			System.out.println(vo.getNo()+"."+vo.getGoods_name()+"("+vo.getGoods_price()+")");
		}
	}
}
