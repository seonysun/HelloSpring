package com.sist.web;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.vo.*;
import com.sist.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SeoulController {
	@Autowired
	private SeoulDAO dao;
	
	@GetMapping("seoul/list.do")
	public String seoul_list(String page, String cate, Model model, HttpServletRequest request) {
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
		
		Cookie[] cookies=request.getCookies();
		List<SeoulVO> cList=new ArrayList<SeoulVO>();
		if(cookies!=null) {
			for(int i=cookies.length-1;i>=0;i--) {
				if(cookies[i].getName().startsWith("seoul")) {
					String no=cookies[i].getValue();
					Map map1=new HashMap();
					map1.put("no", no);
					map1.put("table", table);
					SeoulVO vo=dao.seoulDetailData(map1);
					cList.add(vo);
				}
			}
		}
		
		model.addAttribute("cate", cate);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("list", list);
		model.addAttribute("cList", cList);
		return "seoul/list";
	}
	
	@GetMapping("seoul/detail_before.do")
	public String seoul_detail_before(String cate, String no, HttpServletResponse response, RedirectAttributes ra) {
		Cookie cookie=new Cookie("seoul"+no, no);
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		ra.addAttribute("cate", cate);
		ra.addAttribute("no", no);
		return "redirect:detail.do";
	}
	
	@GetMapping("seoul/detail.do")
	public String seoul_detail(int no, String cate, Model model) {
		if(cate==null) cate="1";
		String[] category= {"", "location","nature","shop"};
		String table="seoul_"+category[Integer.parseInt(cate)];
		Map map=new HashMap();
		map.put("table", table);
		map.put("no", no);
		
		SeoulVO vo=dao.seoulDetailData(map);
		
		String addr="";
			//address로 받아서 바로 처리하면 null값 ...왜????????????
		String address=vo.getAddress(); 
			//03177 서울 종로구 새문안로 55 (신문로2가, 서울역사박물관)
		String[] addrs=address.split(" ");
		addr=addrs[2].trim();
		
		List<FoodVO> list=dao.foodListData(addr);
		
		model.addAttribute("cate", cate);
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
