package com.yc.xk.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.xk.dao.MovieDao;
import com.yc.xk.dao.UserDao;
import com.yc.xk.po.XkMovie;

@RestController
public class MovieAction {
	
	@Resource
	private MovieDao mdao;
	
	@RequestMapping(path="movie.s",params = "op=queryMovie")
	public List<XkMovie> queryMovie(){
		return mdao.selectMovie();
	}
	
	@RequestMapping(path="movie.s",params = "op=queryNew")
	public List<XkMovie> queryNew(){
		return mdao.selectNewMovie();
	}

}
