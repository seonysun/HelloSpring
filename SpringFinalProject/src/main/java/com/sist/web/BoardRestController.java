package com.sist.web;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping(value = "board/list_vue.do", produces = "text/plain;charset=UTF-8")
	public String board_list_vue(int page) {
		Map map=new HashMap();
		map.put("start", (page*10)-9);
		map.put("end", page*10);
		List<BoardVO> list=dao.boardListData(map);
		int totalpage=dao.boardTotalPage();
		JSONArray arr=new JSONArray();
		int i=0;
		for(BoardVO vo:list) {
			JSONObject obj=new JSONObject();
			obj.put("no", vo.getNo());
			obj.put("name", vo.getName());
			obj.put("subject", vo.getSubject());
			obj.put("dbday", vo.getDbday());
			obj.put("hit", vo.getHit());
			if(i==0) {
				obj.put("curpage", page);
				obj.put("totalpage", totalpage);
			}
			i++;
			arr.add(obj);
		}
		return arr.toJSONString();
	}
	
	@GetMapping("board/insert_vue.do")
	public String board_insert_vue(BoardVO vo) {
		dao.boardInsert(vo);
		return "";
	}
	
	@GetMapping(value = "board/detail_vue.do", produces = "text/plain;charset=UTF-8")
	public String board_detail_vue(int no) {
		BoardVO vo=dao.boardDetailData(no);
		JSONObject obj=new JSONObject();
		obj.put("no", vo.getNo());
		obj.put("name", vo.getName());
		obj.put("subject", vo.getSubject());
		obj.put("content", vo.getContent());
		obj.put("dbday", vo.getDbday());
		obj.put("hit", vo.getHit());
		return obj.toJSONString();
	}

	@GetMapping(value = "board/update_vue.do", produces = "text/plain;charset=UTF-8")
	public String board_update_vue(int no) {
		BoardVO vo=dao.boardDetailData(no);
		JSONObject obj=new JSONObject();
		obj.put("no", vo.getNo());
		obj.put("name", vo.getName());
		obj.put("subject", vo.getSubject());
		obj.put("content", vo.getContent());
		return obj.toJSONString();
	}
	
	@GetMapping("board/update_ok_vue.do")
	public String board_update_ok(BoardVO vo) {
		String res=dao.boardUpdate(vo);
		return res;
	}
	
	@GetMapping("board/delete_vue.do")
	public String board_delete(int no, String pwd) {
		String res=dao.boardDelete(no, pwd);
		return res;
	}
}
