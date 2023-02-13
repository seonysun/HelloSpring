package com.sist.di4;
import java.util.*;
import java.sql.*;

public class EmpDAO {
	private Connection conn;
	private PreparedStatement ps;
	/* DI
	 *  - 객체 간의 의존 관계를 객체 자신이 아닌 외부 조립기가 수행
	 *  - DI 패턴 방식
	 *  	1) 생성자(constructor-arg) : 초기화
	 *  	2) 메소드(property) : 변수값 대입(setter/getter), 자동 호출 메소드 등에서 사용
	 */
	//Setter(메소드) DI > XML을 통해 가져올 데이터 : 변수(getter/setter) -> p 태그로 등록
	private String url,user,pwd; 
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	//생성자 DI > XML을 통해 가져올 데이터 : 생성자 -> c 태그로 등록
	public EmpDAO(String driver) { 
		try {
			Class.forName(driver);
		} catch(Exception ex) {}
	}
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(url,user,pwd);
		} catch(Exception ex) {}
	}
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch(Exception ex) {}
	}
	
	public List<EmpVO> empListData(){
		List<EmpVO> list=new ArrayList<EmpVO>();
		try {
			getConnection();
			String sql="SELECT empno,ename,job,hiredate,sal "
					+ "FROM emp";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				EmpVO vo=new EmpVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				vo.setSal(rs.getInt(5));
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
	//메소드 DI > 자동 호출 메소드 설정 -> 생성 시 : init-method="메소드명", 소멸 시 : destroy-method="메소드명"
	public void init() {
		System.out.println("==========사원 목록==========");
	}
}
