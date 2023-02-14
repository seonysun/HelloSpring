package com.sist.main;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.model.*;

public class MainClass2 {
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app2.xml");
		AModel a=(AModel)app.getBean("am");
							//클래스에 어노테이션을 줄 경우 어노테이션 옆에 ID명 제시
		a.board();
		BModel b=(BModel)app.getBean("BModel");
							//ID명 제시하지 않을 경우 자동 지정 -> class명과 동일
		b.member();
		CModel c=new CModel();
				//어노테이션 없으므로 XML에서 파싱되지 않음 -> 직접 메모리 할당 필요
		c.reserve();
	}
}
