package com.yc.xk;

import java.sql.SQLException;

import javax.annotation.Resource;




import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import com.yc.xk.dao.UserDao;
import com.yc.xk.po.User;


//SpringBoot自带的测试类
@SpringBootTest
class C89S2PlbBootApplicationTests {
	
	@Resource
	private JdbcTemplate jt;

	@Test
	void contextLoads() {
		Assert.notNull(jt,"jt不能为空");
		jt.update("insert into root values(null,?,?,?,?)","兰北","123","13187241457","2963465238@qq.com");
	}
	

	@Resource
	private UserDao udao;
	@Test
	void test() throws SQLException {
		User user= new User();
		user.setPhone("1313131355");
		user.setEmail("dasda");
		user.setName("a");
		user.setPwd("123");
		
		udao.insert(user);
	}

}
