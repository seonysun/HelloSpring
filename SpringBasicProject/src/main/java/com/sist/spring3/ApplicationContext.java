package com.sist.spring3;
import java.util.*;

//Ŭ���� ������(�����̳�) -> ���������� �̹� ���۵Ǿ�����
public class ApplicationContext {
	private Map clsMap=new HashMap(); //������ Ŭ�������� ������ �÷��� ����
	public ApplicationContext() {
		clsMap.put("amodel", new AModel());
					//key�� ���� value������ ������ ��ü ����
		/*
		������ ��ü ȣ�� �����ϵ��� �����ص� -> �̱���(ù��° �ϳ��� ����, ���� ����) -> �޸� ���� ����, ���� �ܼ�
		*/
		clsMap.put("bmodel", new BModel());
		clsMap.put("cmodel", new CModel());
	}
	public Object getBean(String key) {
				//��ϵ� key�� ��ü ȣ���ϴ� �޼ҵ� ����
		return clsMap.get(key);
	}
}
