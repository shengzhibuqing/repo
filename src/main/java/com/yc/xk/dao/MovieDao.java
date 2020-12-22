package com.yc.xk.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yc.xk.po.XkMovie;

@Repository
public class MovieDao extends BaseDao{

	public List<XkMovie> selectMovie() {
		String sql = "select * from xk_movie limit 0,16";
		return jt.query(sql, movieRowMapper);
	}
	
	public List<XkMovie> selectNewMovie() {
		String sql = "select * from xk_movie order by create_date desc limit 0,6";
		return jt.query(sql, movieRowMapper);
	}
	
	private RowMapper<XkMovie> movieRowMapper = new RowMapper<XkMovie>() {

		@Override
		public XkMovie mapRow(ResultSet rs, int rowNum) throws SQLException {
			XkMovie m = new XkMovie();
			m.setId(rs.getInt("id"));
			m.setName(rs.getString("name"));
			m.setPoster(rs.getString("poster"));
			m.setCreateDate(rs.getInt("create_date"));
			return m;
		}
	};

}
