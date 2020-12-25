package com.yc.xk.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.xk.biz.BizException;
import com.yc.xk.biz.MsgBiz;
import com.yc.xk.dao.MsgDao;
import com.yc.xk.po.XkMsg;

@RestController
public class AddMsgAction {

	
	@Resource
	MsgBiz biz = new MsgBiz();
	
	@RequestMapping("addMsg")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("----");
		response.setContentType("text/html;charset=utf-8");
		XkMsg xkmsg = new XkMsg();
		
		xkmsg.setContent(request.getParameter("content"));
		System.out.println(request.getParameter("content") + "--");
		try {
			biz.addMsg(xkmsg);
			System.out.println("-");
			response.getWriter().append("成功留言！");
		}catch (BizException e) {
			e.printStackTrace();
			response.getWriter().append(e.getMessage());
		}
	 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}
	
	@Resource
	MsgDao mdao = new MsgDao();	
	
	@RequestMapping(path="xkmsg.s",params = "op=showMsg")
	public List<XkMsg> shouMsg() throws IOException{
		return mdao.shouMsg();
	}
	
	
}
