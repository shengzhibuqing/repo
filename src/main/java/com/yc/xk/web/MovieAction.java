package com.yc.xk.web;

import java.util.List;


import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.xk.dao.MovieDao;
import com.yc.xk.po.XkMovie;
import com.yc.xk.po.XkMovieWithBLOBs;

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
	
	@RequestMapping(path="movie.s",params = "op=queryHot")
	public List<XkMovie> queryHot(){
		return mdao.selectHotMovie();
	}
	
	@RequestMapping(path="movie.s",params = "op=queryHot1")
	public List<XkMovie> queryHot1(){
		return mdao.selectHotMovie1();
	}
	
	@RequestMapping(path="movie.s",params = "op=queryHot2")
	public List<XkMovie> queryHot2(){
		return mdao.selectHotMovie2();
	}
	
	@RequestMapping(path="movie.s" ,params = "op=queryMovieById")
	public XkMovieWithBLOBs selectMovieById(int id){
		return mdao.selectMovieById(id);
	}


}
