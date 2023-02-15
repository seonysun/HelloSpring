package com.sist.mapper;
import java.util.*;
import com.sist.dao.*;

import org.apache.ibatis.annotations.Select;

public interface SeoulMapper {
	@Select("SELECT no,title,address FROM seoul_location")
	public List<SeoulVO> seoulListData();
}
