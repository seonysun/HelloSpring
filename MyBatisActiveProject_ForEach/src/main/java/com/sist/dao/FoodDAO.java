package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;

@Repository
public class FoodDAO {
	@Autowired
    private FoodMapper mapper;
   
	public List<FoodVO> foodListData(Map map) {
	    return mapper.foodListData(map);
	}
	public int foodTotalPage() {
		return mapper.foodTotalPage();
	}
	  
	public List<FoodVO> foodFindData(Map map) {
		return mapper.foodFindData(map);
	}
	public int foodFindCount(Map map) {
		return mapper.foodFindCount(map);
	}
}
