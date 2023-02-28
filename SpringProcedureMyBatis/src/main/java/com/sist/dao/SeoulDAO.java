package com.sist.dao;
import java.util.*;
import com.sist.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SeoulDAO {
	@Autowired
	private SeoulMapper mapper;
	
	public List<SeoulVO> seoulListData(Map map){
		mapper.seoulListData(map);
		return (List<SeoulVO>)map.get("presult");
	}
	public SeoulVO seoulDetailData(Map map) {
		mapper.seoulDetailData(map);
		return (SeoulVO)map.get("presult");
	}
	public int seoulTotalPage() {
		return mapper.seoulTotalPage();
	}
}
