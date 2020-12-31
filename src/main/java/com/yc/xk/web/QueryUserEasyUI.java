package com.yc.xk.web;

import java.sql.SQLException;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.xk.biz.BizException;
import com.yc.xk.biz.UserBiz;
import com.yc.xk.dao.UserDao;
import com.yc.xk.po.Result;
import com.yc.xk.po.User;
import com.yc.xk.po.XkMovie;

@RestController
public class QueryUserEasyUI {
	
	@Resource
	private UserDao udao;
	
	@Resource
	private UserBiz ubiz;
	
	@RequestMapping(path="queryuser.s")
	public Map<String,Object> queryMovie(String page){
		int iPage = page == null ? 1 : Integer.parseInt(page);
		List<User> rows=udao.selectPage(iPage);
		int total = udao.selectCount();
		
		Map<String,Object>data=new HashMap<>();
		data.put("rows", rows);
		data.put("total", total);
		return data;
	}
	
	@RequestMapping("remuser.s")
	public Map<String,Object> rm(String email,String page) {
		System.out.println("3333333333"+email);
		if(email==null) {
			int iPage = page == null ? 1 : Integer.parseInt(page);
			List<User> rows=udao.selectPage(iPage);
			int total = udao.selectCount();
			
			Map<String,Object>data=new HashMap<>();
			data.put("rows", rows);
			data.put("total", total);
			System.out.println(data);
			return data;
		}else {
			int iPage = page == null ? 1 : Integer.parseInt(page);
			List<User> rows=udao.selectPageLike(iPage,email);
			int total = udao.selectCountLike(email);
			
			Map<String,Object>data=new HashMap<>();
			data.put("rows", rows);
			data.put("total", total);
			System.out.println(data);
			return data;
		}
	}
	
	@RequestMapping(path="shanchuu.s")
	public void shanchu(String email){
		System.out.println(email);
		udao.del(email);
	}
	@RequestMapping("createUser")
	public Result create(User u) throws SQLException {
		try {
			ubiz.create(u);
			return Result.success("用户添加成功!");
		} catch (BizException e) {
			e.printStackTrace();
			return Result.failure(e.getMessage());
		}
	}


}
