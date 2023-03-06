package com.sist.common;
import java.io.*;
import java.sql.*;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/* 1. 데이터 주입 어노테이션
 * 	- @Value : 일반 문자열 주입
 * 	- @Autowired, @Qualifier, @Resource(*) : 클래스 객체 주소 주입
 * 		- @Resource(name = "id") = @Autowired + @Qualifier("id")
 * 		- @Resource의 경우 JDK 1.8버전까지만 사용 가능(실무)
 * 
 * 2. 에러
 * 	(1) RuntimeException
 * 		- NumberFormatException
 * 		- NullPointerException
 * 		- ClassCastException
 * 	(2) SQLException
 * 	(3) IOException
 * 	(4) Exception
 * */
@ControllerAdvice
public class CommonException {
	@ExceptionHandler(Exception.class)
	public void exceptionHandler(Exception ex) {
		System.out.println("=====에러 발생 Exception=====");
		System.out.println(ex.getMessage());
	}
	
	@ExceptionHandler(RuntimeException.class)
	public void runtimeException(RuntimeException ex) {
		System.out.println("=====에러 발생 RuntimeException=====");
		System.out.println(ex.getMessage());
	}
	
	@ExceptionHandler(IOException.class)
	public void ioException(IOException ex) {
		System.out.println("=====에러 발생 IOException=====");
		System.out.println(ex.getMessage());
	}
	
	@ExceptionHandler(SQLException.class)
	public void sqlException(SQLException ex) {
		System.out.println("=====에러 발생 SQLException=====");
		System.out.println(ex.getMessage());
	}
}
