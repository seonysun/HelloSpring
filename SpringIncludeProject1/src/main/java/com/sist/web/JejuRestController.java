package com.sist.web;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JejuRestController {
	@Autowired
	JejuDAO dao;
	
	@GetMapping(value = "jeju/location_list_vue.do", produces = "text/plain;charset=UTF-8")
	public String location_list(String page) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		map.put("start", (curpage*20)-19);
		map.put("end", curpage*20);
		List<JejuLocationVO> list=dao.jejuLocationListData(map);
		int totalpage=dao.jejuTotalPage();
		int i=0;
		JSONArray arr=new JSONArray();
		for(JejuLocationVO vo:list) {
			JSONObject obj=new JSONObject();
			obj.put("poster", vo.getPoster());
			obj.put("title", vo.getTitle());
			if(i==0) {
				obj.put("curpage", curpage);
				obj.put("totalpage", totalpage);
			}
			arr.add(obj);
			i++;
		}
		return arr.toJSONString();
	}
	
	@GetMapping(value = "jeju/location_detail_vue.do", produces = "text/plain;charset=UTF-8")
	public String location_detail() {
		return "";
	}
	
	@GetMapping(value = "jeju/food_list_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_list(String page) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		map.put("start", (curpage*20)-19);
		map.put("end", curpage*20);
		List<JejuFoodVO> list=dao.jejuFoodListData(map);
		int totalpage=dao.jejuTotalPage();
		int i=0;
		JSONArray arr=new JSONArray();
		for(JejuFoodVO vo:list) {
			JSONObject obj=new JSONObject();
			obj.put("no", vo.getNo());
			obj.put("poster", vo.getPoster());
			obj.put("title", vo.getTitle());
			if(i==0) {
				obj.put("curpage", curpage);
				obj.put("totalpage", totalpage);
			}
			arr.add(obj);
			i++;
		}
		return arr.toJSONString();
	}
	
	@GetMapping(value = "jeju/food_detail_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_detail(int no) {
		JejuFoodVO vo=dao.jejuFoodDetailData(no);
		JSONObject obj=new JSONObject();
		obj.put("no", vo.getNo());
		obj.put("poster", vo.getPoster());
		obj.put("title", vo.getTitle());
		obj.put("score", vo.getScore());
		obj.put("addr", vo.getAddr());
		obj.put("addr2", vo.getAddr2());
		obj.put("tel", vo.getTel());
		obj.put("type", vo.getType());
		obj.put("parking", vo.getParking());
		obj.put("time", vo.getTime());
		obj.put("menu", vo.getMenu());
		return obj.toJSONString();
	}
}
