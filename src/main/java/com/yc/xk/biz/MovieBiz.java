package com.yc.xk.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.xk.dao.MovieDao;
import com.yc.xk.po.XkMovie;
import com.yc.xk.util.Utils;

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

	public void delete(String name) throws BizException {
		if(name==null) {
			mdao.selectAllMovie();
		}else {
			mdao.queryLike(name);
		}
	}
	
	@Transactional
	public void create(XkMovie m) throws BizException{
		// 验证输入
		Utils.checkNull(m.getName(), "商品名称不能为空");
		Utils.checkNull(m.getNation(), "国家不能为空");
		Utils.checkNull(m.getTags(), "影片类型不能为空");
		Utils.checkNull(m.getLanguage(), "语言不能为空");
		Utils.checkNull(m.getDirector(), "导演不能为空");
		Utils.checkNull(m.getTimes(), "影片时长不能为空");

		// 添加到数据库
		mdao.insert(m);
	}
		
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

