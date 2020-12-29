package com.yc.xk.dao;

import java.sql.ResultSet;


import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

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
	
	public User login(String email, String pwd) throws BizException {
		return ubiz.login(email, pwd);
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
			user.setHead(rs.getString("head"));
			user.setPwd(rs.getString("pwd"));
			return user;
		}
	};

	public List<User> selectPage(int page) {
		//计算开始页数
		int begin=(page-1)*10;
		//mysql 分页查询语法 ：limit 从第几行开始，查几行数据
		String sql="select * from user limit ?,10";
		return jt.query(sql, UserRowMapper, begin);
				
	}

	
	public int selectCount() {
		String sql="select count(*) cnt from user";
		return jt.queryForObject(sql,Integer.class);
	}

	public Integer updateByEmail(String email,String newpwd) {
		String sql = "update user set pwd = ? where email = ?";
		return jt.update(sql, newpwd,email);
	}

	public void insert(String name, String email, String phone, String pwd) {
		String sql = "insert into user values(null,?,?,?,null,?)";
		jt.update(sql,
		phone,
		email,
		name,
		pwd);
	}
		
}
