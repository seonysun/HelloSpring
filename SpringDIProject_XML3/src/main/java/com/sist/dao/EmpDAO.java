package com.sist.dao;
import java.util.*;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sist.vo.*;

public class EmpDAO extends SqlSessionDaoSupport{
	public List<EmpVO> empListData() {
		return getSqlSession().selectList("empListData");
	}
}
