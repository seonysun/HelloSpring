package com.sist.dao;
import java.util.*;
import com.sist.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataBoardDAO {
	@Autowired
	private DataBoardMapper mapper;
	
	//목록
	public List<DataBoardVO> databoardListData(Map map){
		return mapper.databoardListData(map);
	}
	public int databoardTotalPage() {
		return mapper.databoardTotalPage();
	}
	
	//추가
	public void databoardInsert(DataBoardVO vo) {
		mapper.databoardInsert(vo);
	}
	
	//상세
	public DataBoardVO databoardDetailData(int no) {
		mapper.databoardView(no);
		return mapper.databoardDetailData(no);
	}
}
