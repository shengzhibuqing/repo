package com.yc.xk.biz;

import java.util.List;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.xk.dao.MovieDao;
import com.yc.xk.po.XkMovie;

@Service
public class MovieBiz {
	
	@Resource
	private MovieDao mdao;
	
	@Resource
	private MailBiz mbiz;
	
	public String sendVcode(String email) {
		//产生随机验证码
		String vcode = ""+System.currentTimeMillis();
		vcode = vcode.substring(vcode.length()-4);
		//发送邮件
		mbiz.sendSimpleMail(email, "注册时发送的验证码","请使用"+vcode+"验证码注册您的账号！！");
		return vcode;
	}

	public List<XkMovie> queryLike(String m) throws BizException {
		List<XkMovie>list = mdao.queryLike(m);
		return list;
	}
	
	/*
	public String resetPwd(String email,String vcode,
			String pwd,String sessionVcode) {
		if(vcode.equalsIgnoreCase(sessionVcode)) {
			adao.updatePwdByName(pwd,name);
			return "重置密码成功";
		}else {
			return "验证码错误";
		}
	}
	*/

}
