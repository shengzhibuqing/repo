package com.yc.xk.web;

import java.util.List;



import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.xk.biz.BizException;
import com.yc.xk.biz.MovieBiz;
import com.yc.xk.dao.MovieDao;
import com.yc.xk.po.Result;
import com.yc.xk.po.XkMovie;
import com.yc.xk.po.XkMovieWithBLOBs;

@RestController
public class MovieAction {
	
	@Resource
	private MovieDao mdao;
	
	@Resource
	private MovieBiz mbiz;
	
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
	
	@RequestMapping(path="movie.s" ,params = "op=queryl")
	public List<XkMovie> queryl(String m,int page){
		List<XkMovie> list = mdao.queryLikePage(m,page);
		System.out.println("niubi"+m);
		System.out.println("------"+list);
		return list;
	}

	@RequestMapping(path="queryM")
	public Result queryLike(String m) throws BizException{
		if(m.equals("")) {
			return new Result(0,"请输入你想查询的片名");
		}else {
			List<XkMovie>list = mbiz.queryLike(m);
			System.out.println("++++++"+list);
			if(list.isEmpty()) {
				return new Result(0,"网站资源有限，没有相关电影");
			}else {
				return new Result(1,"查询成功");
			}
		}
	}
	
	@RequestMapping(path="movie.s" ,params = "op=querycount")
	public int querycount(String m){
		int pages = (int) Math.ceil(mdao.selectCountpage(m)/6.0);
		return pages;
	}
}
