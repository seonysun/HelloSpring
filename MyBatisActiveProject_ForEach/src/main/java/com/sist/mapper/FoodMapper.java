package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.dao.*;

public interface FoodMapper {
	@Select("SELECT fno,name,poster,num "
			 +"FROM (SELECT fno,name,poster,rownum as num "
			 +"FROM (SELECT fno,name,poster "
			 +"FROM food_location ORDER BY fno ASC)) "
			 +"WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(Map map);
	  
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM food_location")
	public int foodTotalPage();
	 
	public List<FoodVO> foodFindData(Map map);
	public int foodFindCount(Map map);
}
