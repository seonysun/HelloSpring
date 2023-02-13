package com.sist.main;
import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class XMLParse {
	private Map map=new HashMap();
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
		try {
			if(qName.equals("bean")) {
				String id=attributes.getValue("id");
				String cls=attributes.getValue("class");
				String sabun=attributes.getValue("p:sabun");
				String name=attributes.getValue("p:name");
				String job=attributes.getValue("p:job");
				String dept=attributes.getValue("p:dept");
				Class clsName=Class.forName(cls);
				Object obj=clsName.getDeclaredConstructor().newInstance();
				
				Method[] methods=clsName.getDeclaredMethods();
				for(Method m:methods) {
					String mName=m.getName();
				}
			}
		} catch(Exception ex) {}
	}
}
