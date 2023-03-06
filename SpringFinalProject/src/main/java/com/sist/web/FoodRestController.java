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
public class FoodRestController {
	@Autowired
	private FoodDAO dao;
	
	@GetMapping(value = "food/food_main_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_main_vue() {
		List<CategoryVO> list=dao.categoryListData();
		JSONArray arr=new JSONArray();
		for(CategoryVO vo:list) {
			JSONObject obj=new JSONObject();
			obj.put("cno", vo.getCno());
			obj.put("title", vo.getTitle());
			obj.put("poster", vo.getPoster());
			arr.add(obj);
		}
		return arr.toJSONString();
	}
	
	@GetMapping(value = "food/category_info_vue.do", produces = "text/plain;charset=UTF-8")
	public String cate_info_vue(int cno) {
		CategoryVO vo=dao.categoryInfoData(cno);
		JSONObject obj=new JSONObject();
		obj.put("title", vo.getTitle());
		obj.put("subject", vo.getSubject());
		return obj.toJSONString();
	}
	
	@GetMapping(value = "food/food_list_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_list_vue(int cno) {
		List<FoodVO> list=dao.foodListData(cno);
		JSONArray arr=new JSONArray();
		for(FoodVO vo:list) {
			JSONObject obj=new JSONObject();
			obj.put("cno", vo.getCno());
			obj.put("fno", vo.getFno());
			obj.put("name", vo.getName());
			obj.put("tel", vo.getTel());
			String address=vo.getAddress();
			address=address.substring(0, address.lastIndexOf("지"));
			obj.put("address", address.trim());
			obj.put("type", vo.getType());
			String poster=vo.getPoster();
			poster=poster.substring(0, poster.indexOf("^"));
			poster=poster.replace("#", "&");
			obj.put("poster", poster);
			obj.put("score", vo.getScore());
			arr.add(obj);
		}
		return arr.toJSONString();
	}

	@GetMapping(value = "food/food_detail_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_detail(int fno) {
		FoodVO vo=dao.foodDetailData(fno);
		JSONObject obj=new JSONObject();
		obj.put("fno", vo.getFno());
		obj.put("name", vo.getName());
		obj.put("score", vo.getScore());
		obj.put("address", vo.getAddress());
		obj.put("tel", vo.getTel());
		obj.put("type", vo.getType());
		obj.put("poster", vo.getPoster());
		obj.put("price", vo.getPrice());
		obj.put("parking", vo.getParking());
		obj.put("time", vo.getTime());
		obj.put("menu", vo.getMenu());
		return obj.toJSONString();
	}
}
