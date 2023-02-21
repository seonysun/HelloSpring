package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.dao.*;

public interface FoodMapper {
	@Select("SELECT cno,title,poster,subject "
			+ "FROM project_food_category "
			+ "WHERE cno BETWEEN #{start} AND #{end}")
	public List<CategoryVO> categoryListData(Map map);
	
	@Select("SELECT title,subject FROM project_food_category "
			+ "WHERE cno=#{cno}")
	public CategoryVO categoryInfoData(int cno);
	
	@Select("SELECT fno,name,poster,address,tel,type,score "
			+ "FROM project_food "
			+ "WHERE cno=#{cno}")
	public List<FoodVO> foodListData(int cno);
}
