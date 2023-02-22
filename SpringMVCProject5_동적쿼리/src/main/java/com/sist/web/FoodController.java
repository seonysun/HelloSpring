package com.sist.web;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
