package com.sist.dao;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	public List<CategoryVO> categoryListData(){
		return mapper.categoryListData();
	}
	public List<FoodVO> foodListData(int cno){
		return mapper.foodListData(cno);
	}
	public CategoryVO categoryInfoData(int cno) {
		return mapper.categoryInfoData(cno);
	}
	public FoodVO foodDetailData(int fno) {
		return mapper.foodDetailData(fno);
	}
}
