package com.sist.mapper;
import java.util.*;
import com.sist.vo.*;

import org.apache.ibatis.annotations.Select;

public interface SeoulMapper {
	@Select("SELECT no,title,poster,msg,num "
			+ "FROM (SELECT no,title,poster,msg,rownum as num "
			+ "FROM (SELECT no,title,poster,msg "
			+ "FROM ${table} ORDER BY no)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<SeoulVO> seoulListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM ${table}")
	public int seoulTotalPage(String table);
	
	@Select("SELECT * FROM ${table} "
			+ "WHERE no=#{no}")
	public SeoulVO seoulDetailData(Map map);
	
	@Select("SELECT fno,name,poster,type,rownum "
			+ "FROM food_location "
			+ "WHERE address LIKE '%'||#{addr}||'%' "
			+ "AND rownum<=4")
	public List<FoodVO> foodListData(String addr);
	
	@Select("SELECT no,title,poster,msg,num "
			+ "FROM (SELECT no,title,poster,msg,rownum as num "
			+ "FROM (SELECT no,title,poster,msg "
			+ "FROM seoul_location "
			+ "WHERE address LIKE '%'||#{addr}||'%' "
			+ "ORDER BY no)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<SeoulVO> seoulFindData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) "
			+ "FROM seoul_location "
			+ "WHERE address LIKE '%'||#{addr}||'%'")
	public int seoulFindTotalPage(String addr);
}
