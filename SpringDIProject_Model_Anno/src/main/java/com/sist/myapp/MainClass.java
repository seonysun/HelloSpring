package com.sist.myapp;
import java.util.*;
import com.sist.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MainClass {
	@Autowired
	public Model model;
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		MainClass mc=(MainClass)app.getBean("mainClass");
		System.out.println("==========MENU==========");
		System.out.println("1.레시피");
		System.out.println("2.상품");
		System.out.println("3.맛집");
		Scanner scan=new Scanner(System.in);
		System.out.print("> 메뉴를 선택해주세요 : ");
		int no=scan.nextInt();
		if(no==1) {
			mc.model.recipeListData();
		}
		else if(no==2) {
			mc.model.goodsListData();
		}
		else if(no==3) {
			mc.model.foodListData();
		}
	}
}
