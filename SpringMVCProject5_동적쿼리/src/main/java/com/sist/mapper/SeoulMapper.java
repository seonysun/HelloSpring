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
		//조건에 따라 조회되는 table이 변경됨 -> 동적쿼리문
	public int seoulTotalPage(Map map);
							/* 동적 쿼리에서 데이터 가져오는 방식 : parameterType에 해당하는 클래스에서 getter 메소드 이용
							 * 	- 기본 데이터 타입(int, long), Map의 경우 getter 보유
							 * 	- VO에 포함된 데이터의 경우 getter 만들어줌(lombok)
							 * 	- String의 경우 getter 미보유
							 * 		-> 매개변수 타입 String 설정 시 에러 
							 * 			: There is no getter for property named 'table' in 'class java.lang.String'
							 * */
	
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
