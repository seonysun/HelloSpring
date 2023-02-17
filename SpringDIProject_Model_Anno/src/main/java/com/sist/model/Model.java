package com.sist.model;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Model {
//	@Autowired
//	private FoodDAO fdao;
//	@Autowired
//	private RecipeDAO rdao;
//	@Autowired
//	private GoodsDAO gdao;
	@Autowired
	private Service service;
	public void recipeListData() {
		List<RecipeVO> list=service.recipeListData();
		for(RecipeVO vo:list) {
			System.out.println(vo.getRno()+"."+vo.getTitle()+"("+vo.getChef()+")");
		}
	}
	public void foodListData() {
		List<FoodVO> list=service.foodListData();
		for(FoodVO vo:list) {
			System.out.println(vo.getFno()+"."+vo.getName()+"("+vo.getType()+")");
		}
	}
	public void goodsListData() {
		List<GoodsVO> list=service.goodsListData();
		for(GoodsVO vo:list) {
			System.out.println(vo.getNo()+"."+vo.getName()+"("+vo.getPrice()+")");
		}
	}
}
