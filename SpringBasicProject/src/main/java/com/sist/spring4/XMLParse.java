package com.sist.spring4;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;
public class XMLParse extends DefaultHandler{
    private Map map=new HashMap();
	public Map getMap() {
		return map;
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		try {
			if(qName.equals("bean")) { //bean 태그 읽기
				String id=attributes.getValue("id"); //속성값 가져오기
				String cls=attributes.getValue("class");
				Class className=Class.forName(cls); //클래스 정보 읽기
				Object obj=className.getDeclaredConstructor().newInstance(); //클래스 객체 생성
				map.put(id,obj); //id로 객체에 접근할 수 있도록 map에 저장
			}
		} catch(Exception ex) {}
	}
}
