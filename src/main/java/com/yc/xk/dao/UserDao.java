package com.yc.xk.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yc.xk.po.User;
import com.yc.xk.biz.UserBiz;

@Repository
public class UserDao extends BaseDao{
	
	@Resource
	private UserBiz bbiz;
	
	private RowMapper<User> UserRowMapper = new RowMapper<User>() {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUid(rs.getInt("uid"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPhone(rs.getString("phone"));
			user.setPhone(rs.getString("head"));
			user.setPhone(rs.getString("pwd"));
			user.setRegTime(rs.getTimestamp("reg_time"));
			return user;
		}
	};
	

}
