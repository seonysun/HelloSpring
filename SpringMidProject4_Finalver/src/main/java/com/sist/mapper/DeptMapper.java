package com.sist.mapper;
import java.util.*;
import com.sist.vo.*;

import org.apache.ibatis.annotations.Select;

public interface DeptMapper {
	@Select("SELECT * FROM dept")
	public List<DeptVO> deptListData();
}
