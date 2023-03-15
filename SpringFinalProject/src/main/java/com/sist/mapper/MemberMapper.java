package com.sist.mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface MemberMapper {
	//채팅 로그인
	@Select("SELECT COUNT(*) FROM chat_member "
			+ "WHERE id=#{id}")
	public int memberIdCheck(String id);

	@Select("SELECT id,pwd,name FROM chat_member "
			+ "WHERE id=#{id}")
	public MemberVO memberLogin(String id);

	//회원가입
	@Select("SELECT COUNT(*) FROM spring_join "
			+ "WHERE id=#{id}")
	public int joinIdCheck(String id);
	
	@Insert("INSERT INTO spring_join "
			+ "VALUES(#{id},#{pwd},#{name},'n')")
	public void memberInsert(MemberVO vo);
	
	//회원 로그인
	@Select("SELECT id,pwd,name,admin FROM spring_join "
			+ "WHERE id=#{id}")
	public MemberVO JoinLogin(String id);
}
