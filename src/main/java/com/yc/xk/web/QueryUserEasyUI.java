package com.yc.xk.web;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.xk.dao.UserDao;
import com.yc.xk.po.User;

@RestController
public class QueryUserEasyUI {
	
	@Resource
	private UserDao udao;
	
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

}
