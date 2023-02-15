package com.sist.service;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpDeptService {
	@Autowired
	private EmpMapper eMapper;
	@Autowired
	private DeptMapper dMapper;
	
	public List<EmpVO> empListData(){
		return eMapper.empListData();
	}
	public List<DeptVO> deptListData(){
		return dMapper.deptListData();
	}
}
