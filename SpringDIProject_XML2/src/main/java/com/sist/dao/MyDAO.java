package com.sist.dao;

public class MyDAO {
	private String url,user,password;
	public void setUrl(String url) {
		this.url = url;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public MyDAO(String driver) {
		System.out.println("DRIVER:"+driver);
	}
	public void getConnection() {
		System.out.println("URL:"+url);
		System.out.println("USER:"+user);
		System.out.println("PW:"+password);
	}
}
