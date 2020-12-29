package com.yc.xk.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.xk.biz.MovieBiz;
import com.yc.xk.dao.MovieDao;
import com.yc.xk.po.XkMovie;

@RestController
public class QueryMovieEasyUI {
	
	@Resource
	private MovieDao mdao;
	
	@Resource
	private MovieBiz mbiz;
	
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
	
	@RequestMapping("rmmovie")
	public Map<String,Object> rm(String name,String page) {
		if(name==null) {
			int iPage = page == null ? 1 : Integer.parseInt(page);
			List<XkMovie> rows=mdao.selectPage(iPage);
			int total = mdao.selectCount();
			
			Map<String,Object>data=new HashMap<>();
			data.put("rows", rows);
			data.put("total", total);
			return data;
		}else {
			int iPage = page == null ? 1 : Integer.parseInt(page);
			List<XkMovie> rows=mdao.selectPageLike(iPage,name);
			int total = mdao.selectCountLike(name);
			
			Map<String,Object>data=new HashMap<>();
			data.put("rows", rows);
			data.put("total", total);
			System.out.println(data);
			return data;
		}
	}

}
