package com.sist.web;
import com.sist.manager.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieRestController {
	@Autowired
	public MovieManager mm;
	
	@RequestMapping(value="movie/list_json.do", produces="text/plain;charset=UTF-8")
	public String movie_list_json(int no) {
		String json=mm.movieSite(no);
		return json;
	}
}
