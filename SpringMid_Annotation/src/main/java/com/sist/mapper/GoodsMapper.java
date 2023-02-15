package com.sist.mapper;
import java.util.*;
import com.sist.vo.*;

import org.apache.ibatis.annotations.Select;

public interface GoodsMapper {
	@Select("SELECT no,goods_name,goods_price,rownum "
			+ "FROM (SELECT no,goods_name,goods_price "
			+ "FROM ${goods_tbl} "
			+ "ORDER BY no) "
			+ "WHERE rownum<=30")
	public List<GoodsVO> goodsListData(Map map);
}
