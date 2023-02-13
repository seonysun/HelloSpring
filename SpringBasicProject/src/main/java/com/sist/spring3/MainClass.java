package com.sist.spring3;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app=new ApplicationContext();
		
		AModel a=(AModel)app.getBean("amodel");
		a.display();
		System.out.println("a="+a);
		AModel a1=(AModel)app.getBean("amodel");
		a1.display();
		System.out.println("a1="+a1);
		BModel b=(BModel)app.getBean("bmodel");
		b.display();
		CModel c=(CModel)app.getBean("cmodel");
		c.display();
	}
}
