package com.sist.intercepter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//intercepter는 메모리할당 하지 않으므로 상속 필요
public class CommonIntercepter extends HandlerInterceptorAdapter{
	/*								  intercept		 intercept					intercept
	 * 사용자 요청(.do) -> DispatcherServlet -> @Controller -> ViewResolver -> JSP 
	 * 								  preHandle		 postHandle					afterHandle
	 * 	- preHandle : 자동로그인, ID 저장
	 * */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for(int i=0;i<cookies.length;i++) {
				String key=cookies[i].getName();
				if(key.equals("id")) {
					cookies[i].setPath("/");
					String id=cookies[i].getValue();
					request.setAttribute("id", id);
					request.setAttribute("ck", true);
					break;
				}
			}
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
	
}
