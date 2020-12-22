package com.yc.xk.web;


import java.io.IOException;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.EncodeException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.xk.biz.BizException;
import com.yc.xk.biz.MovieBiz;
import com.yc.xk.biz.UserBiz;
import com.yc.xk.dao.UserDao;
import com.yc.xk.po.Result;
import com.yc.xk.po.User;

@RestController
public class UserAction {

	@Resource
	private UserDao udao;
	
	@Resource
	private UserBiz ubiz;
	
	@RequestMapping("getLoginedUser")
	public User getLoginedUser(String username,HttpSession session) {
		User user = (User) session.getAttribute("loginedUser");
		return user;
	}
	
	@RequestMapping("login")
	public Result login(String email,String pwd,HttpSession s) throws IOException, EncodeException {
		User user = new User();
		try {
			System.out.println(email);
			System.out.println(pwd);
			user = udao.login(email,pwd);
			s.setAttribute("loginedUser", user);
			return new Result(1, "登录成功");
		} catch (BizException e) {
			e.printStackTrace();
			return  new Result(0, e.getMessage());
		}
		
	}
	
	@RequestMapping("reg")
	public Result reg(User user,String repwd,String vcode,HttpSession session) throws SQLException {
		try {
			//System.out.println(user.getPwd());
			//System.out.println(repwd);
			String svcode = (String) session.getAttribute("vcode");
			if(!vcode.equalsIgnoreCase(svcode)) {
				throw new BizException("验证码错误");
			}
			if(repwd.equals(user.getPwd())){
				udao.register(user);
				return new Result(1, "注册成功");
			}else {
				throw new BizException("两次密码不一致");
			}
		} catch (BizException e) {
			e.printStackTrace();
			return new Result(0, e.getMessage());
		} 
	}
	
	@Resource
	private MovieBiz mbiz;
	
	@RequestMapping("email.s")
	public Result msg(String email,HttpSession s) {
		try {
			System.out.println("6+++"+email);
			String vcode = mbiz.sendVcode(email);
			//将验证码保存到会话
			s.setAttribute("vcode", vcode);
			System.out.println(vcode);
			//通知浏览器发送成功
			return new Result(1,"发送成功");
		} catch (Exception e) {
			return new Result(0,"发送失败,邮箱未填写，请填写正确的邮箱地址");
		}
	}
	
}
