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
public class EmpRestController {
	@Autowired
	private EmpDAO dao;
	
	@GetMapping(value = "emp/list.do", produces = "text/plain;charset=UTF-8")
	public String emp_list() {
		List<EmpVO> list=dao.empListData();
		JSONArray arr=new JSONArray();
		for(EmpVO vo:list) {
			JSONObject obj=new JSONObject();
			obj.put("empno", vo.getEmpno());
			obj.put("ename", vo.getEname());
			obj.put("job", vo.getJob());
			obj.put("hiredate", vo.getDbday());
			obj.put("sal", vo.getSal());
			obj.put("dname", vo.getDvo().getDname());
			obj.put("loc", vo.getDvo().getLoc());
			arr.add(obj);
		}
		return arr.toJSONString();
	}
}
