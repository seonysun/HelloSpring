package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.dao.*;

public interface RecipeMapper {
	@Select("SELECT title,chef,poster,rownum "
			+ "FROM project_recipe "
			+ "WHERE rownum<=40 "
			+ "AND REGEXP_LIKE(title, #{menu})")
	//select * from emp where ename in('a','b','c')
	//select * from emp where regexp_like(ename,'a|b|c')
	public List<RecipeVO> recipeFindData(String menu);
}
