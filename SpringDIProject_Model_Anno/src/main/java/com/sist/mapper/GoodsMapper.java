package com.sist.mapper;
import java.util.*;
import com.sist.vo.*;

import org.apache.ibatis.annotations.Select;

public interface GoodsMapper {
	@Select("SELECT no,name,price,rownum "
			+ "FROM (SELECT no,goods_name as name,goods_price as price "
			+ "FROM goods_all "
			+ "ORDER BY no) "
			+ "WHERE rownum<=30")
	public List<GoodsVO> goodsListData();
}
