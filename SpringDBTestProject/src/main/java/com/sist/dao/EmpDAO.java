package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
@Repository //DAO 메모리할당
public class EmpDAO {
	@Autowired //메모리할당 어노테이션
	private EmpMapper mapper; //Mapper(인터페이스) 메모리할당 -> 구현된 클래스값 받기
	
	public List<EmpVO> empListData(){
		return mapper.empListData();
	}
}
