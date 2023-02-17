package com.sist.dao;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sist.vo.GoodsVO;

public class GoodsDAO extends SqlSessionDaoSupport{
	public List<GoodsVO> goodsListData(){
		return getSqlSession().selectList("goodsListData");
	}
}
