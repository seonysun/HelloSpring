package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	//카테고리
	public List<CategoryVO> categoryListData(Map map){
		return mapper.categoryListData(map);
	}
	
	//맛집목록
	public CategoryVO categoryInfoData(int cno) {
		return mapper.categoryInfoData(cno);
	}
	public List<FoodVO> foodListData(int cno){
		return mapper.foodListData(cno);
	}
	
	//맛집상세
	public FoodVO foodDetailData(int fno) {
		return mapper.foodDetailData(fno);
	}
}
