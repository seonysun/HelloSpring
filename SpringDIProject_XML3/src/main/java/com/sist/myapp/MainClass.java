package com.sist.myapp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
import com.sist.vo.EmpVO;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		EmpDAO dao=app.getBean("dao",EmpDAO.class);
        List<EmpVO> list=dao.empListData();
        for(EmpVO vo:list) {
        	System.out.println(vo.getEmpno()+" "
        			   +vo.getEname()+" "
        			   +vo.getJob()+" "
        			   +new SimpleDateFormat("yyyy-MM-dd").format(vo.getRegdate())+" "
        			   +vo.getSal()+" "
        			   +vo.getDname()+" "
        			   +vo.getLoc()+" "
        			   +vo.getRank());
        }
	}
}
