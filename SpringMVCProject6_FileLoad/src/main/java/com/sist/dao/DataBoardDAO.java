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
	
	//삭제
	public DataBoardVO databoardFileInfoData(int no) {
		return mapper.databoardFileInfoData(no);
	}
	public boolean databoardDelete(int no, String pwd) {
		boolean bCheck=false;
		String db_pwd=mapper.databoardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			bCheck=true;
			mapper.databoardDelete(no);
		}
		return bCheck;
	}

	//수정 
	public DataBoardVO databoardUpdateData(int no) {
		return mapper.databoardDetailData(no);
	}
	public boolean pwdCheck(int no,String pwd) {
		boolean bCheck=false;
		String db_pwd=mapper.databoardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			bCheck=true;
		}
		return bCheck;
	}
	public void databoardUpdate(DataBoardVO vo) {
		mapper.databoardUpdate(vo);
	}
	
	//검색
	public List<DataBoardVO> databoardFindData(Map map) {
		return mapper.databoardFindData(map);
	}
	public int FindCount(Map map) {
	    return mapper.FindCount(map);
	}
}
