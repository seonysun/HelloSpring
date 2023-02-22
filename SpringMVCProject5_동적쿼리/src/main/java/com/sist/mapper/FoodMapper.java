package com.sist.mapper;
import java.util.*;
import com.sist.vo.*;

import org.apache.ibatis.annotations.Select;

public interface FoodMapper {
	//카테고리
	@Select({
		"<script>"
			/* 동적 쿼리 : 조건에 따라 쿼리문 달라짐
			 * 	- <if> <choose> <forEach> <trim> <where> <set>
			 * 	- 태그 필요 -> script 필수
			 * */
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
	
	//맛집검색
	@Select({
		"<script>"
		+ "SELECT fno,name,poster,num "
		+ "FROM (SELECT fno,name,poster,rownum as num "
		+ "FROM (SELECT fno,name,poster "
		+ "FROM food_location "
		+ "<if test=\"ss!='all'\">"
		+ "WHERE address LIKE '%'||#{ss}||'%'"
		+ "</if>"
		+ "ORDER BY fno))"
		+ "WHERE num BETWEEN #{start} AND #{end}"
		+ "</script>"
	})
	public List<FoodVO> foodFindData(Map map);
}
