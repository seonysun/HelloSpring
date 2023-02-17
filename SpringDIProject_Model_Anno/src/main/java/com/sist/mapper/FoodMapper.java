package com.sist.mapper;
import java.util.*;
import com.sist.vo.*;

import org.apache.ibatis.annotations.Select;

public interface FoodMapper {
	@Select("SELECT fno,name,type,rownum "
			+ "FROM (SELECT fno,name,type FROM food_location "
			+ "ORDER BY fno) "
			+ "WHERE rownum<=30")
	public List<FoodVO> foodListData();
}
