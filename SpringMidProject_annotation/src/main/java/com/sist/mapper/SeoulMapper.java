package com.sist.mapper;
import java.util.*;
import com.sist.dao.*;

public interface SeoulMapper {
	public List<SeoulLocationVO> seoulListData(Map map);
											//매개변수 무조건 1개 -> hashmap or VO로 통합
	public SeoulLocationVO seoulDetailData(Map map);
}
