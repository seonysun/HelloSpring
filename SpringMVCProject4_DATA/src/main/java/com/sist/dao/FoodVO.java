package com.sist.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodVO {
	private int fno,cno,good,soso,bad,hit,like_count,jjim_count;
	private double score;
	private String poster,name,address,tel,type,price,parking,menu,time;
}
