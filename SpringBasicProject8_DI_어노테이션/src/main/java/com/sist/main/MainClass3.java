package com.sist.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sist.model.*;
import com.sist.config.*;

public class MainClass3 {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext app=new AnnotationConfigApplicationContext(ModelConfig.class);
		AModel a=(AModel)app.getBean("am");
		a.board();
		BModel b=(BModel)app.getBean("BModel");
		b.member();
		CModel c=new CModel();
		c.reserve();
	}
}
