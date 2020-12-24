package com.yc.xk.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.xk.dao.RootDao;
import com.yc.xk.po.Root;
import com.yc.xk.util.Utils;

@Service
public class RootBiz {
	
	@Resource
	private RootDao rdao;
	
	@Resource
	private MailBiz mbiz;
	
	public Root login(String email,String password) throws BizException {
		// 字段验证
		Utils.checkNull(email, "请输入邮箱");
		Utils.checkNull(password, "请输入密码");
		
		Root root = new Root();
		root = rdao.selectByEmail(email);
		if(root == null) {
			throw new BizException("请检查邮箱是否正确");
		}
	
		if( !root.getPassword().equals(password)  ) {
			throw new BizException("密码错误");
		}
		return root;
	}

}
