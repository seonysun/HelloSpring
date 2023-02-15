package com.sist.mapper;
import java.util.*;
import com.sist.vo.*;

import org.apache.ibatis.annotations.Select;

public interface EmpMapper {
	@Select("SELECT empno,ename,job,sal,hiredate FROM emp")
	public List<EmpVO> empListData();
}
