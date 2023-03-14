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
public class RecipeRestController {
	@Autowired
	private RecipeService service;
	
	@GetMapping(value = "recipe/recipe_list_vue.do", produces = "text/plain;charset=UTF-8")
	public String recipe_list_vue(int page) {
		Map map=new HashMap();
		map.put("start", (page*20)-19);
		map.put("end", page*20);
		List<RecipeVO> list=service.recipeListData(map);
		
		int totalpage=service.recipeTotalPage();
		String count=service.recipeRowCount();
		final int BLOCK=10;
		int startpage=(page-1)/BLOCK*BLOCK+1;
		int endpage=(page-1)/BLOCK*BLOCK+BLOCK;
		if(endpage>totalpage) endpage=totalpage;
		
		int i=0;
		JSONArray arr=new JSONArray();
		for(RecipeVO vo:list) {
			JSONObject obj=new JSONObject();
			obj.put("no", vo.getNo());
			obj.put("title", vo.getTitle());
			obj.put("poster", vo.getPoster());
			obj.put("chef", vo.getChef());
			
			if(i==0) {
				obj.put("curpage", page);
				obj.put("totalpage", totalpage);
				obj.put("startpage", startpage);
				obj.put("endpage", endpage);
				obj.put("count", count);
			}
			arr.add(obj);
			i++;
		}
		return arr.toJSONString();
	}

	@GetMapping(value = "recipe/chef_list_vue.do", produces = "text/plain;charset=UTF-8")
	public String chef_list_vue(int page) {
		Map map=new HashMap();
		map.put("start", (page*50)-49);
		map.put("end", page*50);
		List<ChefVO> list=service.chefListData(map);
		int totalpage=service.chefTotalPage();
		
		int i=0;
		JSONArray arr=new JSONArray();
		for(ChefVO vo:list) {
			JSONObject obj=new JSONObject();
			obj.put("chef", vo.getChef());
			obj.put("poster", vo.getPoster());
			obj.put("mc1", vo.getMem_cont1());
			obj.put("mc2", vo.getMem_cont2());
			obj.put("mc3", vo.getMem_cont3());
			obj.put("mc7", vo.getMem_cont7());
			
			if(i==0) {
				obj.put("curpage", page);
				obj.put("totalpage", totalpage);
			}
			arr.add(obj);
			i++;
		}
		return arr.toJSONString();
	}
	
	@GetMapping(value = "recipe/chef_made_vue.do", produces = "text/plain;charset=UTF-8")
	public String chef_made_vue(String chef) {
		List<RecipeVO> list=service.chefMadeRecipeData(chef);
		JSONArray arr=new JSONArray();
		for(RecipeVO vo:list) {
			JSONObject obj=new JSONObject();
			obj.put("no", vo.getNo());
			String title=vo.getTitle();
			if(title.length()>12)
				title=title.substring(0, 12)+"..";
			obj.put("title", title);
			obj.put("poster", vo.getPoster());
			obj.put("chef", vo.getChef());
			arr.add(obj);
		}
		return arr.toJSONString();
	}
	
	@GetMapping(value = "recipe/goods_price_vue.do", produces = "text/plain;charset=UTF-8")
	public String goods_price_vue(String goods_name) {
		String name=goods_name.replaceAll("[^가-힣]", "");
//		name=name.substring(0, name.indexOf(" "));
		List<GoodsVO> list=service.goodsListData(name);
		JSONArray arr=new JSONArray();
		for(GoodsVO vo:list) {
			JSONObject obj=new JSONObject();
			obj.put("no", vo.getNo());
			obj.put("goods_poster", vo.getGoods_poster());
			String gname=vo.getGoods_name();
			if(gname.length()>10) {
				gname=gname.substring(0, 10)+"..";
			}
			obj.put("goods_name", gname);
			obj.put("goods_price", vo.getGoods_price());
			arr.add(obj);
		}
		return arr.toJSONString();
	}
}
