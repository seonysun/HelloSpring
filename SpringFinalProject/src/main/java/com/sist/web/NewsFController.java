package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsFController {
	@GetMapping("news/news_find.do")
	public String news_find() {
		return "news/find";
	}
}
