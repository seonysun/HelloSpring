package com.sist.dao;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MusicDAO {
	@Autowired
	private MusicMapper mapper;
	public List<MusicVO> musicAllData(){
		return mapper.musicAllData();
	}
	public MusicVO musicDetailData(int no) {
		return mapper.musicDetailData(no);
	}
}
