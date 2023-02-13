package com.sist.spring2;

public class HelloImpl implements Hello{
	@Override
	public String sayHello(String name) {
		return name+"님 안녕하세요!";
	}
}
