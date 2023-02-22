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
	
	public List<JejuLocationVO> jejuLocationListData(Map map){
		return mapper.jejuLocationListData(map);
	}
	public int jejuTotalPage() {
		return mapper.jejuTotalPage();
	}
}
