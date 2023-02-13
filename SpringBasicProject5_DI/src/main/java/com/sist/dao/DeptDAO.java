package com.sist.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sist.commons.DataSource;

public class DeptDAO {
	private Connection conn;
	private PreparedStatement ps;
	private DataSource ds;
	public DeptDAO(DataSource ds) {
		this.ds=ds;
		try {
			Class.forName(ds.getDriver());
		} catch(Exception ex) {}
	}
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(ds.getUrl(), ds.getUser(), ds.getPassword());
		} catch(Exception ex) {}
	}
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch(Exception ex) {}
	}
	public List<DeptVO> deptListData(){
		List<DeptVO> list=new ArrayList<DeptVO>();
		try {
			getConnection();
			String sql="SELECT deptno,dname,loc FROM dept";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				DeptVO vo=new DeptVO();
				vo.setDeptno(rs.getInt(1));
				vo.setDname(rs.getString(2));
				vo.setLoc(rs.getString(3));
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
}
