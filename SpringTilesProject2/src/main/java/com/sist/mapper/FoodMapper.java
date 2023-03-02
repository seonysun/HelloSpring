package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface FoodMapper {
	@Select("SELECT cno,title,poster,subject "
			+ "FROM project_food_category")
	public List<CategoryVO> categoryListData();
	
	@Select("SELECT fno,cno,name,tel,address,type,poster,score "
			+ "FROM project_food "
			+ "WHERE cno=#{cno}")
	public List<FoodVO> foodListData(int cno);
	
	@Select("SELECT title,subject "
			+ "FROM project_food_category "
			+ "WHERE cno=#{cno}")
	public CategoryVO categoryInfoData(int cno);
	
	@Select("SELECT * FROM project_food "
			+ "WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
	
	@Select("SELECT fno,name,poster,num "
			+ "FROM (SELECT fno,name,poster,rownum as num "
			+ "FROM (SELECT fno,name,poster "
			+ "FROM food_location "
			+ "WHERE address LIKE '%'||#{addr}||'%' "
			+ "ORDER BY fno)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodSearchData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) "
			+ "FROM food_location "
			+ "WHERE address LIKE '%'||#{addr}||'%'")
	public int foodSearchTotalPage(Map map);
}
