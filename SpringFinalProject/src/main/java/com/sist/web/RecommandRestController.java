package com.sist.web;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sist.vo.*;
import com.sist.dao.*;
import com.sist.recommand.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommandRestController {
	@Autowired
	private NaverRecommandData nrd;
	@Autowired
	private FoodRecommandManager mgr;
	@Autowired
	private FoodDAO dao;
	
	@GetMapping(value = "food/recommand_change_vue.do", produces = "text/plain;charset=UTF-8")
	public String recommand_change_vue(int no) {
		String[] strArr=null;
		if(no==1) strArr=new String[] {"출근길","퇴근길","휴식","공부","카페","휴가","여행","드라이브","산책","운동","집중","고백","공연"};
		else if(no==2) strArr=new String[] {"기분전환","외로움","슬픔","힘찬","이별","지침","설렘","위로","사랑","스트레스","짜증","그리움","추억","행복","불안","분노","기쁨","축하"};
		else if(no==3) strArr=new String[] {"밝은","신나는","편안한","따뜻한","그루브한","부드러운","로맨틱한","웅장한","매혹적인","잔잔한","달콤한","시원한","애절한"};
		else if(no==4) strArr=new String[] {"봄","여름","가을","겨울","맑은날","추운날","흐린날","비오는날","더운날","안개낀날","눈오는날"};
		JSONArray arr=new JSONArray();
		for(String s:strArr) 
			arr.add(s);
		return arr.toJSONString();
	}
	
	@GetMapping(value = "food/recommand_result_vue.do", produces = "text/plain;charset=UTF-8")
	public String recommand_result_vue(String ss) {
		String json=nrd.newsData(ss);
		List<String> list=mgr.jsonParser(json);
		
		List<String> nList=dao.foodGetNameData();
		Pattern[] p=new Pattern[nList.size()];
		int[] count=new int[nList.size()];
		for(int i=0;i<p.length;i++) {
			p[i]=Pattern.compile(nList.get(i)); //찾는 문자열
		}
		Matcher[] m=new Matcher[nList.size()];
		for(String s:list) {
			for(int i=0;i<m.length;i++) {
				m[i]=p[i].matcher(s);
				if(m[i].find()) { //블로그 중에 맛집명이 존재하면
					String data=m[i].group();
					count[i]++;
				}
			}
		}
		
		List<FoodVO> fList=new ArrayList<FoodVO>();
		for(int i=0;i<nList.size();i++) {
			String name=nList.get(i);
			if(count[i]>1) {
//				System.out.println(name+":"+count[i]);
				FoodVO vo=dao.foodInfoData(name);
				String poster=vo.getPoster();
				poster=poster.substring(0, poster.indexOf("^"));
				poster=poster.replace("#", "&");
				vo.setPoster(poster);
				fList.add(vo);
			}
		}
		
		JSONArray arr=new JSONArray();
		int i=0;
		for(FoodVO vo:fList) {
			JSONObject obj=new JSONObject();
			obj.put("fno", vo.getFno());
			obj.put("name", vo.getName());
			obj.put("score", vo.getScore());
			obj.put("poster", vo.getPoster());
			if(i==0) {
				obj.put("count", fList.size());
			}
			arr.add(obj);
			i++;
		}
		return arr.toJSONString();
	}
	
	@GetMapping(value = "food/recommand_detail_vue.do", produces = "text/plain;charset=UTF-8")
	public String recommand_detail_vue(int fno) {
		FoodVO vo=dao.foodLocationDetailData(fno);
		JSONObject obj=new JSONObject();
		obj.put("fno", vo.getFno());
		obj.put("name", vo.getName());
		obj.put("score", vo.getScore());
		String address=vo.getAddress();
		String addr1=address.substring(0, address.lastIndexOf("지"));
		String addr2=address.substring(address.lastIndexOf("지")+3);
		obj.put("addr1", addr1.trim());
		obj.put("addr2", addr2.trim());
		obj.put("tel", vo.getTel());
		obj.put("type", vo.getType());
		obj.put("price", vo.getPrice());
		obj.put("parking", vo.getParking());
		obj.put("time", vo.getTime());
		String menu=vo.getMenu();
	 	JSONArray arr1=new JSONArray();
	 	if(!menu.equals("no")) {
	 		String[] s=menu.split("원");
	 		for(String ss:s) {
	 			arr1.add(ss);
	 		}
	 	}
	 	String poster=vo.getPoster();
	 	JSONArray arr2=new JSONArray();
	 	StringTokenizer st=new StringTokenizer(poster,"^");
	 	while(st.hasMoreTokens()) {
	 		arr2.add(st.nextToken());
	 	}
	 	obj.put("menu", arr1);
	 	obj.put("poster", arr2);
		return obj.toJSONString();
	}
}
