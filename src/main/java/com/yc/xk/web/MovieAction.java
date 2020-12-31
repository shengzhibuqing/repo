package com.yc.xk.web;

import java.util.List;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.xk.biz.BizException;
import com.yc.xk.biz.MovieBiz;
import com.yc.xk.dao.MovieDao;
import com.yc.xk.po.Result;
import com.yc.xk.po.User;
import com.yc.xk.po.XkMovie;
import com.yc.xk.po.XkMovieWithBLOBs;

@RestController
public class MovieAction {
	
	@Resource
	private MovieDao mdao;
	
	@Resource
	private MovieBiz mbiz;
	
	@Resource
	private StringRedisTemplate rt;
	
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
		/**
		 * 定义当前商品浏览器在redis中的键值
		 */
		String key = "movie_bcount_" + id;
		/**
		 * rt.opsForValue() 获取操作 stirng 类型的 redis对象
		 * increment(key), 让 key 自增 1 ==> key++ 
		 */
		rt.opsForValue().increment(key);
		return mdao.selectMovieById(id);
	}
	
	@RequestMapping(path="movie.s" ,params = "op=queryl")
	public List<XkMovie> queryl(String m,int page){
		List<XkMovie> list = mdao.queryLikePage(m,page);
		return list;
	}

	@RequestMapping(path="queryM")
	public Result queryLike(String m) throws BizException{
		if(m.equals("")) {
			return new Result(0,"请输入你想查询的片名");
		}else {
			List<XkMovie>list = mbiz.queryLike(m);
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
	
	
	@RequestMapping(path="pf")
	public Result pf(int score,String name,HttpSession s){
		System.out.println("----------"+name);
		User user=(User) s.getAttribute("loginedUser");
		String mids="df_"+name+"";
		
		if(user==null) {
			return new Result(0,"用户未登录不能评分");	
		}else {
			if(rt.opsForHash().get(mids, user.getUid()+"")!=null){
				return new Result(0,"您已评过分");
			} else {
				double sum=0;
				rt.opsForHash().put(mids, user.getUid()+"",score+"");
				Map map = rt.opsForHash().entries(mids);
				map.get( user.getUid()+"") ;
				map.values();
				for( Object a:map.values()) {
					double sc=Double.parseDouble(a.toString());   
					sum+=sc;
				}
				double scores=sum/map.size();
				System.out.println("=============="+scores );
				mdao.selectPf(scores, name);
				return new Result(1,"评分成功");
				}
		}
	}
	
	@RequestMapping("createMovie")
	public Result create(XkMovie m) {
		try {
			mbiz.create(m);
			return Result.success("电影添加成功!");
		} catch (BizException e) {
			e.printStackTrace();
			return Result.failure(e.getMessage());
		}
	}
}
