package com.sist.exception;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
/*
 * 1. Controller에서 에러 발생했을 경우 
 * 	- 메모리 할당
 * 		@Component, @Repository, @Service, @Controller, @RestController, 
 * 		@ControllerAdvice, @RestControllerAdvice, @Configuration
 * 		=> xml처리 : <context:component-scan base-package="com.sist.*"/>
 * 		      자바 처리 : @ComponentScan(basePackages={"com.sist.*"})
 * 		
 * */
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class CommonsException {
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
