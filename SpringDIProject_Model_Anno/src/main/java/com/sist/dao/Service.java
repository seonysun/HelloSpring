package com.sist.dao;
import com.sist.mapper.*;
import com.sist.vo.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service {
	@Autowired
	private FoodMapper fmapper;
	@Autowired
	private RecipeMapper rmapper;
	@Autowired
	private GoodsMapper gmapper;
	public List<FoodVO> foodListData(){
		return fmapper.foodListData();
	}
	public List<RecipeVO> recipeListData(){
		return rmapper.recipeListData();
	}
	public List<GoodsVO> goodsListData(){
		return gmapper.goodsListData();
	}
}
