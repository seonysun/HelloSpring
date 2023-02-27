package com.sist.web;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SeoulController {
	@Autowired
	private SeoulDAO dao;
	
	@GetMapping("seoul/location.do") 
		//사용자 요청에 따라 메소드 찾음 => if : 어노테이션 (index: 찾기)
	public String seoul_list(String page, Model model) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int totalpage=dao.seoulTotalPage();
		
		Map map=new HashMap();
		map.put("start", (curpage*20)-19);
		map.put("end", curpage*20);
		
		List<SeoulVO> sList=dao.seoulLocationListData(map);
		
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) endpage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("sList", sList);
		model.addAttribute("main_jsp", "../seoul/location.jsp");
		return "main/main";
	}
	
	@GetMapping("seoul/nature.do") 
	public String seoul_nature(String page, Model model) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int totalpage=dao.natureTotalPage();
		
		Map map=new HashMap();
		map.put("start", (curpage*20)-19);
		map.put("end", curpage*20);
		
		List<SeoulVO> sList=dao.seoulNatureListData(map);
		
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) endpage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("sList", sList);
		model.addAttribute("main_jsp", "../seoul/nature.jsp");
		return "main/main";
	}
	
	@GetMapping("seoul/shop.do") 
	public String seoul_shop(String page, Model model) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int totalpage=dao.shopTotalPage();
		
		Map map=new HashMap();
		map.put("start", (curpage*20)-19);
		map.put("end", curpage*20);
		
		List<SeoulVO> sList=dao.seoulShopListData(map);
		
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) endpage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("sList", sList);
		model.addAttribute("main_jsp", "../seoul/shop.jsp");
		return "main/main";
	}
}
