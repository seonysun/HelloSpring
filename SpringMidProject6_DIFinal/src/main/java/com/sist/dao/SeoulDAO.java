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
	public List<SeoulVO> seoulListData(Map map){
		return mapper.seoulListData(map);
	}
}
