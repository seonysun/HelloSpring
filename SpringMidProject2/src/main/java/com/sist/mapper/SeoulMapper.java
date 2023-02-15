package com.sist.mapper;
import java.util.*;
import com.sist.dao.*;

import org.apache.ibatis.annotations.Select;

public interface SeoulMapper {
	@Select("SELECT no,title,msg,address FROM ${seoul_tbl}")
	public List<SeoulVO> seoulListData(Map map);
	
	@Select("SELECT no,title,msg,address FROM ${seoul_tbl} "
			+ "WHERE no=#{no}")
	public SeoulVO seoulDetailData(Map map);
}
