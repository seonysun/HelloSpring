package com.sist.web;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FoodController {
	@Autowired
	private FoodDAO dao;
	
	@GetMapping("food/category.do")
	public String food_category(String no, Model model) {
		if(no==null) no="1";
		Map map=new HashMap();
		map.put("cno", no);
		
		List<CategoryVO> list=dao.categoryListData(map);
		
		model.addAttribute("list", list);
		return "food/category";
	}
	
	@GetMapping("food/list.do")
	public String food_list(int cno, Model model) {
		CategoryVO vo=dao.categoryInfoData(cno);
		List<FoodVO> list=dao.foodListData(cno);
		for(FoodVO fvo:list) {
			String address=fvo.getAddress();
			address=address.substring(0, address.lastIndexOf("지"));
			fvo.setAddress(address.trim());
			
			String poster=fvo.getPoster();
			poster=poster.substring(0, poster.indexOf("^"));
			poster=poster.replace("#", "&");
			fvo.setPoster(poster);
		}
		
		model.addAttribute("vo", vo);
		model.addAttribute("list", list);
		return "food/list";
	}
	
	@GetMapping("food/detail.do")
	public String food_detail(int fno, Model model) {
		FoodVO vo=dao.foodDetailData(fno);
		
		model.addAttribute("vo", vo);
		return "food/detail";
	}
	
	@RequestMapping("food/find.do")
		//검색 처음 실행 시 get(a 태그), 검색어 입력 시 post(form 태그) -> GET/POST 방식 동시 처리 requestMapping
	public String food_find(String addr, String page, Model model) {
		String s="";
		if(addr==null || addr.equals("")) s="all";
			//웹에서 매개변수 넘어올 때 null이 아닌 ""으로 넘어오는 경우가 많음 -> 처리 시 주의
		else s=addr;
		Map map=new HashMap();
		map.put("ss", s);

		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int rowsize=20;
		int start=rowsize*(curpage-1)+1;
		int end=rowsize*curpage;
		map.put("start", start);
		map.put("end", end);
		
		List<FoodVO> list=dao.foodFindData(map);
		
		model.addAttribute("list", list);
		return "food/find";
	}
}
