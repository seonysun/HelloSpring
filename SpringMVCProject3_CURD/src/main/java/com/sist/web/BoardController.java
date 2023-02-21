package com.sist.web;
import java.util.*;
import com.sist.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
/* 
 * @Controller : 화면 전환 (forward,redirect)
 * 				  -> 리턴형 jsp, *.do
 * @RestController : 데이터 전송 (문자열, json, javascript, ajax)
 * 					  -> vueJS, ajax, react 연결
 * @Component : 일반 클래스 연결 (Jsoup, Manager...(Open API))
 * @Repository : 데이터베이스 연결 
 * @Service : DAO 여러 개 통합해서 사용 (BI)
 */
@RequestMapping("board/") 
	//중복되는 경로 묶어두기
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	@RequestMapping("list.do")
	public String board_list(String page, Model model) {
							//매개변수 값 : dispatcherServlet이 getParameter 자동 처리 -> 한글 변환은 web.xml에서 처리
								//null값 가능성 있는 경우 String(ex.page), 나머지는 해당 데이터형 설정
								//매개변수 데이터형 오류 시 400 에러
							//Model : request 전송 객체
		//request : 쿠키, response : 파일 다운로드 => 그 외에는 dispatcherServlet과 Model이 자동 처리
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int totalpage=dao.boardTotalPage();
		Map map=new HashMap();
		int rowsize=10;
		int start=rowsize*(curpage-1)+1;
		int end=rowsize*curpage;
		map.put("start", start);
		map.put("end", end);
		List<BoardVO> list=dao.boardListData(map);
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		return "board/list";
	}
	
	@RequestMapping("detail.do")
	public String board_detail(int no, Model model) {
		BoardVO vo=dao.boardDetailData(no);
		model.addAttribute("vo", vo);
		return "board/detail";
	}
	
	@RequestMapping("insert.do")
	public String board_insert() {
		return "board/insert";
	}
	
	@RequestMapping("insert_ok.do")
	public String board_insert_ok(BoardVO vo) {
		dao.boardInsert(vo);
		return "redirect:list.do";
	}
	
	@RequestMapping("update.do")
	public String board_update(int no, Model model) {
		BoardVO vo=dao.boardUpdateData(no);
		model.addAttribute("vo", vo);
		return "board/update";
	}
	
	@RequestMapping("delete.do")
	public String board_delete(int no, Model model) {
		model.addAttribute("no", no);
		return "board/delete";
	}
	   
	@RequestMapping("find.do")
	public String board_find(String fs, String ss, Model model) {
		Map map=new HashMap();
		map.put("fs", fs);
		map.put("ss", ss);
		List<BoardVO> list=dao.boardFindData(map);
		int count=dao.boardFindCount(map);
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		return "board/find";
	}
}
