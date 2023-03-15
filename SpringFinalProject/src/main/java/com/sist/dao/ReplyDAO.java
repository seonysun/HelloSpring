package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;

import org.springframework.stereotype.Repository;
/* 댓글 프로시저
 * 삭제
create or replace NONEDITIONABLE procedure replyDelete(
    pno spring_reply.no%type
)
is
begin
    delete from spring_reply
    where no=pno;
    commit;
end;

 * 등록
create or replace NONEDITIONABLE procedure replyInsert(
    prno spring_reply.rno%type,
    ptype spring_reply.type%type,
    pid spring_reply.id%type,
    pname spring_reply.name%type,
    pmsg spring_reply.msg%type
)
is
begin
    insert into spring_reply values(sr_no_seq.nextval,prno,ptype,pid,pname,pmsg,sysdate);
    commit;
end;

 * 목록
create or replace NONEDITIONABLE procedure replyList(
    prno spring_reply.rno%type,
    ptype spring_reply.type%type,
    presult out SYS_REFCURSOR
)
is
begin
    open presult for
        select no,rno,type,id,name,msg,regdate
        from spring_reply
        where rno=prno and type=ptype
        order by no desc;
end;

 * 수정
create or replace NONEDITIONABLE procedure replyUpdate(
    pno spring_reply.no%type,
    pmsg spring_reply.msg%type
)
is
begin
    update spring_reply
    set msg=pmsg
    where no=pno;
    commit;
end;
 */

import oracle.jdbc.OracleTypes;
@Repository
public class ReplyDAO {
	private Connection conn;
	private CallableStatement cs;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	public ReplyDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(Exception ex) {}
	}
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch(Exception ex) {}
	}
	public void disConnection() {
		try {
			if(cs!=null) cs.close();
			if(conn!=null) conn.close();
		} catch(Exception ex) {}
	}
	public List<ReplyVO> replyListData(ReplyVO vo){
		List<ReplyVO> list=new ArrayList<ReplyVO>();
		try {
			getConnection();
			String sql="{CALL replyList(?,?,?)";
			cs=conn.prepareCall(sql);
			cs.setInt(1, vo.getRno());
			cs.setInt(2, vo.getType());
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			cs.executeQuery();
			ResultSet rs=(ResultSet)cs.getObject(3);
			while(rs.next()) {
				ReplyVO rvo=new ReplyVO();
				rvo.setNo(rs.getInt(1));
				rvo.setRno(rs.getInt(2));
				rvo.setType(rs.getInt(3));
				rvo.setId(rs.getString(4));
				rvo.setName(rs.getString(5));
				rvo.setMsg(rs.getString(6));
				rvo.setRegdate(rs.getDate(7));
				list.add(rvo);
			}
			rs.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return list;
	}
	public void replyInsert(ReplyVO vo) {
		try {
			getConnection();
			String sql="{Call replyInsert(?,?,?,?,?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, vo.getRno());
			cs.setInt(2, vo.getType());
			cs.setString(3, vo.getId());
			cs.setString(4, vo.getName());
			cs.setString(5, vo.getMsg());
			cs.executeQuery();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
	}
	public void replyUpdate(int no, String msg) {
		try {
			getConnection();
			String sql="{Call replyUpdate(?,?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, no);
			cs.setString(2, msg);
			cs.executeQuery();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
	}
	public void replyDelete(int no) {
		try {
			getConnection();
			String sql="{Call replyDelete(?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, no);
			cs.executeQuery();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
	}
}
