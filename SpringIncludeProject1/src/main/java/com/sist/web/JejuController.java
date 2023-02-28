package com.sist.web;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JejuController {
	@Autowired
	private JejuDAO dao;
	@Autowired
	private ReplyOrmDAO rdao;
	
	@GetMapping("jeju/location.do")
	public String jeju_location(String page, Model model) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int totalpage=dao.jejuTotalPage();
		
		Map map=new HashMap();
		map.put("start", (curpage*20)-19);
		map.put("end", curpage*20);
		
		List<JejuLocationVO> jList=dao.jejuLocationListData(map);
		
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) endpage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("jList", jList);
		model.addAttribute("main_jsp", "../jeju/location.jsp");
		return "main/main";
	}
	
	@GetMapping("jeju/food.do")
	public String jeju_food(String page, Model model) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int totalpage=dao.jejuFoodTotalPage();
		
		Map map=new HashMap();
		map.put("start", (curpage*20)-19);
		map.put("end", curpage*20);
		
		List<JejuFoodVO> jList=dao.jejuFoodListData(map);
		
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) endpage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("jList", jList);
		model.addAttribute("main_jsp", "../jeju/food.jsp");
		return "main/main";
	}
	
	@GetMapping("jeju/food_detail.do")
	public String jeju_food_detail(int fno, int type, Model model) {
		JejuFoodVO vo=dao.jejuFoodDetailData(fno);
		model.addAttribute("vo", vo);
 		List<ReplyVO> rList=rdao.replyListData(fno, type);
 		model.addAttribute("rList", rList);
		model.addAttribute("main_jsp", "../jeju/food_detail.jsp");
		return "main/main";
	}
}
