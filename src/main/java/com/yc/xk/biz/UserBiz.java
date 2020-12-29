package com.yc.xk.biz;

import java.sql.SQLException;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.xk.dao.UserDao;
import com.yc.xk.po.User;
import com.yc.xk.util.Utils;


@Service
public class UserBiz {
	
	@Resource
	private UserDao udao;
	
	@Resource
	private MailBiz mbiz;
	
	public String sendVcode(String email) {
		User user = udao.selectByEmail(email);
		// 生成随机验证码
		String vcode = "" + System.currentTimeMillis();
		vcode = vcode.substring(vcode.length()-4);
		mbiz.sendSimpleMail(user.getEmail(), "密码重置验证码","请使用"+vcode+"验证码来重置密码");
		return vcode;
	}

	public void register(User user) throws BizException, SQLException {
		// 字段验证
		Utils.checkNull(user.getName(), "昵称不能为空");
		Utils.checkNull(user.getEmail(), "邮箱不能为空");
		Utils.checkNull(user.getPhone(), "电话号码不能为空");
		Utils.checkNull(user.getPwd(), "密码不能为空");
		// 同名验证
		User dbuser = udao.selectByEmail(user.getEmail());
		if(dbuser != null ) {
			throw new BizException("该邮箱已经被注册");
		}
		
		try {
			udao.insert(user);
		} catch (SQLException e) {
			throw new BizException("注册失败，请联系管理员",e);
		}
	}
	
	public User login(String email,String pwd) throws BizException {
		// 字段验证
		Utils.checkNull(email, "请输入邮箱");
		Utils.checkNull(pwd, "请输入密码");
		
		User user = new User();
		user = udao.selectByEmail(email);
		System.out.println(user);
		
		if(user == null) {
			throw new BizException("请检查邮箱是否正确");
		}
	
		if( !user.getPwd().equals(pwd)  ) {
			throw new BizException("密码错误");
		}
		return user;
	}
	
	public Integer update(String email,String newpwd) throws BizException {
		// 字段验证
		Utils.checkNull(email, "请输入邮箱");
		Utils.checkNull(newpwd, "请输入新密码");
		
		int i=udao.updateByEmail(email,newpwd);
		//System.out.println(user);
		return i;
	}

	public void create(String name, String email, String phone, String pwd) throws BizException {
		Utils.checkNull(name, "用户名名不能为空");
		Utils.checkNull(email, "用户邮箱不能为空");
		Utils.checkNull(phone, "用户电话不能为空");
		Utils.checkNull(pwd, "密码不能为空");
		// ...其他字段请自行扩展: 2价格,图片,说明
		
		// 添加到数据库
		udao.insert(name,email,phone,pwd);
		
	}

}
