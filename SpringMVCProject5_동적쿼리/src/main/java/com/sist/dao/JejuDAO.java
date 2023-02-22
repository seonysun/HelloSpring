package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class JejuDAO {
	@Autowired
	private JejuMapper mapper;
	//제주명소목록
	public List<JejuLocationVO> jejuLocationListData(Map map){
		return mapper.jejuLocationListData(map);
	}
	public int jejuTotalPage() {
		return mapper.jejuTotalPage();
	}
	
	//제주명소상세
	public JejuLocationVO jejuDetailData(int no) {
		return mapper.jejuDetailData(no);
	}
	
	//제주맛집목록
	public List<JejuFoodVO> jejuFoodData(Map map){
		return mapper.jejuFoodData(map);
	}
}
