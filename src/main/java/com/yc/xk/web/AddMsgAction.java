package com.yc.xk.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import javax.servlet.http.HttpSession;
import javax.websocket.EncodeException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.xk.biz.BizException;
import com.yc.xk.biz.MsgBiz;
import com.yc.xk.dao.MsgDao;
import com.yc.xk.po.Result;
import com.yc.xk.po.User;
import com.yc.xk.po.XkMsg;

@RestController
public class AddMsgAction {

	
	@Resource
	MsgBiz mbiz = new MsgBiz();
	
	@RequestMapping("addMsg")
	public Result addmsg(String content,HttpSession s,String mid) throws IOException, EncodeException, BizException, SQLException {
		User user=(User) s.getAttribute("loginedUser");
		if(user==null) {
			return new Result(0,"用户未登录不能留言");
		}else if(content.equals("")){
			return new Result(0,"留言内容不能为空");
		}else {
			mbiz.addMsg(user.getName(),user.getEmail(),content,mid);
			return new Result(1,"留言成功");
		}
	}
	
	@Resource
	MsgDao mdao = new MsgDao();	
	
	@RequestMapping(path="xkmsg.s",params = "op=showMsg")
	public List<XkMsg> shouMsg(String mid) throws IOException{
		return mdao.shouMsg(mid);
	}
	
	
}
