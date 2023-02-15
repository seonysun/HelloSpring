package com.sist.dao;
import com.sist.mapper.*;

import lombok.Setter;

import java.util.*;

public class SeoulDAO {
	@Setter
	private SeoulMapper mapper;
	public List<SeoulVO> seoulListData(Map map) {
		return mapper.seoulListData(map);
	}
	public SeoulVO seoulDetailData(Map map) {
		return mapper.seoulDetailData(map);
	}
}
