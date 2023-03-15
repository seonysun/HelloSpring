package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.MemberMapper;
import com.sist.vo.MemberVO;

@Repository
public class MemberDAO {
	@Autowired
	private MemberMapper mapper;
	//채팅 로그인
	public MemberVO memberLogin(String id, String pwd) {
		MemberVO vo=new MemberVO();
		int count=mapper.memberIdCheck(id);
		if(count==0) {
			vo.setMsg("NOID");
		} else {
			MemberVO dbvo=mapper.memberLogin(id);
			if(dbvo.getPwd().equals(pwd)) {
				vo.setMsg("OK");
				vo.setId(dbvo.getId());
				vo.setName(dbvo.getName());
			} else {
				vo.setMsg("NOPWD");
			}
		}
		return vo;
	}
	
	//회원가입
	public int joinIdCheck(String id) {
		return mapper.joinIdCheck(id);
	}
	public void memberInsert(MemberVO vo) {
		mapper.memberInsert(vo);
	}
	
	//회원 로그인
	public MemberVO JoinLogin(String id, String pwd) {
		MemberVO vo=mapper.JoinLogin(id);
		return vo;
	}
}
