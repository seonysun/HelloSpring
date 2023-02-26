package com.sist.mapper;
import java.util.*;
import com.sist.dao.*;

public interface EmpMapper {
	public List<String> empNameListData();
	public List<EmpVO> empInfoData(Map map);
}
