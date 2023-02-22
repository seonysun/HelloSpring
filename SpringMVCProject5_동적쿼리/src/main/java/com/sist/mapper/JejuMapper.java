package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface JejuMapper {
	//제주명소목록
	@Select("SELECT no,title,poster,price,num "
			+ "FROM (SELECT no,title,poster,price,rownum as num "
			+ "FROM (SELECT no,title,poster,price "
			+ "FROM jejuLocation ORDER BY no)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<JejuLocationVO> jejuLocationListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM jejuLocation")
	public int jejuTotalPage();
	
	//제주명소상세
	@Select("SELECT * FROM jejuLocation "
			+ "WHERE no=#{no}")
	public JejuLocationVO jejuDetailData(int no);
	
	//제주맛집목록
	@Select("SELECT no,title,poster,score,rownum "
			+ "FROM jejuFood "
			+ "WHERE rownum<=4 "
			+ "AND addr2 LIKE '%'||#{addr}||'%'")
	public List<JejuFoodVO> jejuFoodData(Map map);
}
