package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.dao.*;

/* 매개변수는 1개만 설정 가능 -> 여러 개일 때 VO, Map으로 통일
 * 	- VO : get 메소드 호출
 * 	- Map : key 호출 
 * */
public interface BoardMapper {
	//목록 출력
	@Select("SELECT no,name,subject,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,num "
			+ "FROM (SELECT no,name,subject,regdate,hit,rownum as num "
			+ "FROM (SELECT /*+ INDEX_DESC(spring_board sb_no_pk)*/no,name,subject,regdate,hit "
			+ "FROM spring_board)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM spring_board")
	public int boardTotalPage();
	
	//상세보기
	@Update("UPDATE spring_board "
			+ "SET hit=hit+1 " 
			+ "WHERE no=#{no}")
	public void hitIncrement(int no);
	
	@Select("SELECT no,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit "
			+ "FROM spring_board "
			+ "WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
	
	//데이터 추가 
	@Insert("INSERT INTO spring_board(no,name,subject,content,pwd) "
			+ "VALUES(sb_no_seq.nextval,#{name},#{subject},#{content},#{pwd})")
	public void boardInsert(BoardVO vo);
	
	//데이터 수정
	@Select("SELECT pwd FROM spring_board "
			+ "WHERE no=#{no}")
	public String boardGetPassword(int no);
	
	@Update("UPDATE spring_board "
			 +"SET name=#{name},subject=#{subject},content=#{content} "
			 +"WHERE no=#{no}")
	public void boardUpdate(BoardVO vo);
	
	//데이터 삭제
	@Delete("DELETE FROM spring_board "
			+ "WHERE no=#{no}")
	public void boardDelete(int no);
	
	//데이터 검색
	@Select("SELECT COUNT(*) FROM spring_board "
			+ "WHERE ${fs} LIKE '%'||#{ss}||'%'")
		//테이블명, 컬럼명은 입력값 그대로 입력되므로 ${}
		//검색어는 실제 데이터값(문자열, 숫자 등)으로 삽입('' 내에 삽입)되므로 #{}
	public int boardFindCount(Map map);
	
	@Select("SELECT no,name,subject,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit "
			+ "FROM spring_board "
			+ "WHERE ${fs} LIKE '%'||#{ss}||'%'")
	public List<BoardVO> boardFindData(Map map);
}
