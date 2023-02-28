package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.sist.vo.*;

public interface ReplyMapper {
	@Select(value = "{CALL replyList(#{prno,mode=IN,javaType=java.lang.Integer},"
											//값을 첨부하는 경우 IN
			+ "#{ptype,mode=IN,javaType=java.lang.Integer},"
			+ "#{presult,mode=OUT,jdbcType=CURSOR,resultMap=replyMap})}")
						//값을 가져오는 경우 OUT
	@Options(statementType = StatementType.CALLABLE)
										//prepared : sql 문장 전송할 수 있는 상태, callable : 프로시저 호출할 수 있는 상태
	public List<ReplyVO> replyListData(Map map);
	
	@Select(value = "{CALL replyInsert(#{prno,mode=IN,javaType=java.lang.Integer},"
			+ "#{ptype,mode=IN,javaType=java.lang.Integer},"
			+ "#{pid,mode=IN,javaType=java.lang.String},"
			+ "#{pname,mode=IN,javaType=java.lang.String},"
			+ "#{pmsg,mode=IN,javaType=java.lang.String})}")
	@Options(statementType = StatementType.CALLABLE)
	public void replyInsert(Map map);
	
	@Select(value = "{CALL replyUpdate(#{pno,mode=IN,javaType=java.lang.Integer},"
			+ "#{pmsg,mode=IN,javaType=java.lang.String})}")
	@Options(statementType = StatementType.CALLABLE)
	public void replyUpdate(Map map);
	
	@Select(value = "{CALL replyDelete(#{pno,mode=IN,javaType=java.lang.Integer})}")
	@Options(statementType = StatementType.CALLABLE)
	public void replyDelete(Map map);
}
