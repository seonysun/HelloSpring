package com.sist.web;
import java.util.*;
import java.io.*;
import java.net.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
	String[] urls= {"","searchMainDailyBoxOffice.do","searchMainRealTicket.do","searchMainDailySeatTicket.do","searchMainOnlineDailyBoxOffice.do"};
	@GetMapping(value = "movie/movie_list_vue.do", produces = "text/plain;charset=UTF-8")
	public String movie_list(int no) {
		String strUrl="https://www.kobis.or.kr/kobis/business/main/"+urls[no];
		String json="";
		try {
			URL url=new URL(strUrl);
			HttpURLConnection conn=(HttpURLConnection)url.openConnection(); //사이트 연결
			if(conn!=null) {
				BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
				while(true) {
					String s=in.readLine();
					if(s==null) break;
					json+=s;
				}
			}
		} catch(Exception ex) {}
		return json.trim();
	}
}
