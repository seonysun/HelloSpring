package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.SeoulVO;

public interface SeoulMapper {
	@Select("SELECT no,title,address FROM ${seoul_tbl} "
			+ "ORDER BY no")
	public List<SeoulVO> seoulListData(Map map);
}
