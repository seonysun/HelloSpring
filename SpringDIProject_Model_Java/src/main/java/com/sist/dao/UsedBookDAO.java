package com.sist.dao;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("bdao")
public class UsedBookDAO {
	@Autowired
	private UsedBookMapper mapper;
	public List<String> bookCategory(){
		return mapper.bookCategory();
	}
	public List<UsedBookVO> bookCategoryListData(String type){
		return mapper.bookCategoryListData(type);
	}
}
