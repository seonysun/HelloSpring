package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
@Repository //DAO �޸��Ҵ�
public class EmpDAO {
	@Autowired //�޸��Ҵ� ������̼�
	private EmpMapper mapper; //Mapper(�������̽�) �޸��Ҵ� -> ������ Ŭ������ �ޱ�
	
	public List<EmpVO> empListData(){
		return mapper.empListData();
	}
}
