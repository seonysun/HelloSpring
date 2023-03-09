package com.sist.web;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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

	@GetMapping(value = "food/cookie_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_cookie_vue(HttpServletRequest request) {
		Cookie[] cookies=request.getCookies();
		List<FoodVO> list=new ArrayList<FoodVO>();
		if(cookies!=null) {
			for(int i=cookies.length-1;i>=0;i--) {
				if(cookies[i].getName().startsWith("food")) {
					String fno=cookies[i].getValue();
					FoodVO vo=dao.foodCookieData(Integer.parseInt(fno));
					list.add(vo);
				}
			}
		}
		JSONArray arr=new JSONArray();
		int i=0;
		for(FoodVO vo:list) {
			if(i>=9) break;
			JSONObject obj=new JSONObject();
			obj.put("fno", vo.getFno());
			obj.put("name", vo.getName());
			String poster=vo.getPoster();
			poster=poster.substring(0, poster.indexOf("^"));
			poster=poster.replace("#", "&");
			obj.put("poster", poster);
			arr.add(obj);
			i++;
		}
		return arr.toJSONString();
	}
	
	@GetMapping(value = "food/food_detail_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_detail_vue(int fno) {
		FoodVO vo=dao.foodDetailData(fno);
		JSONObject obj=new JSONObject();
		obj.put("fno", vo.getFno());
		obj.put("name", vo.getName());
		obj.put("score", vo.getScore());
		String address=vo.getAddress();
		String addr1=address.substring(0, address.lastIndexOf("지"));
		String addr2=address.substring(address.lastIndexOf("지")+3);
		obj.put("addr1", addr1.trim());
		obj.put("addr2", addr2.trim());
		obj.put("tel", vo.getTel());
		obj.put("type", vo.getType());
		obj.put("poster", vo.getPoster());
		obj.put("price", vo.getPrice());
		obj.put("parking", vo.getParking());
		obj.put("time", vo.getTime());
		obj.put("menu", vo.getMenu());
		return obj.toJSONString();
	}
	
	@GetMapping(value = "food/food_find_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_find_vue(int page, String address) {
		int totalpage=dao.foodLocationTotalPage(address);
		Map map=new HashMap();
		map.put("start", (page*20)-19);
		map.put("end", page*20);
		map.put("address", address);
		List<FoodVO> list=dao.foodLocationFindData(map);
		
		final int BLOCK=3;
		int startpage=(page-1)/BLOCK*BLOCK+1;
		int endpage=(page-1)/BLOCK*BLOCK+BLOCK;
		if(endpage>totalpage) endpage=totalpage;
		
		int i=0;
		JSONArray arr=new JSONArray();
		for(FoodVO vo:list) {
			JSONObject obj=new JSONObject();
			obj.put("fno", vo.getFno());
			obj.put("name", vo.getName());
			obj.put("score", vo.getScore());
			String poster=vo.getPoster();
			poster=poster.substring(0, poster.indexOf("^"));
			poster=poster.replace("#", "&");
			obj.put("poster", poster);
			
			if(i==0) {
				obj.put("curpage", page);
				obj.put("totalpage", totalpage);
				obj.put("startpage", startpage);
				obj.put("endpage", endpage);
			}
			arr.add(obj);
			i++;
		}
		return arr.toJSONString();
	}
	
	/*
	 * @GetMapping(value = "food/food_find_gu_vue.do", produces =
	 * "text/plain;charset=UTF-8") public String food_find_gu_vue(String page, int
	 * gu) { String[] guList = { "전체", "강서구", "양천구", "구로구", "마포구", "영등포구", "금천구",
	 * "은평구", "서대문구", "동작구", "관악구", "종로구", "중구", "용산구", "서초구", "강북구", "성북구", "도봉구",
	 * "동대문구", "성동구", "강남구", "노원구", "중랑구", "광진구", "송파구", "강동구" };
	 * 
	 * if(page==null) page="1"; int curpage=Integer.parseInt(page); int
	 * totalpage=dao.foodLocationTotalPage(guList[gu]); Map map=new HashMap();
	 * map.put("start", (curpage*20)-19); map.put("end", curpage*20);
	 * map.put("address", guList[gu]); List<FoodVO>
	 * list=dao.foodLocationFindData(map);
	 * 
	 * final int BLOCK=3; int startpage=(curpage-1)/BLOCK*BLOCK+1; int
	 * endpage=(curpage-1)/BLOCK*BLOCK+BLOCK; if(endpage>totalpage)
	 * endpage=totalpage;
	 * 
	 * int i=0; JSONArray arr=new JSONArray(); for(FoodVO vo:list) { JSONObject
	 * obj=new JSONObject(); obj.put("fno", vo.getFno()); obj.put("name",
	 * vo.getName()); obj.put("score", vo.getScore()); String poster=vo.getPoster();
	 * poster=poster.substring(0, poster.indexOf("^")); poster=poster.replace("#",
	 * "&"); obj.put("poster", poster);
	 * 
	 * if(i==0) { obj.put("curpage", curpage); obj.put("totalpage", totalpage);
	 * obj.put("startpage", startpage); obj.put("endpage", endpage); } arr.add(obj);
	 * i++; } return arr.toJSONString(); }
	 */
	
	@GetMapping(value = "food/food_location_detail_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_location_detail_vue(int fno) {
		FoodVO vo=dao.foodLocationDetailData(fno);
		JSONObject obj=new JSONObject();
		obj.put("fno", vo.getFno());
		obj.put("name", vo.getName());
		obj.put("score", vo.getScore());
		String address=vo.getAddress();
		String addr1=address.substring(0, address.lastIndexOf("지"));
		String addr2=address.substring(address.lastIndexOf("지")+3);
		obj.put("addr1", addr1.trim());
		obj.put("addr2", addr2.trim());
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
