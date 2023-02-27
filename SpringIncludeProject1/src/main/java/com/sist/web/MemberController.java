package com.sist.web;
import java.util.*;

import javax.servlet.http.HttpSession;

import com.sist.vo.*;
import com.sist.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
	@Autowired
	private MemberDAO dao;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping("member/join.do")
	public String member_join(Model model) {
		model.addAttribute("main_jsp", "../member/join.jsp");
		return "main/main";
	}
	
	@PostMapping("member/join_ok.do")
	public String member_join_ok(MemberVO vo, Model model) {
		String en=encoder.encode(vo.getPwd()); //입력된 비밀번호 암호화
		vo.setPwd(en); //암호화된 상태로 저장
		dao.memberInsert(vo);
		return "redirect:../main/main.do";
	}
	
	@PostMapping("member/login_ok.do")
	@ResponseBody
		//화면 변경하지 않고 원하는 데이터 전송하는 어노테이션 @RestController -> 중간에 jsp 파일 없이 문자열 바로 보낼 수 있음
	public String member_login_ok(MemberVO vo, HttpSession session) {
		String result="";
		int count=dao.memberIdCheck(vo.getId());
		if(count==0) {
			result="NOID";
		} else {
			MemberVO pvo=dao.memberPwdCheck(vo.getId());
			if(encoder.matches(vo.getPwd(), pvo.getPwd())) { //복호화 : 암호화된 비밀번호와 비교
				result="OK";
				session.setAttribute("id", pvo.getId());
				session.setAttribute("name", pvo.getName());
			} else {
				result="NOPWD";
			}
		}
		return result;
	}
	
	@PostMapping("member/logout.do")
	public String member_logout(MemberVO vo, HttpSession session) {
		session.invalidate(); //세션에 저장된 모든 내용 삭제
		return "redirect:../main/main.do";
	}
}
