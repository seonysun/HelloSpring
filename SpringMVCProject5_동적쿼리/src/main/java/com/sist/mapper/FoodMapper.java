package com.sist.mapper;
import java.util.*;
import com.sist.vo.*;

import org.apache.ibatis.annotations.Select;

public interface FoodMapper {
	//카테고리
	@Select({
		"<script>"
		+ "SELECT cno,title,poster,subject "
		+ "FROM project_food_category "
		+ "WHERE "
		+ "<if test='cno==1'>"
		+ "cno BETWEEN 1 AND 12"
		+ "</if>"
		+ "<if test='cno==2'>"
		+ "cno BETWEEN 13 AND 18"
		+ "</if>"
		+ "<if test='cno==3'>"
		+ "cno BETWEEN 19 AND 30"
		+ "</if>"
		+ "</script>"
	})
	public List<CategoryVO> categoryListData(Map map);
	
	//맛집목록
	@Select("SELECT title,subject FROM project_food_category "
			+ "WHERE cno=#{cno}")
	public CategoryVO categoryInfoData(int cno);
	
	@Select("SELECT fno,name,poster,address,tel,type,score "
			+ "FROM project_food "
			+ "WHERE cno=#{cno}")
	public List<FoodVO> foodListData(int cno);
	
	//맛집상세
	@Select("SELECT * FROM project_food "
			+ "WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
}
