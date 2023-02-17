package com.sist.dao;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sist.vo.RecipeVO;

public class RecipeDAO extends SqlSessionDaoSupport{
	public List<RecipeVO> recipeListData(){
		return getSqlSession().selectList("recipeListData");
	}
}
