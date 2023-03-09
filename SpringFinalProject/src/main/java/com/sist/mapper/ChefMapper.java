package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface ChefMapper {
	@Select("SELECT chef,poster,mem_cont1,mem_cont3,mem_cont7,mem_cont2,num "
			+ "FROM (SELECT chef,poster,mem_cont1,mem_cont3,mem_cont7,mem_cont2,rownum as num "
			+ "FROM (SELECT /*+ INDEX_ASC(chef chef_no_pk)*/chef,poster,mem_cont1,mem_cont3,mem_cont7,mem_cont2 "
			+ "FROM chef)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<ChefVO> chefListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/50.0) "
			+ "FROM chef")
	public int chefTotalPage();
	
	@Select("SELECT no,title,poster,chef,rownum "
			+ "FROM recipe "
			+ "WHERE chef=#{chef} "
			+ "AND rownum<=20")
	public List<RecipeVO> chefMadeRecipeData(String chef);
}
