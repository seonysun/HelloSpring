package com.sist.dao;
import java.util.*;
import java.sql.*;

public class EmpDAO {
	/*
	 *    스프링에서 동작 
	 *    ----------- 객체의 생명주기 (생성~소멸)
	 *    1. 생성 (객체) Class.forName("등록된 클래스명") <bean id="dao" class="com.sist.dao.EmpDAO">
	 *                                                   --------- 구분자 (EmpDAO를 찾기 위한 데이터) 
	 *                                                   map.put(id,생성된 객체) => id가 중복되면 오류 발생 
	 *    2. setter DI => 객체생성에 필요한 데이터 주입 
	 *    3. init-method를 호출 
	 *    4. 프로그래머가 활용 =============================
	 *    5. destory-method ==> 메모리 해제(소멸)
	 */
	// 주입을 받는 경우 => 데이터베이스 정보 => 스프링에 등록된 DAO만 정보를 가지고 있다  
	/*
	 *   컨테이너의 기능 (클래스 관리영역)
	 *   -------------------------- ApplicationContext
	 *   DL => lookup(클래스 찾기 기능) => getBean(id)
	 *   DI => injection => 생성 필요한 데이터를 주입 
	 *           |
	 *          1. setter DI : setXxx()=> 값을 주입 
	 *          2. constructor DI : 생성자 매개변수를 통해서 주입 
	 *          3. method DI : init-method , destory-method
	 *                         -----------   --------------
	 *                         객체 생성시 호출      객체 소멸시 호출 
	 *          BeanFactory : DI (core)
	 *              |
	 *         ApplicationContext : AOP => AnnotationConfigApplicationContext(자바로 환경설정이된 경우)
	 *              |
	 *        WebApplicationContext : MVC
	 *   *** 5page => 최신의 스프링은 자바기반의 환경설정을 많이 사용하고 있다 
	 *       ======> 환경설정 파일 : 생성 , 주입 , 클래스간의 연결 관계설정 
	 *                                      ------------------ XML,자바 => 스프링에서 읽어서 설정 
	 *                                      ------------------ XML은 반드시 스프링에서 지원하는 태그와 속성만 사용이 가능 
	 *      객체 생성 
	 *      -------
	 *      1. 클래스 한개씩 메모리 할당 <bean>
	 *      2. 패키지 단위로 메모리 할당 (********) <context:component-scan> : 클래스를 구분(어노테이션) @Controller
	 *      3. 사용자 정의 클래스 , 라이브러리 클래스(마이바티스,JPA) cos 
	 *         -------------  ------------
	 *            |               |
	 *          어노테이션            <bean>
	 */
	private Connection conn;
	private PreparedStatement ps;
	private String url,user,password;
	public void setUrl(String url) {
		this.url = url;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public EmpDAO(String driver) {
		try {
			Class.forName(driver);
		} catch(Exception ex) {}
	}
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(url,user,password);
		} catch(Exception ex) {}
	}
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch(Exception ex) {}
	}
	
	public List<EmpVO> empListData() {
		List<EmpVO> list=new ArrayList<EmpVO>();
		try {
			getConnection();
			String sql="SELECT empno,ename,job FROM emp";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				EmpVO vo=new EmpVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
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
