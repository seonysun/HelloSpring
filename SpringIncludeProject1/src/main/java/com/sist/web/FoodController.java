package com.sist.web;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FoodController {
	@Autowired
	private FoodDAO dao;
	
	@GetMapping("food/food_list.do")
	public String food_list(int cno, Model model) {
		List<FoodVO> fList=dao.foodListData(cno);
		for(FoodVO vo:fList) {
			String address=vo.getAddress();
			address=address.substring(0, address.lastIndexOf("지"));
			vo.setAddress(address.trim());
			
			String poster=vo.getPoster();
			poster=poster.substring(0,  poster.indexOf("^"));
			poster=poster.replace("#", "&");
			vo.setPoster(poster);
		}
		CategoryVO vo=dao.categoryInfoData(cno);
		model.addAttribute("vo", vo);
		model.addAttribute("fList", fList);
		model.addAttribute("main_jsp", "../food/food_list.jsp");
			/* request 데이터는 return 쪽으로 보내짐
			 *  -> model에 감싸진 형태로 : 클래스 캡슐화 
			 * 	-> include / forward 파일은 request를 공유
			 * */
		return "main/main";
	}
	
	@GetMapping("food/detail.do")
	public String food_detail(int fno, Model model) {
		FoodVO vo=dao.foodDetailData(fno);
		String[] addrs=vo.getAddress().split(" ");
		model.addAttribute("vo", vo);
 		model.addAttribute("addr", addrs[1].trim());
		model.addAttribute("main_jsp", "../food/detail.jsp");
		return "main/main";
	}
}
