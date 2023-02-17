package com.sist.dao;
import java.util.*;
import com.sist.vo.*;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class FoodDAO extends SqlSessionDaoSupport{
	public List<FoodVO> foodListData(){
		return getSqlSession().selectList("foodListData");
	}
}
