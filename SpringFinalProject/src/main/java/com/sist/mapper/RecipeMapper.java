package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface RecipeMapper {
	@Select("SELECT no,title,poster,chef,num "
			+ "FROM (SELECT no,title,poster,chef,rownum as num "
			+ "FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk)*/no,title,poster,chef "
			+ "FROM recipe)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> recipeListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) "
			+ "FROM recipe")
	public int recipeTotalPage();
	
	@Select("SELECT TO_CHAR(COUNT(*),'999,999') FROM recipe")
	public String recipeRowCount();
	
	@Select("SELECT COUNT(*) FROM recipeDetail "
			+ "WHERE no=#{no}")
	public int recipeDetailCount(int no);
	
	@Select("SELECT * FROM recipeDetail "
			+ "WHERE no=#{no}")
	public RecipeDetailVO recipeDetailData(int no);
	
	@Select("SELECT no,goods_name,goods_price,goods_poster,rownum "
			+ "FROM (SELECT no,goods_name,goods_price,goods_poster "
			+ "FROM goods_all "
			+ "WHERE REGEXP_LIKE(goods_name,#{goods_name}) "
			+ "ORDER BY TO_NUMBER(REPLACE(REPLACE(goods_price,',',''),'원',''))) "
			+ "WHERE rownum<=3")
	public List<GoodsVO> goodsListData(String goods_name);
}
