package com.sist.dao;
import java.util.*;
import com.sist.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dao") //메모리 할당 <bean id="dao">
public class SeoulDAO {
	@Autowired //클래스 메모리 할당 시 주소값 자동 부여
	private SeoulMapper mapper;
	public List<SeoulVO> seoulListData(){
		return mapper.seoulListData();
	}
}
