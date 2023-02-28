package com.sist.dao;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JejuDAO {
	@Autowired
	private JejuMapper mapper;
	public List<JejuLocationVO> jejuLocationListData(Map map){
		return mapper.jejuLocationListData(map);
	}
	public int jejuTotalPage() {
		return mapper.jejuTotalPage();
	}
	public List<JejuFoodVO> jejuFoodListData(Map map){
		return mapper.jejuFoodListData(map);
	}
	public int jejuFoodTotalPage() {
		return mapper.jejuTotalPage();
	}
	public JejuFoodVO jejuFoodDetailData(int no) {
		return mapper.jejuFoodDetailData(no);
	}
}
