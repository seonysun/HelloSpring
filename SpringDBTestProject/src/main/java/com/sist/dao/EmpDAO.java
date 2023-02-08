package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
@Repository //DAO 메모리할당
public class EmpDAO {
	@Autowired //Mapper(인터페이스) 메모리할당 -> 구현된 클래스값 받기
	private EmpMapper mapper;
	
	public List<EmpVO> empListData(){
		return mapper.empListData();
	}
}
