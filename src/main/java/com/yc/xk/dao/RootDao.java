package com.yc.xk.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yc.xk.biz.BizException;
import com.yc.xk.biz.RootBiz;
import com.yc.xk.po.Root;

@Repository
public class RootDao extends BaseDao{
	
	@Resource
	private RootBiz rbiz;
	
	public Root login(String email, String password, String vcode, HttpSession session) throws BizException {
		return rbiz.login(email, password, vcode, session);
	}
	
	public Root selectByEmail(String email) {
		String sql = "select * from root where email=?";
		return jt.query(sql, rs -> {
			return rs.next() ? RootRowMapper.mapRow(rs, -1) : null;
		}, email);
	}
	
	
	private RowMapper<Root> RootRowMapper = new RowMapper<Root>() {

		@Override
		public Root mapRow(ResultSet rs, int rowNum) throws SQLException {
			Root root = new Root();
			root.setIname(rs.getString("name"));
			root.setEmail(rs.getString("email"));
			root.setPassword(rs.getString("password"));
			root.setPhone(rs.getString("phone"));
			return root;
		}
	};

}
