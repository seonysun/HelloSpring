package com.sist.web;
import java.util.*;
import com.sist.vo.*;
import com.sist.openapi.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsRestController {
	@Autowired
	private NaverNewsManager mgr;
	
	@GetMapping(value = "news/news_find_vue.do", produces = "text/plain;charset=UTF-8")
	public String news_find_vue(String ss) {
		List<NewsVO> list=mgr.newsListData(ss);
		JSONArray arr=new JSONArray();
		for(NewsVO vo:list) {
			JSONObject obj=new JSONObject();
			obj.put("title", vo.getTitle());
			obj.put("description", vo.getDescription());
			obj.put("pubDate", vo.getPubDate());
			obj.put("link", vo.getLink());
			arr.add(obj);
		}
		return arr.toJSONString();
	}
}
