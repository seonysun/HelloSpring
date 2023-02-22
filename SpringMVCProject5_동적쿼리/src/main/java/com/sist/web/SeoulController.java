package com.sist.web;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SeoulController {
	@Autowired
	private SeoulDAO dao;
	
	@GetMapping("seoul/list.do")
	public String seoul_list(String page, String cate, Model model) {
		if(cate==null) cate="1";
		String[] category= {"", "location","nature","shop"};
		String table="seoul_"+category[Integer.parseInt(cate)];
		Map map=new HashMap();
		map.put("table", table);
		
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int totalpage=dao.seoulTotalPage(map);
		
		int rowsize=20;
		int start=rowsize*(curpage-1)+1;
		int end=rowsize*curpage;
		map.put("start", start);
		map.put("end", end);
		
		List<SeoulVO> list=dao.seoulListData(map);
		for(SeoulVO vo:list) {
			String title=vo.getTitle();
			if(title.length()>16) {
				title=title.substring(0, 15)+"...";
				vo.setTitle(title);
			}
			vo.setTitle(title);
		}
		
		final int BLOCK=5;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) endpage=totalpage;
		
		model.addAttribute("cate", cate);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("list", list);
		return "seoul/list";
	}
	
	@GetMapping("seoul/detail.do")
	public String seoul_detail(int no, Model model) {
//		if(cate==null) cate="1";
//		String[] category= {"", "location","nature","shop"};
//		String table="seoul_"+category[Integer.parseInt(cate)];
//		Map map=new HashMap();
//		map.put("table", table);
//		map.put("no", no);
		
		SeoulVO vo=dao.seoulDetailData(no);
		
		String addr=vo.getAddress(); 
			//03177 서울 종로구 새문안로 55 (신문로2가, 서울역사박물관)
		String[] addrs=addr.split(" ");
		addr=addrs[2].trim();
		
		List<FoodVO> list=dao.foodListData(addr);
		
		model.addAttribute("vo", vo);
		model.addAttribute("list", list);
		return "seoul/detail";
	}
	
	@RequestMapping("seoul/find.do")
	public String seoul_find(String addr, String page, Model model) {
		Map map=new HashMap();
		map.put("addr", addr);
		
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int totalpage=dao.seoulFindTotalPage(addr);
		
		int rowsize=20;
		int start=rowsize*(curpage-1)+1;
		int end=rowsize*curpage;
		map.put("start", start);
		map.put("end", end);
		
		List<SeoulVO> list=dao.seoulFindData(map);
		for(SeoulVO vo:list) {
			String title=vo.getTitle();
			if(title.length()>16) {
				title=title.substring(0, 15)+"...";
				vo.setTitle(title);
			}
			vo.setTitle(title);
		}
		
		final int BLOCK=5;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) endpage=totalpage;
		
		model.addAttribute("addr", addr);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("list", list);
		return "seoul/find";
	}
}
