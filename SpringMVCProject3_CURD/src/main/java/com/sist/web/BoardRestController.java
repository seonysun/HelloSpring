package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;

@RestController //@ResponseBody => Rest API
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	@RequestMapping(value = "board/update_ok.do", produces = "text/html;charset=UTF-8") 
												//JSON : text/plain
	public String board_update_ok(BoardVO vo) {
		String result="";
		boolean bCheck=dao.boardUpdate(vo);
		if(bCheck==true) {
			result="<script>"
					 +"location.href=\"detail.do?no="+vo.getNo()+"\""
					 +"</script>";
		} else {
			result="<script>"
					 +"alert(\"비밀번호가 틀립니다!!\");"
					 +"history.back();"
					 +"</script>";
		}
		return result;
	}
	
	@RequestMapping(value = "board/delete_ok.do", produces = "text/html;charset=UTF-8")
	public String board_delete(int no, String pwd) {
		String result="";
		boolean bCheck=dao.boardDelete(no, pwd);
		   if(bCheck==true) {
			   result="<script>"
					 +"location.href=\"list.do\""
					 +"</script>";
		   } else {
			   result="<script>"
					 +"alert(\"비밀번호가 틀립니다!!\");"
					 +"history.back();"
					 +"</script>";
		   }
		return result;
	}
}
