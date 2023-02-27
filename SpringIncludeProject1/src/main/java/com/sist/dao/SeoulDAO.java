package com.sist.dao;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SeoulDAO {
	@Autowired
	private SeoulMapper mapper;
	public List<SeoulVO> seoulLocationListData(Map map){
		return mapper.seoulLocationListData(map);
	}
	public int seoulTotalPage() {
		return mapper.seoulTotalPage();
	}
	public List<SeoulVO> seoulNatureListData(Map map){
		return mapper.seoulNatureListData(map);
	}
	public int natureTotalPage() {
		return mapper.natureTotalPage();
	}
	public List<SeoulVO> seoulShopListData(Map map){
		return mapper.seoulShopListData(map);
	}
	public int shopTotalPage() {
		return mapper.shopTotalPage();
	}
}
