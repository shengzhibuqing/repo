package com.yc.xk.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yc.xk.po.XkMovie;
import com.yc.xk.po.XkMovieWithBLOBs;

@Repository
public class MovieDao extends BaseDao{

	public List<XkMovie> selectMovie() {
		String sql = "select * from xk_movie limit 0,16";
		return jt.query(sql, movieRowMapper);
	}
	
	public List<XkMovie> selectNewMovie() {
		String sql = "select * from xk_movie order by create_date desc limit 0,8";
		return jt.query(sql, movieRowMapper);
	}
	

	public List<XkMovie> selectHotMovie() {
		String sql = "select * from xk_movie order by bcount desc limit 0,6";
		return jt.query(sql, movieRowMapper);
	}
	
	public List<XkMovie> selectHotMovie1() {
		String sql = "select * from xk_movie order by bcount desc limit 7,6";
		return jt.query(sql, movieRowMapper);
	}
	
	public List<XkMovie> selectHotMovie2() {
		String sql = "select * from xk_movie order by bcount desc limit 13,3";
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
			m.setBcount(rs.getInt("bcount"));
			return m;
		}
	};
	
	private RowMapper<XkMovieWithBLOBs> moviewithRowMapper = new RowMapper<XkMovieWithBLOBs>() {

		@Override
		public XkMovieWithBLOBs mapRow(ResultSet rs, int rowNum) throws SQLException {
			XkMovieWithBLOBs m = new XkMovieWithBLOBs();
			m.setId(rs.getInt("id"));
			m.setName(rs.getString("name"));
			m.setPoster(rs.getString("poster"));
			m.setReleaseDate(rs.getString("release_date"));
			m.setTags(rs.getString("tags"));
			m.setTimes(rs.getString("times"));
			m.setDirector(rs.getString("director"));
			m.setIntro(rs.getString("intro"));
			return m;
		}
	};

	public XkMovieWithBLOBs selectMovieById(int id) {
		String sql = "select * from xk_movie where id=?";
		return jt.query(sql, rs->{
			return rs.next() ? moviewithRowMapper.mapRow(rs, -1) : null;
		}, id);
		
	}

}
