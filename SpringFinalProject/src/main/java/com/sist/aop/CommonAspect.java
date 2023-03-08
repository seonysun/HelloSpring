package com.sist.aop;
import com.sist.openapi.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;
import com.sist.vo.NewsVO;

@Aspect
@Component
public class CommonAspect {
	@Autowired
	private FoodDAO dao;
	@Autowired
	private NaverNewsManager mgr;
	
	@Around("execution(* com.sist.web.*Controller.*(..))")
	public Object around(ProceedingJoinPoint jp) throws Throwable{
		Object obj=null;
			System.out.println("==============================");
		long start=System.currentTimeMillis();
			System.out.println("클라이언트 요청 전:"+jp.getSignature().getName());
		obj=jp.proceed();
			System.out.println("클라이언트 요청완료");
		long end=System.currentTimeMillis();
			System.out.println("오청 처리에 걸린 시간:"+(end-start));
		return obj;
	}
	
	@After("execution(* com.sist.web.*FController.*(..))")
	public void footerData() {
		HttpServletRequest request=
			((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		List<FoodVO> tList=dao.foodTop7();
		for(FoodVO vo:tList) {
			String address=vo.getAddress();
			String[] addr=address.split(" ");
			vo.setAddress(addr[1].trim());
		}
		request.setAttribute("tList", tList);
		
		List<NewsVO> nList=mgr.newsListData("맛집");
		request.setAttribute("nList", nList);
	}
}
