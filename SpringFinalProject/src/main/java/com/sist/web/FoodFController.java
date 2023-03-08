package com.sist.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FoodFController {
	@GetMapping("food/food_list.do")
	public String food_list(int cno, Model model) {
		model.addAttribute("cno", cno);
		return "food/list";
	}
	
	@GetMapping("food/food_detail_before.do")
	public String food_detail_before(int fno, HttpServletResponse response, RedirectAttributes ra) {
		Cookie cookie=new Cookie("food"+fno, String.valueOf(fno));
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		ra.addAttribute("fno", fno);
		return "redirect:../food/food_detail.do";
	}
	
	@GetMapping("food/food_detail.do")
	public String food_detail(int fno, Model model) {
		model.addAttribute("fno", fno);
		return "food/detail";
	}
	
	@GetMapping("food/food_find.do")
	public String food_find() {
		return "food/find";
	}
	
	@GetMapping("food/food_location_detail.do")
	public String food_find_detail(int fno, Model model) {
		model.addAttribute("fno", fno);
		return "food/location_detail";
	}
	
	@GetMapping("food/food_recommand.do")
	public String food_recommand() {
		return "food/recommand";
	}
}
