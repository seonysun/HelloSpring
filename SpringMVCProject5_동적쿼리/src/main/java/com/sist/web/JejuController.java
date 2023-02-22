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
	
	@GetMapping("jeju/list.do")
	public String jeju_list(String page, Model model) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowsize=20;
		int start=rowsize*(curpage-1)+1;
		int end=rowsize*curpage;
		map.put("start", start);
		map.put("end", end);
		List<JejuLocationVO> list=dao.jejuLocationListData(map);
		for(JejuLocationVO vo:list) {
			String title=vo.getTitle();
			if(title.length()>19) {
				title=title.substring(0, 16)+"...";
				vo.setTitle(title);
			}
			vo.setTitle(title);
		}
		int totalpage=dao.jejuTotalPage();
		final int BLOCK=5;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) endpage=totalpage;
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		return "jeju/list";
	}
}
