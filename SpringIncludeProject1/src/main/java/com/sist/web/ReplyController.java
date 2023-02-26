package com.sist.web;
import java.util.*;

import javax.servlet.http.HttpSession;

import com.sist.vo.*;
import com.sist.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReplyController {
	private String[] url= {"","../food/detail.do","../jeju/food_detail.do"};
	@Autowired
	private ReplyOrmDAO dao;
	
	@PostMapping("reply/insert.do")
	public String reply_insert(ReplyVO vo, RedirectAttributes ra, HttpSession session) {
		String id=(String)session.getAttribute("id");
		String name=(String)session.getAttribute("name");
		vo.setId(id);
		vo.setName(name);
		
		Map map=new HashMap();
		map.put("prno", vo.getRno());
		map.put("ptype", vo.getType());
		map.put("pid", vo.getId());
		map.put("pname", vo.getName());
		map.put("pmsg", vo.getMsg());
		dao.replyInsert(map);
		
		ra.addAttribute("fno", vo.getRno());
		ra.addAttribute("type", vo.getType());
		return "redirect:"+url[vo.getType()];
	}
	
	@GetMapping("reply/delete.do")
	public String reply_delete(ReplyVO vo, RedirectAttributes ra) {
		dao.replyDelete(vo.getNo());
		ra.addAttribute("fno", vo.getRno());
		ra.addAttribute("type", vo.getType());
		return "redirect:"+url[vo.getType()];
	}
	
	@PostMapping("reply/update.do")
	public String reply_update(ReplyVO vo, RedirectAttributes ra) {
		dao.replyUpdate(vo.getNo(), vo.getMsg());
		ra.addAttribute("fno", vo.getRno());
		ra.addAttribute("type", vo.getType());
		return "redirect:"+url[vo.getType()];
	}
}
