package com.yc.xk.dao;

import java.sql.ResultSet;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yc.xk.po.User;
import com.yc.xk.biz.BizException;
import com.yc.xk.biz.UserBiz;

@Repository
public class UserDao extends BaseDao{
	
	@Resource
	private UserBiz ubiz;
	
	public void insert(User user) throws SQLException {
		String sql = "insert into user values(null,?,?,?,null,?)";
		jt.update(sql, user.getPhone(), user.getEmail(), user.getName(), user.getPwd());
	}
	
	public User selectByEmail(String email) {
		String sql = "select * from user where email=?";
		return jt.query(sql, rs -> {
			return rs.next() ? UserRowMapper.mapRow(rs, -1) : null;
		}, email);
	}
	
	public User login(String email, String password, String vcode, HttpSession session) throws BizException {
		return ubiz.login(email, password, vcode, session);
	}

	public void register(User user) throws BizException, SQLException {
		ubiz.register(user);
	}
	
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
