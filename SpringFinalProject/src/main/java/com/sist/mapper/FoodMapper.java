package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface FoodMapper {
	@Select("SELECT cno,title,poster "
			+ "FROM project_food_category "
			+ "ORDER BY cno")
	public List<CategoryVO> categoryListData();
	
	@Select("SELECT title,subject "
			+ "FROM project_food_category "
			+ "WHERE cno=#{cno}")
	public CategoryVO categoryInfoData(int cno);

	@Select("SELECT fno,cno,name,tel,address,type,poster,score "
			+ "FROM project_food "
			+ "WHERE cno=#{cno}")
	public List<FoodVO> foodListData(int cno);
	
	@Select("SELECT * FROM project_food "
			+ "WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
}
