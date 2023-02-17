package com.sist.model;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.UsedBookVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Model {
	@Autowired
	private UsedBookDAO udao;
	public void bookCategory() {
		List<String> list=udao.bookCategory();
		int i=1;
		for(String s:list) {
			System.out.println(i+"."+s);
			i++;
		}
	}
	public void bookListData(String type) {
		List<UsedBookVO> list=udao.bookCategoryListData(type);
		for(UsedBookVO vo:list) {
			System.out.println(vo.getNo()+"."+vo.getTitle()+"("+vo.getPrice()+")");
		}
	}
}
