package com.sist.aop;
import java.util.*;

import org.aspectj.lang.ProceedingJoinPoint;

import com.sist.model.*;

import lombok.Setter;

public class ModelAspect {
	@Setter
	private EmpDAO dao;
	public void before() {
		System.out.println("before Call...");
	   	dao.getConnection();
	}
	public void after() {
		System.out.println("after Call...");
		dao.disConnection();
	}
	public Object around(ProceedingJoinPoint jp) throws Throwable{
		Object obj=null;
		//수행 시간 계산 
        long start=0;
        long end=0;
        try {
        	start=System.currentTimeMillis();
        	System.out.println("Client 요청 메소드: "+jp.getSignature().getName());
        	obj=jp.proceed(); //메소드 호출 
        } catch(Exception ex) {
        	ex.printStackTrace();
        }
        finally {
        	end=System.currentTimeMillis();
        	System.out.println("수행 시간: "+(end-start));
        }
		return obj;
	}
	public void afterReturning(Object obj) throws Throwable {
		System.out.println("afterReturning Call...");
			//결과값 받아서 출력 -> web에서 return값 먼저 처리하는 경우 : 자동 로그인, ID저장, 권한 설정
		List<EmpVO> list=(List<EmpVO>)obj;
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "+vo.getEname()+" "+vo.getJob());
		}
	}
	public void afterThrowing(Throwable ex) throws Throwable {
		System.out.println("afterThrowing Call...");
			//catch 수행 시 오류 메시지 -> web에서 alert 처리
		System.out.println(ex.getMessage());
	}
}
