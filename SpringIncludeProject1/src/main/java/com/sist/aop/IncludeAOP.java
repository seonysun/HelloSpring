package com.sist.aop;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.sist.vo.*;
import com.sist.dao.*;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
	/* 
	 * DispatcherServlet이 연결(호출)하는 클래스 : @Controller, @RestController
	 * 	-> 매개변수 값 자동으로 채워서 호출(request.getParameter())
	 * 	-> 나머지 클래스는 매개변수 직접 가져다 사용해야 함(request)
	 */
public class IncludeAOP {
	@Autowired
	private FooterDAO dao;
	
	@After("execution(* com.sist.web.*Controller.*(..))")
		//@After : 메소드 호출 시점, execution : 호출할 메소드 설정
	public void footerData() {
		//현재 사용중인 request 객체를 얻어옴 : AOP, Intercepter, Task
		HttpServletRequest request=
				((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		List<NoticeVO> nList=dao.noticeDataTop5();
		List<DataBoardVO> dList=dao.boardDataTop5();
		request.setAttribute("nList", nList);
		request.setAttribute("dList", dList);
	}
}
