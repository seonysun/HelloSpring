package com.sist.manager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import java.util.*;
import com.sist.vo.*;

@Component("mm")
public class MovieManager {
    /*
    public static void main(String[] args) {
	    MovieManager m=new MovieManager();
	    List<MovieVO> list=m.movieListData(1);
	    for(MovieVO vo:list) {
	    	System.out.println(vo.getRank()+" "+vo.getTitle()+" "+vo.getGenre()+" "+vo.getDirector());
	    }
    }
    */
    private String[] strUrl={"","searchMainDailyBoxOffice.do","searchMainRealTicket.do",
    		"searchMainDailySeatTicket.do","searchMainOnlineDailyBoxOffice.do"};
    public List<MovieVO> movieListData(int no) {
	    List<MovieVO> list=new ArrayList<MovieVO>(); //[{},{},{}]
	    /*
	    try {
		    URL url=new URL("https://www.kobis.or.kr/kobis/business/main/"+strUrl[no]);
		    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
		    if(conn!=null) {
			    BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
			    while(true) {
				    String data=br.readLine();
				    if(data==null) break;
				    System.out.println(data);
			    }
		    }
	    } catch(Exception ex) {}
	    */
	    try {
		    Document doc=Jsoup.connect("https://www.kobis.or.kr/kobis/business/main/"+strUrl[no]).get();
		    String data=doc.toString();
		    data=data.substring(data.indexOf("["),data.lastIndexOf("]")+1);
		    data=data.replace("<", "");
		    data=data.replace(">", "");
		    System.out.println(data);
		   
		    JSONParser jp=new JSONParser();
		    JSONArray arr=(JSONArray)jp.parse(data);
			   /*
			    *   JSONArray : [] ArrayList
			    *   JSONObject : {} 객체
			    *   => [{},{},{},{},{}...]
			    * 	 
			    * 	["a","b","c"] : String, [{},{},{}] : JSONObject
			    */
		    for(int i=0;i<arr.size();i++) {
			    JSONObject obj=(JSONObject)arr.get(i);
			    MovieVO vo=new MovieVO();
			    String rank=String.valueOf((long)obj.get("rank"));
			    vo.setRank(Integer.parseInt(rank));
			    vo.setTitle((String)obj.get("movieNm"));
			    vo.setDirector((String)obj.get("director"));
			    vo.setGenre((String)obj.get("genre"));
			    list.add(vo);
		    }
	    } catch(Exception ex) {
		    ex.printStackTrace();
		}
	    return list;
    }
}
