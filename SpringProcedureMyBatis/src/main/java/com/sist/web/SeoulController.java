package com.sist.web;
import java.util.*;
import com.sist.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SeoulController {
	@Autowired
	private SeoulDAO dao;
	
	@GetMapping("seoul/list.do")
	public String seoul_list(String page, Model model) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int start=(curpage*20)-19;
		int end=curpage*20;
		Map map=new HashMap();
		map.put("pstart", start);
		map.put("pend", end);
		List<SeoulVO> list=dao.seoulListData(map);
		
		int totalpage=dao.seoulTotalPage();
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("list", list);
		return "seoul/list";
	}
}
