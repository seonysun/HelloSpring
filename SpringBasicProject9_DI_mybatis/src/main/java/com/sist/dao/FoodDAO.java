package com.sist.dao;
import java.util.*;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class FoodDAO extends SqlSessionDaoSupport{
	public List<FoodVO> foodListData(){
		return getSqlSession().selectList("foodListData");
				//Connection 처리 자동으로 수행
	}
	public FoodVO foodDetailData(int fno){
		return getSqlSession().selectOne("foodDetailData", fno);
	}
}
