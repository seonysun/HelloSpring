package com.sist.spring3;
import java.util.*;

//클래스 관리자(컨테이너) -> 스프링에서 이미 제작되어있음
public class ApplicationContext {
	//관리할 클래스들을 저장할 컬렉션 생성
	private Map clsMap=new HashMap(); 
	//클래스 객체 호출 가능하도록 연결 -> 싱글턴(첫번째 하나만 생성, 이후 재사용) -> 메모리 절약 가능, 연결 단순
	public ApplicationContext() {
		clsMap.put("amodel", new AModel());
					//key 등록, key에 대한 value값으로 생성할 객체 연결
		clsMap.put("bmodel", new BModel());
		clsMap.put("cmodel", new CModel());
	}
	//등록된 key로 객체 호출하는 메소드 생성
	public Object getBean(String key) {
		return clsMap.get(key);
	}
}
