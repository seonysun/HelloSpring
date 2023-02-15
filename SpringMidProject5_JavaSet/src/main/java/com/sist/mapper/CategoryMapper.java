package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface CategoryMapper {
	@Select("SELECT cno,title,subject FROM project_food_category "
			+ "ORDER BY cno")
	public List<CategoryVO> categoryListData();
}
