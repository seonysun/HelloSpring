package com.sist.web;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@Autowired
	private FoodDAO fdao;
	
	@GetMapping("main/main.do")
	public String main_main(Model model) {
		List<CategoryVO> cList=fdao.categoryListData();
		model.addAttribute("cList", cList);
		model.addAttribute("main_jsp", "../main/home.jsp");
		return "main/main";
	}
}
