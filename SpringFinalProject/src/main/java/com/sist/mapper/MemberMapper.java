package com.sist.mapper;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface MemberMapper {
	@Select("SELECT COUNT(*) FROM chat_member "
			+ "WHERE id=#{id}")
	public int memberIdCheck(String id);

	@Select("SELECT id,pwd,name FROM chat_member "
			+ "WHERE id=#{id}")
	public MemberVO memberLogin(String id);
}
