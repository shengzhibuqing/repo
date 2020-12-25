package com.yc.xk.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yc.xk.po.XkMsg;

@Repository
public class MsgDao extends BaseDao{

	public void insertMsg(String name,String email,String content,String mid) throws SQLException {
		String sql = "insert into xk_msg values(null,?,?,?,default,null,null,?)";
		jt.update(sql, name,email,content,mid);
	}
	
	public List<XkMsg> shouMsg(String mid){
		String sql = "select * from xk_msg where mid = ? order by create_time desc";
		return jt.query(sql, msgRowMapper,mid);
		
	}
	private RowMapper<XkMsg> msgRowMapper = new RowMapper<XkMsg>() {

		@Override
		public XkMsg mapRow(ResultSet rs, int rowNum) throws SQLException {
			XkMsg m = new XkMsg();
			m.setId(rs.getInt("id"));
			m.setCreateName(rs.getString("create_name"));
			m.setContent(rs.getString("content"));
			m.setCreateTime(rs.getTimestamp("create_time"));
			return m;
		}
	};
	
}
