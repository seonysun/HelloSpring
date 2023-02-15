package com.sist.service;
import com.sist.mapper.*;
import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
	@Autowired	
		//private에도 접근 가능(리플렉션) -> Spring은 캡슐화에서 벗어난다는 말이 많음
	private FoodMapper fMapper;
	@Autowired
	private CategoryMapper cMapper;
	
	public List<CategoryVO> categoryListData() {
		return cMapper.categoryListData();
	}
	public List<FoodVO> foodListData(int cno){
		return fMapper.foodListData(cno);
	}
}
