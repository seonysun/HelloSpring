package com.sist.sawon;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
/* 
 * 스프링 : 클래스(컴포넌트) 관리하는 컨테이너
 * 	1) 컨테이너 라이브러리
 * 		BeanFactory
 * 			 |
 * 		ApplicationContext : 클래스를 Map에 저장(key=id, value=객체생성) 
 * 			 |					-> DL(클라이언트가 요청한 클래스 찾아줌) : getBean(id)
 * 			 |					-> 클래스 간 연결관계 없이 독립적으로 동작(결합성 낮음) : 유지보수 good
 * 	 GenericApplicationContext
 *   AnnocationConfingApplicationContext : 자바로 세팅할 때
 *   WebApplicationContext(MVC)
 *   
 *  2) 컨테이너의 역할 : 클래스 관리(객체 생성 ~ 소멸)
 *  	- 객체 생성(메모리 할당)
 *  		- new
 *  		- 리플렉션(*) : 클래스 이름으로 메모리 할당 
 *  						-> Class.forName()
 *  	- 필요한 데이터를 설정 : DB 연결, 채팅 서버 연결(소켓) 시 필요한 값 설정
 *  		- 멤버변수 값 설정 : DI
 *  			-> setXxx()
 *  			-> 생성자
 *  	- 메소드 호출
 *  		- 객체 생성 시 : init-method
 *  		- 객체 소멸 시 : destroy-method
 *  3) 생명주기
 *  	- 생성자 DI(객체 생성)
 *  	- setter DI
 *  	- 프로그래머 활용
 *  	- 객체 소멸
 * */
public class Sawon implements InitializingBean,DisposableBean,BeanNameAware,BeanFactoryAware{
	private String name;
	private String sex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		System.out.println("2. setName() 호출 : name 값 설정 -> "+name);
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
		System.out.println("2. setSex() 호출 : sex 값 설정 -> "+sex);
	}

	public Sawon() {
		System.out.println("1. 스프링에서 메모리 할당 : Sawon 객체 생성(Class.forName())");
	}
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("setBeanFactory() call..");
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("setBeanName() call.."+name);
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("3. 객체 소멸");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("2. set 메소드 종료 : DI 완료");
	}
	
	public void print() {
		System.out.println("프로그래머 활용 메소드");
		System.out.println("name:"+name);
		System.out.println("sex:"+sex);
	}
}
