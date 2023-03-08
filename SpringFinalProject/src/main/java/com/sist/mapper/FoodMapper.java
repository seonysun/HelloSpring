package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
	
	@Update("UPDATE project_food "
			+ "SET hit=hit+1 "
			+ "WHERE fno=#{fno}")
	public void foodHitIncrement(int fno);
	
	@Select("SELECT * FROM project_food "
			+ "WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
	
	@Select("SELECT fno,name,poster,score,num "
			+ "FROM(SELECT fno,name,poster,score,rownum as num "
			+ "FROM(SELECT fno,name,poster,score "
			+ "FROM food_location "
			+ "WHERE address LIKE '%'||#{address}||'%' "
			+ "ORDER BY fno)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodLocationFindData(Map map);
	
	@Select("SELECT * FROM food_location "
			+ "WHERE fno=#{fno}")
	public FoodVO foodLocationDetailData(int fno);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) "
			+ "FROM food_location "
			+ "WHERE address LIKE '%'||#{address}||'%'")
	public int foodLocationTotalPage(String address);
}
