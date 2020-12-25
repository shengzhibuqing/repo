package com.yc.xk.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yc.xk.po.XkMovie;
import com.yc.xk.po.XkMsg;

@Repository
public class MsgDao extends BaseDao{

	public void insertMsg(XkMsg xkmsg) throws SQLException {
		String sql = "insert into xk_msg values(null,null,null,?,default,null,null,null)";
		jt.update(sql, xkmsg.getContent());
	}
 
	
	public List<XkMsg> shouMsg(){
		String sql = "select * from xk_msg order by create_time desc limit 0,6";
		return jt.query(sql, msgRowMapper);
		
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
