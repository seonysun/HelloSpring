package com.sist.dao;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyOrmDAO {
	@Autowired
	private ReplyMapper mapper;
	//replyListData(int rno, int type)
	public List<ReplyVO> replyListData(int rno, int type) {
		Map map=new HashMap();
		map.put("prno", rno);
		map.put("ptype", type);
		mapper.replyListData(map);
		return (List<ReplyVO>)map.get("presult");
			//map안에 메소드 구현에 필요한 매개변수와 presult 들어있음 -> get 함수로 호출 후 형변환
	}
	//replyInsert(ReplyVO vo)
	public void replyInsert(Map map) {
		mapper.replyInsert(map);
	}
	//replyUpdate(int no, String msg)
	public void replyUpdate(int no, String msg) {
		Map map=new HashMap();
		map.put("pno", no);
		map.put("pmsg", msg);
		mapper.replyUpdate(map);
	}
	//replyDelete(int no)
	public void replyDelete(int no) {
		Map map=new HashMap();
		map.put("pno", no);
		mapper.replyDelete(map);
	}
}
