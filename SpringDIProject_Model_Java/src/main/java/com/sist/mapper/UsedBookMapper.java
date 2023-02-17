package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface UsedBookMapper {
	@Select("SELECT DISTINCT type FROM usedbook")
	public List<String> bookCategory();
	
	@Select("SELECT no,price,publisher,author,title,type,rownum "
			+ "FROM (SELECT no,price,publisher,author,title,type "
			+ "FROM usedbook "
			+ "ORDER BY no) "
			+ "WHERE type=#{type}")
	public List<UsedBookVO> bookCategoryListData(String type);
}
