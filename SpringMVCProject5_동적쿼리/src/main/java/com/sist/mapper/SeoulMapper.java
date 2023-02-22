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
	public int seoulTotalPage(Map map);
							//parameterType을 String으로 받을 시 오류.. 왜????
							//controller에서 cate 널값 처리 후 String table로 넣었을 때 null값인 경우는 없음
	
	@Select("SELECT * FROM seoul_location "
			+ "WHERE no=#{no}")
	public SeoulVO seoulDetailData(int no);
	
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
