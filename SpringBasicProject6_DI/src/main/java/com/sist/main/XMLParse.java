package com.sist.main;
import java.lang.reflect.Method;
import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParse extends DefaultHandler{
    private Map map=new HashMap();
	public Map getMap() {
		return map;
	}
	/*
	 *   XML => JAXB / JAXP
	 *                 ==== DOM/SAX(프레임워크) => Spring/MyBatis
	 *                          ------------
	 *                          <?XML version="1.0" encoding="UTF-8"> => startDocument() => 자동 
	 *                          <beans> => startElement()
	 *                            <bean id="" class=""/> => startElement() , endElement()
	 *                            <bean id="" class=""/> => startElement() , endElement()
	 *                          </beans> =>  endElement()
	 *                          endDocument()
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		try {
			if(qName.equals("bean")) {
				String id=attributes.getValue("id");
				String cls=attributes.getValue("class");
				String sabun=attributes.getValue("p:sabun");
				String name=attributes.getValue("p:name");
				String job=attributes.getValue("p:job");
				String dept=attributes.getValue("p:dept");
				
				String s=attributes.getQName(3);
				System.out.println(s);
				Class clsName=Class.forName(cls);
				Object obj=clsName.getDeclaredConstructor().newInstance();
				System.out.println(attributes.getLength());
				
				Method[] methods=clsName.getDeclaredMethods();
				for(Method m:methods) {
					String mName=m.getName();
					if(mName.equalsIgnoreCase("set"+s.substring(s.indexOf(":")+1))) {
						m.invoke(obj, name);
					}
				}
				map.put(id, obj);
			}
		} catch(Exception ex) {}
	}
}