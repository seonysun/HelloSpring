package com.sist.myapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/*
 * XML, Container ȣ��
 * 	1) �������� ����� Ŭ���� ���� : Model, DAO, Manager
 * 	2) ����� ���� ���������� ���� : VO, DTO, Bean
 * 	3) XML �̿��ؼ� ���
 * 	4) ������ ���̺귯������ ApplicationContext�� ��� ó��
 * 	5) ��ϵ� Ŭ���� ApplicationContext���� ���ͼ� ����, ����
 * 	6) ��ϵ� Ŭ���� �Ҹ�
 * */
public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		//������ ���̺귯�� -> src/main/java���� �ν�, �ٸ� ��Ű�� �ȿ� ������ ��� ��� �߰��� ��� ��
		AModel a=(AModel)app.getBean("a");
		a.display();
		AModel a1=(AModel)app.getBean("a");
		a1.display();
	}
}
