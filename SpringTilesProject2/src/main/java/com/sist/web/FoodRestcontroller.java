package com.sist.web;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
 * JSON : 문자 기반의 데이터 포맷
 * JSONP(JSON with Padding) : 스크립트 호출 방식
 * 								-> 스크립트 내에 JSON 포함시켜 가져오는 방식
 * 클로져 : 내부함수가 자신이 선언되었을 때의 환경(스코프) 기억
 * 			-> 스코프 밖에서 호출되어도 해당 스코프에 접근 가능
 * */
@RestController
public class FoodRestcontroller {
	@Autowired
	private FoodDAO dao;
	
	@RequestMapping(value = "food/food_search_vue.do", produces = "text/plain;charset=UTF-8") 
					//자바스크립트로 데이터 넘어가므로 addr로 들어오는 한글 변환 필요
	public String food_search(String page, String addr) {
		if(addr==null) addr="역삼";
		
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		map.put("start", (curpage*20)-19);
		map.put("end", curpage*20);
		map.put("addr", addr);
		List<FoodVO> list=dao.foodSearchData(map);
		int totalpage=dao.foodSearchTotalPage(map);
		
		//자바스크립트에서 list는 인식 불가 -> 인식할 수 있는 JSON 형태로 변경 필요
		JSONArray arr=new JSONArray(); //List
		int i=0;
		for(FoodVO vo:list) {
			JSONObject obj=new JSONObject(); //VO
			obj.put("fno", vo.getFno());
			obj.put("name", vo.getName());
			String poster=vo.getPoster();
			poster=poster.substring(0, poster.indexOf("^"));
			poster=poster.replace("#", "&");
			obj.put("poster", poster);
			
			if(i==0) { //page 정보는 모든 obj에 입력할 필요는 없으므로 첫 번째 obj에만 입력
				obj.put("curpage", curpage);
				obj.put("totalpage", totalpage);
			}
			arr.add(obj);
			i++;
		}
		return arr.toJSONString();
	}
	
	@GetMapping(value = "food/location_detail_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_location_detail(int fno) {
		FoodVO vo=dao.foodLocationDetailData(fno);
		JSONObject obj=new JSONObject();
		obj.put("fno", vo.getFno());
		obj.put("name", vo.getName());
		obj.put("poster", vo.getPoster());
		obj.put("tel", vo.getTel());
		obj.put("address", vo.getAddress());
		obj.put("parking", vo.getParking());
		obj.put("time", vo.getTime());
		obj.put("price", vo.getPrice());
		obj.put("type", vo.getType());
		obj.put("menu", vo.getMenu());
		return obj.toJSONString();
	}
}
