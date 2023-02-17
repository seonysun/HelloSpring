package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface JejuFoodMapper {
	@Select("SELECT no,title,addr,score,rownum FROM jejufood "
			+ "ORDER BY no")
	public List<JejuFoodVO> foodListData();
}
