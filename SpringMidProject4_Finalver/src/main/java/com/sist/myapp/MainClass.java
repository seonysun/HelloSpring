package com.sist.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.*;
import com.sist.service.EmpDeptService;
import com.sist.vo.*;
import java.util.*;

@Component("mc")
public class MainClass {
	@Autowired
	private EmpDAO edao;
	@Autowired
	private DeptDAO ddao;
	@Autowired
	private EmpDeptService es;
	
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("application-*.xml");
		MainClass mc=app.getBean("mc",MainClass.class);
								//자동 형변환
		//DAO 각각 등록
		List<EmpVO> eList=mc.edao.empListData();
		List<DeptVO> dList=mc.ddao.deptListData();
		System.out.println("==========사원 목록==========");
		for(EmpVO vo:eList) {
			System.out.println(vo.getEmpno()+" "+vo.getEname()+" "+vo.getJob());
		}
		System.out.println("==========부서 목록==========");
		for(DeptVO vo:dList) {
			System.out.println(vo.getDeptno()+" "+vo.getDname()+" "+vo.getLoc());
		}
		
		//통합 등록
		System.out.println("\n>서비스 이용<");
		List<EmpVO> list1=mc.es.empListData();
		List<DeptVO> list2=mc.es.deptListData();
		System.out.println("==========사원 목록==========");
		for(EmpVO vo:list1) {
			System.out.println(vo.getEmpno()+" "+vo.getEname()+" "+vo.getJob());
		}
		System.out.println("==========부서 목록==========");
		for(DeptVO vo:list2) {
			System.out.println(vo.getDeptno()+" "+vo.getDname()+" "+vo.getLoc());
		}
	}
}
