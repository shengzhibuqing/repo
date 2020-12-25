package com.yc.xk.biz;

import java.sql.SQLException;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.xk.dao.MsgDao;

@Service
public class MsgBiz {

	@Resource   
	private MsgDao dao = new MsgDao();
	
	public void addMsg(String name,String email,String content,String mid)throws BizException, SQLException{
		dao.insertMsg(name,email,content,mid);
	}

}
