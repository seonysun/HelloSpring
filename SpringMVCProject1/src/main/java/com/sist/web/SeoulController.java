package com.sist.web;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //브라우저와 연결
public class SeoulController {
	@Autowired
	private SeoulDAO dao;
	
	@RequestMapping("seoul/location.do")
	public String seoul_list(HttpServletRequest request, HttpServletResponse response) {
		String page=request.getParameter("page");
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int totalpage=dao.seoulTotalPage();
		Map map=new HashMap();
		int rowSize=20;
		int start=rowSize*(curpage-1)+1;
		int end=rowSize*curpage;
		map.put("start", start);
		map.put("end", end);
		List<SeoulVO> list=dao.seoulListData(map);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		return "seoul/location";
	}
}
