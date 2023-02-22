package com.sist.mapper;
import java.util.*;
import com.sist.vo.*;

import org.apache.ibatis.annotations.Select;

public interface SeoulMapper {
	
	@Select("SELECT no,title,poster,msg,num "
			+ "FROM (SELECT no,title,poster,msg,rownum as num "
			+ "FROM (SELECT no,title,poster,msg "
			+ "FROM seoul_location ORDER BY no)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<SeoulVO> seoulListData(Map map);
	
	@Select("SELECT * FROM seoul_location "
			+ "WHERE no=#{no}")
	public SeoulVO seoulDetailData(int no);
}
