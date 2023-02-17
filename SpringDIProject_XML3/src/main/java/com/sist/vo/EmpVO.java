package com.sist.vo;
import java.util.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpVO {
//MyBatis 사용 시 변수명이 컬럼명과 동일하지 않으면 mapper에서 as로 따로 매칭 필요
	private int empno,sal,deptno,rank;
	private String ename,job;
	private Date regdate;
	private String dname,loc;
	private int grade;
}
