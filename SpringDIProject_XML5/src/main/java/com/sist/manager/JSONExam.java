package com.sist.manager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONExam {

	public static void main(String[] args) {
		/* 데이터의 종류 
		 *  - 정형화 : 필요한 데이터만 모아서 관리 (구분된 데이터)
		 *  	-> RDBMS (오라클)
		 *  - 반정형화 : 데이터 구분
		 *  	-> HTML,XML,JSON(JavaScript Object Notation)
		 *  - 비정형화 : 일반 텍스트 (구분되지 않은 데이터)
		 *  	-> 형태소 분석 (채팅, SNS) 
		 *  - 비정형화 데이터를 분석, 통계 ==> 정형화된 데이터를 만든다 => 교육 
		 *                        빅데이터                머신러닝 
		 */
        String json="[{\"name\":\"홍길동1\",\"sex\":\"남자\",\"age\":25,\"address\":\"서울\",\"tel\":\"1111-1111\"},"
        		    +"{\"name\":\"홍길동2\",\"sex\":\"남자\",\"age\":25,\"address\":\"서울\",\"tel\":\"2222-2222\"},"
        		    +"{\"name\":\"홍길동3\",\"sex\":\"남자\",\"age\":25,\"address\":\"서울\",\"tel\":\"3333-3333\"}]";

        try {
        	JSONParser jp=new JSONParser();
        	JSONArray arr=(JSONArray)jp.parse(json); //arrayList
        	System.out.println(arr.toJSONString());
        	for(int i=0;i<arr.size();i++) {
        		JSONObject obj=(JSONObject)arr.get(i); // vo
        		System.out.println("이름:"+obj.get("name"));
        		System.out.println("성별:"+obj.get("sex"));
        		System.out.println("나이:"+obj.get("age"));
        		System.out.println("주소:"+obj.get("address"));
        		System.out.println("전화:"+obj.get("tel"));
        		System.out.println();
        	}
        } catch(Exception ex) {}
	}
	
}
