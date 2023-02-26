package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.sist.dao.*;

public interface DataBoardMapper {
	//목록 -> 페이징(인라인뷰)
	@Select("SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,filecount,num "
			+ "FROM (SELECT no,subject,name,regdate,hit,filecount,rownum as num "
			+ "FROM (SELECT /*+ INDEX_DESC(spring_databoard sd_no_pk)*/no,subject,name,regdate,hit,filecount "
			+ "FROM spring_databoard)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<DataBoardVO> databoardListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM spring_databoard")
	public int databoardTotalPage();
	
	//데이터 추가
	@SelectKey(keyProperty = "no", resultType = int.class, before = true, 
			statement = "SELECT NVL(MAX(no)+1, 1) as no FROM spring_databoard")
		/* @SelectKey : SQL 수행 없이 자동 생성키 바로 사용
		 * 				-> Sequence 대체(시퀀스는 데이터를 지워도 초기화되지 않으므로 @SelectKey 데이터 사용)
		 * 		- keyProperty : @SelectKey 결과가 셋팅될 속성
		 * 		- order
		 * 			- before : @SelectKey 실행한 뒤 insert 실행
		 * 			- after : insert 실행한 뒤 @SelectKey 실행
		 */
	
	@Insert("INSERT INTO spring_databoard(no,name,subject,content,pwd,filename,filesize,filecount) "
			+ "VALUES(#{no},#{name},#{subject},#{content},#{pwd},#{filename},#{filesize},#{filecount})")
	public void databoardInsert(DataBoardVO vo);
	
	//상세보기 -> 다운로드
	@Update("UPDATE spring_databoard "
			+ "SET hit=hit+1 "
			+ "WHERE no=#{no}")
	public void databoardView(int no);
	
	@Select("SELECT no,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,filename,filesize,filecount "
			+ "FROM spring_databoard "
			+ "WHERE no=#{no}")
	public DataBoardVO databoardDetailData(int no);
	
	//데이터 삭제
	@Select("SELECT filename,filesize,filecount "
			+ "FROM spring_databoard "
			+ "WHERE no=#{no}")
	public DataBoardVO databoardFileInfoData(int no);
	
	@Select("SELECT pwd FROM spring_databoard "
			+ "WHERE no=#{no}")
	public String databoardGetPassword(int no);
	
	@Delete("DELETE FROM spring_databoard "
			+ "WHERE no=#{no}")
	public void databoardDelete(int no);
	   
	//데이터 수정
	@Update("UPDATE spring_databoard "
		   +"SET name=#{name},subject=#{subject},content=#{content} "
		   +"WHERE no=#{no}")
	public void databoardUpdate(DataBoardVO vo);
	
	//데이터 검색
//	<select id="databoardFindData" resultType="DataBoardVO" parameterType="hashmap">
	public List<DataBoardVO> databoardFindData(Map map);
	@Select({
		"<script>"
		+ "SELECT COUNT(*) FROM spring_databoard "
		+"WHERE "
		+"<trim prefixOverrides=\"OR\">"
		+"<foreach collection=\"fsArr\" item=\"fd\">"
        +"<trim prefix=\"OR\">"
        +"<choose>"
        +"<when test=\"fd=='N'.toString()\">"
        +"name LIKE '%'||#{ss}||'%'"
        +"</when>"
        +"<when test=\"fd=='S'.toString()\">"
        +"subject LIKE '%'||#{ss}||'%'"
        +"</when>"
        +"<when test=\"fd=='C'.toString()\">"
        +"content LIKE '%'||#{ss}||'%'"
        +"</when>"
        +"</choose>"
        +"</trim>"
        +"</foreach>"
        +"</trim>"
        +"</script>"})
	public int FindCount(Map map);
}
