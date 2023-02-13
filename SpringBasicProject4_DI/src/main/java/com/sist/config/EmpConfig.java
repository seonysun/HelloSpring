package com.sist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sist.dao.EmpDAO;

//환경 설정 파일 : XML 대신 사용
@Configuration
public class EmpConfig {
	@Bean("dao")
	public EmpDAO empDao() {
		EmpDAO dao=new EmpDAO("oracle.jdbc.driver.OracleDriver");
		dao.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dao.setUser("hr");
		dao.setPwd("happy");
		return dao;
	}
}
