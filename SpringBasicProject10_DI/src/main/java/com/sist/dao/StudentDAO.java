package com.sist.dao;
import java.util.*;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class StudentDAO extends SqlSessionDaoSupport{
	//데이터 추가
	public void studentInsert(StudentVO vo) {
		getSqlSession().insert("studentInsert", vo);
	}
	//데이터 목록 보기
	public List<StudentVO> studentListData() {
		return getSqlSession().selectList("studentListData");
	}
	//데이터 상세 보기
	public StudentVO studentDetailData(int hakbun) {
		return getSqlSession().selectOne("studentDetailData", hakbun);
	}
	//데이터 수정
	public void studentUpdate(StudentVO vo) {
		getSqlSession().update("studentUpdate", vo);
	}
	//데이터 삭제
	public void studentDelete(int hakbun) {
		getSqlSession().delete("studentDelete", hakbun);
	}
}
