package com.sist.dao;
import java.util.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpVO {
	private int empno,sal,mgr,deptno;
	private String ename,dname,job;
	private Date hiredate;
}
