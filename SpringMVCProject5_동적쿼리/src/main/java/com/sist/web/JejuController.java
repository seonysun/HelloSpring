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
		int totalpage=dao.jejuTotalPage();

		int rowsize=20;
		int start=rowsize*(curpage-1)+1;
		int end=rowsize*curpage;
		Map map=new HashMap();
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
	
	@GetMapping("jeju/detail.do")
	public String jeju_detail(int no, Model model) {
		JejuLocationVO vo=dao.jejuDetailData(no);
		
		String info=vo.getInfo();
		if(info.indexOf("^")>=0) {
			//info 이미지가 여러개인 경우 잘라서 한개만 저장 -> 이미지 한개인 경우 ^가 존재하지 않으므로 결과값 -1
			info=info.substring(0, info.indexOf("^"));
			vo.setInfo(info);
		}
		vo.setInfo(info);
		
		String addr=vo.getAddr();
		String[] addrs=addr.split(" ");
		Map map=new HashMap();
		map.put("addr", addrs[1].trim());
		
		List<JejuFoodVO> list=dao.jejuFoodData(map);
		
		model.addAttribute("vo", vo);
		model.addAttribute("list", list);
		return "jeju/detail";
	}
}
