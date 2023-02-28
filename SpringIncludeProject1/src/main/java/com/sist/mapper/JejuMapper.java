package com.sist.mapper;
import java.util.*;
import com.sist.vo.*;

import org.apache.ibatis.annotations.Select;

public interface JejuMapper {
	@Select("SELECT no,title,poster,num "
			+ "FROM (SELECT no,title,poster,rownum as num "
			+ "FROM (SELECT no,title,poster "
			+ "FROM jejuLocation ORDER BY no)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<JejuLocationVO> jejuLocationListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM jejuLocation")
	
	public int jejuTotalPage();
	@Select("SELECT no,title,poster,num "
			+ "FROM (SELECT no,title,poster,rownum as num "
			+ "FROM (SELECT no,title,poster "
			+ "FROM jejuFood ORDER BY no)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<JejuFoodVO> jejuFoodListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM jejuFood")
	public int jejuFoodTotalPage();
	
	@Select("SELECT * FROM jejuFood "
			+ "WHERE no=#{no}")
	public JejuFoodVO jejuFoodDetailData(int no);
}
