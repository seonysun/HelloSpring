package com.sist.anno;

import org.springframework.stereotype.Repository;

@Repository("oracle")
public class Oracle implements MyDAO{

	@Override
	public void connect() {
		System.out.println("오라클에 연결");
	}

}
