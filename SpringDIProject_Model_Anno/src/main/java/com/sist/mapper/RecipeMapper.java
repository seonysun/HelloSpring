package com.sist.mapper;
import java.util.*;
import com.sist.vo.*;

import org.apache.ibatis.annotations.Select;

public interface RecipeMapper {
	@Select("SELECT rno,title,chef,rownum "
			+ "FROM (SELECT rno,title,chef FROM project_recipe ORDER BY rno) "
			+ "WHERE rownum<=30")
	public List<RecipeVO> recipeListData();
}
