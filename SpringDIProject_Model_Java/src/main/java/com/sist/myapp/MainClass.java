package com.sist.myapp;
import java.util.*;
import com.sist.model.*;
import com.sist.vo.JejuFoodVO;
import com.sist.vo.JejuLocationVO;
import com.sist.config.*;
import com.sist.dao.JejuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MainClass {
	@Autowired
	private Model model;
	@Autowired
	private JejuService js;
	public static void main(String[] args) {
		AnnotationConfigApplicationContext app=new AnnotationConfigApplicationContext(Config.class);
		MainClass mc=(MainClass)app.getBean("mainClass");
		
		/*
		System.out.println("==========카테고리==========");
		mc.model.bookCategory();
		Scanner scan=new Scanner(System.in);
		System.out.println("> 카테고리 선택: ");
		int no=scan.nextInt();
		String[] cate= {"","순수과학","역사","언어","총류","기술과학","종교","철학","문학","예술","사회과학"};
		System.out.println();
		mc.model.bookListData(cate[no]);
		 */
		
		System.out.println("===== 제주 명소 =====");
		List<JejuLocationVO> lList=mc.js.locationListData();
		int i=1;
		for(JejuLocationVO vo:lList) {
			System.out.println(i+"."+vo.getTitle()+"("+vo.getPrice()+")");
			i++;
		}
		System.out.println();
		System.out.println("===== 제주 맛집 =====");
		List<JejuFoodVO> fList=mc.js.foodListData();
		for(JejuFoodVO vo:fList) {
			System.out.println(vo.getNo()+"."+vo.getTitle()+"("+vo.getScore()+")");
		}
	}
}
