package com.sist.anno;

import org.springframework.stereotype.Repository;

@Repository("mysql")
public class MySQL implements MyDAO{

	@Override
	public void connect() {
		System.out.println("MySQL에 연결");
	}

}
