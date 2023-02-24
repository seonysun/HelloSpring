package com.sist.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.dao.*;
import java.io.*;
@RestController
public class DataBoardRestController {
	@Autowired
    private DataBoardDAO dao;
	 
	@PostMapping("databoard/delete.do")
	public String databoard_delete(int no, String pwd) {
		String result="";
		try {
			DataBoardVO vo=dao.databoardFileInfoData(no);
			if(vo.getFilecount()>0) {
				String[] fn=vo.getFilename().split(",");
				for(String f:fn) {
					File file=new File("c:\\download\\"+f);
					file.delete();
				}
			}
		} catch(Exception ex) {}
		boolean bCheck=dao.databoardDelete(no, pwd);
		if(bCheck==true) result="yes";
		else result="no";
		return result;
	}
	
	@PostMapping("databoard/pwd_check.do")
	public String pwd_check(int no, String pwd) {
		String result="";
		boolean bCheck=dao.pwdCheck(no, pwd);
		if(bCheck==true) result="yes";
		else result="no";
		return result;
	}
}
