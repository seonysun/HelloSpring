package com.sist.model;
import java.util.*;
import com.sist.vo.*;

import lombok.Setter;

import com.sist.dao.*;

public class Model {
	@Setter
	private FoodDAO fdao;
	@Setter
	private GoodsDAO gdao;
	@Setter
	private RecipeDAO rdao;
	public void recipeListData() {
		List<RecipeVO> list=rdao.recipeListData();
		for(RecipeVO vo:list) {
			System.out.println(vo.getRno()+"."+vo.getTitle()+"("+vo.getChef()+")");
		}
	}
	public void foodListData() {
		List<FoodVO> list=fdao.foodListData();
		for(FoodVO vo:list) {
			System.out.println(vo.getFno()+"."+vo.getName()+"("+vo.getType()+")");
		}
	}
	public void goodsListData() {
		List<GoodsVO> list=gdao.goodsListData();
		for(GoodsVO vo:list) {
			System.out.println(vo.getNo()+"."+vo.getName()+"("+vo.getPrice()+")");
		}
	}
}
