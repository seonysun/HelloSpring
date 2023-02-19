package com.sist.proxy;

public class MainClass {
	public static void main(String[] args) {
		MovieDAO dao=new MovieDAO();
		Proxy p=new Proxy(dao);
		p.movieList();
	}
}
