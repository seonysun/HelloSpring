package com.sist.dao;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FooterDAO {
	@Autowired
	private FooterMapper mapper;
	public List<NoticeVO> noticeDataTop5(){
		return mapper.noticeDataTop5();
	}
	public List<DataBoardVO> boardDataTop5(){
		return mapper.boardDataTop5();
	}
}
