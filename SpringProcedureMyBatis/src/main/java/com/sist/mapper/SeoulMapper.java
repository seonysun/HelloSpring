package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.sist.dao.*;

public interface SeoulMapper {
	@Select("{CALL seoulLocationListData(#{pstart,mode=IN,javaType=java.lang.Integer},"
			+ "#{pend,mode=IN,javaType=java.lang.Integer},"
			+ "#{presult,mode=OUT,jdbcType=CURSOR,resultMap=seoulMap})}")
	@Options(statementType = StatementType.CALLABLE)
	public List<SeoulVO> seoulListData(Map map);
	
	@Select("{CALL seoulLocationDetailData(#{pno,mode=IN,javaType=java.lang.Integer},"
			+ "#{presult,mode=OUT,jdbcType=CURSOR,resultMap=seoulMap})}")
	@Options(statementType = StatementType.CALLABLE)
	public SeoulVO seoulDetailData(Map map);
	
	/*
	@Select("{CALL seoulLocationTotalPage(#{ptotal,mode=OUT,javaType=java.lang.Integer})}")
	@Options(statementType = StatementType.CALLABLE)
	public int seoulTotalPage(Map map);
	 */
	public Integer seoulTotalPage(); //xml 처리
}
