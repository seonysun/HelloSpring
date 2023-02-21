package com.sist.web;
import java.util.*;
import com.sist.dao.*;
import com.sist.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FoodController {
	@Autowired
	private FoodDAO dao;
	
	@GetMapping("food/category.do")
	/* Spring 4.3 버전
	 * 	- RequestMapping(GET+POST) -> GetMapping + PostMapping 분리
	 * 		- GetMapping : default, <a>, sendRedirect(), location.href()
	 * 		- PostMapping : <form>, ajax/type="post"
	 * */
	public String food_category(String cno, Model model) {
		if(cno==null) cno="1";
		int no=Integer.parseInt(cno);
		int start=0, end=0;
		if(no==1) {
			start=181;
			end=192;
		} else if(no==2) {
			start=193;
			end=198;
		} else if(no==3) {
			start=199;
			end=210;
		}
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
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
}
