package com.sist.web;

import javax.servlet.http.HttpSession;

import com.sist.vo.*;
import com.sist.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberFController {
	@Autowired
	private MemberDAO dao;
	
	@GetMapping(value = "member/login_vue.do", produces = "text/html;charset=UTF-8")
	public String login(String id, String pwd, HttpSession session) {
		String res="";
		MemberVO vo=dao.memberLogin(id, pwd);
		res=vo.getMsg();
		if(res.equals("OK")) {
			session.setAttribute("id", vo.getId());
			session.setAttribute("name", vo.getName());
		}
		return res;
	}
	
	@GetMapping("member/logout_vue.do")
	public String member_logout(HttpSession session) {
		session.invalidate();
		return "";
	}
}
