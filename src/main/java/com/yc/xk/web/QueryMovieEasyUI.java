package com.yc.xk.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.xk.dao.MovieDao;
import com.yc.xk.po.XkMovie;

@RestController
public class QueryMovieEasyUI {
	
	@Resource
	private MovieDao mdao;
	
	@RequestMapping(path="querymovie.s")
	public Map<String,Object> queryMovie(String page){
		int iPage = page == null ? 1 : Integer.parseInt(page);
		List<XkMovie> rows=mdao.selectPage(iPage);
		int total = mdao.selectCount();
		
		Map<String,Object>data=new HashMap<>();
		data.put("rows", rows);
		data.put("total", total);
		return data;
	}

}
