package com.yc.xk.biz;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.xk.dao.MsgDao;
import com.yc.xk.po.XkMsg;

@Service
public class MsgBiz {

	@Resource
	private MsgDao dao = new MsgDao();
	
	public void addMsg(XkMsg xkmsg)throws BizException{
		if(xkmsg.getContent()==null || xkmsg.getContent().isEmpty()) {
			throw new BizException("留言的内容未填写! ");
		}
		try {
			dao.insertMsg(xkmsg);
		}catch (SQLException e) {
			throw new BizException("业务繁忙，请稍后再试！", e);
		}
	}
}
