package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;

import oracle.jdbc.OracleTypes;

import org.springframework.stereotype.Repository;

@Repository
public class ReplyDAO {
	private Connection conn;
	private CallableStatement cs; //프로시저 호출 시 사용
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
	
	//댓글 추가
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
	//댓글 목록
	public List<ReplyVO> replyListData(int rno, int type) {
											//1 맛집 2 제주 3 서울
		List<ReplyVO> list=new ArrayList<ReplyVO>();
		try {
			getConnection();
			String sql="{Call replyList(?,?,?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, rno);
			cs.setInt(2, type);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			cs.executeQuery();
			ResultSet rs=(ResultSet)cs.getObject(3);
			while(rs.next()) {
				ReplyVO vo=new ReplyVO();
				vo.setNo(rs.getInt(1));
				vo.setRno(rs.getInt(2));
				vo.setType(rs.getInt(3));
				vo.setId(rs.getString(4));
				vo.setName(rs.getString(5));
				vo.setMsg(rs.getString(6));
				vo.setRegdate(rs.getDate(7));
				list.add(vo);
			}
			rs.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return list;
	}
	//댓글 수정
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
	//댓글 삭제
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
