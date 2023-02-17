package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.vo.*;
import com.sist.mapper.*;
@Service
public class JejuService {
	@Autowired
	private JejuFoodMapper fMapper;
	@Autowired
	private JejuLocationMapper lMapper;
	public List<JejuFoodVO> foodListData(){
		return fMapper.foodListData();
	}
	public List<JejuLocationVO> locationListData(){
		return lMapper.locationListData();
	}
}
