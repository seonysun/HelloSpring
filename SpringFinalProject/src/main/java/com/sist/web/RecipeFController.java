package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeFController {
	@GetMapping("recipe/recipe_list.do")
	public String recipe_list() {
		return "recipe/recipe_list";
	}
	
	@GetMapping("recipe/chef_list.do")
	public String chef_list() {
		return "recipe/chef_list";
	}
}
