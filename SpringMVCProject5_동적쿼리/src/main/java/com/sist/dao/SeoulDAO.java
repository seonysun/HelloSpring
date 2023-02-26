package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class SeoulDAO {
	@Autowired
	private SeoulMapper mapper;
	//서울 목록
	public List<SeoulVO> seoulListData(Map map){
		return mapper.seoulListData(map);
	}
	public int seoulTotalPage(Map map) {
		return mapper.seoulTotalPage(map);
	}
	
	//서울 상세
	public SeoulVO seoulDetailData(Map map) {
		return mapper.seoulDetailData(map);
	}
	public List<FoodVO> foodListData(String addr){
		return mapper.foodListData(addr);
	}
	
	//서울 검색
	public List<SeoulVO> seoulFindData(Map map){
		return mapper.seoulFindData(map);
	}
	public int seoulFindTotalPage(String addr) {
		return mapper.seoulFindTotalPage(addr);
	}
}
