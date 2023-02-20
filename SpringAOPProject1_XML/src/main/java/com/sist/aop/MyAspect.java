package com.sist.aop;
/*
 * 1. aspect : 자주 호출되는 공통 모듈 => AOP
 * 	cf. OOP => 공통 메소드 계속해서 직접 호출
 *             	ex. getConnection(), disConnection()
 *                  CreateConnection
 *  	스프링 => 공통 사용 부분 자동화 처리 : AOP
 *  			 ex. CommonsData.footData()
 *  	AOP 처리 => 로그파일, 트랜잭션, 보안 
 *  
 * 	1) 호출할 시점 설정 : JoinPoint
 *  	- Before 
 *      - After
 *      - AfterReturning
 *      - AfterThrowing
 *      - Around
 *      ex. public String display() {
 *          	@Before -> getConnection()
 *           	try {
 *              	======== @Around 시작 -> setAutoCommit(false)
 *              	소스 코딩
 *              	======== @Around 끝 -> commit()
 *           	} catch(Exception ex) {
 *              	@AfterThrowing -> rollback()
 *           	} finally {
 *              	@After -> disConnection()
 *           	}
 *           	@AfterReturning -> 리턴값을 받아서 처리 
 *           	return "";
 *       	}
 *       
 * 	2) 자동화할 메소드 설정 : PointCut 
 *    	- execution("리턴형 패키지.클래스.메소드(매개변수)"
 *    		ex. execution("* com.sist.main.*.*(..)")
 *    			-> 모든 리턴형, com.sist.main 패키지의 모든 클래스의 모든 메소드, 모든 매개변수
 *      - within("com.sist.main.*") : 패키지 단위로 모든 클래스에 적용
 *    
 *  3) Advice : JoinPoint + PointCut => Aspect
 *    
 *  4) Weaving : 통합 => Proxy패턴 (대리자) 
 *    
 *  ex. public class A {
 *            public void display() {}
 *      }
 *      public class Proxy {
 *          private A a;
 *          public Proxy(A a) {
 *             this.a=a;
 *          }
 *          public void display() {
 *              @Before 
 *              System.out.println("Before")
 *              a.display()
 *              @After
 *              System.out.println("After")
 *          }
 *      }
 * */
public class MyAspect {
	public void getConnection() {
		System.out.println("오라클 연결");
	}
	public void disConnection() {
		System.out.println("오라클 닫기");
	}
}
