package com.sist.mapper;
import com.sist.dao.*;
import java.util.*;

import org.apache.ibatis.annotations.Select;

public interface SeoulMapper {
	@Select("SELECT no,title,poster,num "
			+ "FROM (SELECT no,title,poster,rownum as num "
			+ "FROM (SELECT no,title,poster "
			+ "FROM seoul_location ORDER BY no)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<SeoulVO> seoulListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM seoul_location")
	public int seoulTotalPage();
}
