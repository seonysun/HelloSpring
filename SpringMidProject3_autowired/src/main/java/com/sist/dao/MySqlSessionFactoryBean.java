package com.sist.dao;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/*
	<bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean"
		p:dataSource-ref="ds"
	/>
*/
@Component("ssf")
	//@Target(value={TYPE}) : 클래스 위에만 위치 가능 
public class MySqlSessionFactoryBean extends SqlSessionFactoryBean{
	@Autowired
		//@Target(value={CONSTRUCTOR, METHOD, PARAMETER, FIELD, ANNOTATION_TYPE}) : 메소드(setter) 위, 생성자 위, 멤버변수 위에 위치 가능 
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}
}
