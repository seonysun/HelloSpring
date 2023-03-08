package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	public List<CategoryVO> categoryListData(){
		return mapper.categoryListData();
	}
	public CategoryVO categoryInfoData(int cno) {
		return mapper.categoryInfoData(cno);
	}
	public List<FoodVO> foodListData(int cno){
		return mapper.foodListData(cno);
	}
	public FoodVO foodDetailData(int fno) {
		mapper.foodHitIncrement(fno);
		return mapper.foodDetailData(fno);
	}
	public FoodVO foodCookieData(int fno) {
		return mapper.foodDetailData(fno);
	}
	public List<FoodVO> foodLocationFindData(Map map){
		return mapper.foodLocationFindData(map);
	}
	public FoodVO foodLocationDetailData(int fno) {
		return mapper.foodLocationDetailData(fno);
	}
	public int foodLocationTotalPage(String address) {
		return mapper.foodLocationTotalPage(address);
	}
}
