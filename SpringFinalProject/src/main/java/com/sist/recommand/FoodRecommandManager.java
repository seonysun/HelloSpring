package com.sist.recommand;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

@Component
public class FoodRecommandManager {
	public List<String> jsonParser(String json){
		List<String> list=new ArrayList<String>();
		try {
			JSONParser jp=new JSONParser();
			JSONObject root=(JSONObject)jp.parse(json);
			JSONArray arr=(JSONArray)root.get("items");
			for(int i=0;i<arr.size();i++) {
				JSONObject obj=(JSONObject)arr.get(i);
				String desc=(String)obj.get("description");
				list.add(desc);
			}
		} catch(Exception ex) {}
		return list;
	}
}
