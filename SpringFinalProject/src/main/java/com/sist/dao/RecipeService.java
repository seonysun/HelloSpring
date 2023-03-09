package com.sist.dao;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {
	@Autowired
	private RecipeMapper rMapper;
	@Autowired
	private ChefMapper cMapper;
	public List<RecipeVO> recipeListData(Map map){
		return rMapper.recipeListData(map);
	}
	public int recipeTotalPage() {
		return rMapper.recipeTotalPage();
	}
	public String recipeRowCount() {
		return rMapper.recipeRowCount();
	}
	public List<ChefVO> chefListData(Map map){
		return cMapper.chefListData(map);
	}
	public int chefTotalPage() {
		return cMapper.chefTotalPage();
	}
	public List<RecipeVO> chefMadeRecipeData(String chef){
		return cMapper.chefMadeRecipeData(chef);
	}
}
