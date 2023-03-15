package com.sist.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.vo.*;
import com.sist.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberRestController {
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private MemberDAO dao;
	
	@GetMapping(value = "member/chat_login_vue.do", produces = "text/html;charset=UTF-8")
	public String chat_login_vue(String id, String pwd, HttpSession session) {
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
	public String logout_vue(HttpSession session) {
		session.invalidate();
		return "";
	}
	
	@GetMapping("member/idcheck_vue.do")
	public String idcheck_vue(String id) {
		int count=dao.joinIdCheck(id);
		String res=count==0?"yes":"no";
		return res;
	}
	
	@GetMapping("member/insert_vue.do")
	public String insert_vue(MemberVO vo) {
		String res="no";
		try {
			res="yes";
			String pwd=encoder.encode(vo.getPwd()); //입력된 비밀번호 암호화
			vo.setPwd(pwd);
			dao.memberInsert(vo);
		} catch(Exception ex){
			res="no";
		}
		return res;
	}
	
	@GetMapping(value = "member/login_vue.do", produces = "text/html;charset=UTF-8")
	public String login_vue(String id, String pwd, boolean ck, HttpSession session, HttpServletResponse response) {
		String res="";
		int count=dao.joinIdCheck(id);
		if(count==0) res="NOID";
		else {
			MemberVO vo=dao.JoinLogin(id, pwd);
			if(encoder.matches(pwd, vo.getPwd())) { //입력된 비밀번호 복호화
				res="OK";
				session.setAttribute("id", id);
				session.setAttribute("name", vo.getName());
				session.setAttribute("admin", vo.getAdmin());
				
				//아이디 저장 쿠키
				if(ck==true) {
					Cookie cookie=new Cookie("id", id);
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24);
					response.addCookie(cookie);
				} else {
					res="NOPWD";
				}
			}
		}
		return res;
	}
}
