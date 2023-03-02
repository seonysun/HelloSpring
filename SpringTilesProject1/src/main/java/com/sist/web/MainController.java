package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("main/main.do")
	public String main_page() {
		return "main";
	}

	@GetMapping("board/board.do")
	public String board_board() {
		return "board/board";
	}
	
	@GetMapping("notice/notice.do")
	public String notice_notice() {
		return "notice/notice";
	}
}
