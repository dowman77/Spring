package com.dowman.spring.test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class XdpUserDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Transactional(propagation = Propagation. REQUIRED, isolation = Isolation.SERIALIZABLE)
	public String getUser(String id) throws SQLException, ClassNotFoundException {
		String sql = "select cmpy_cd from xdp_user where user_id = :user_id";
		Map pMap = new HashMap();
		pMap.put("user_id", id);
		String cmpy_cd = namedParameterJdbcTemplate.queryForObject(sql, pMap, java.lang.String.class);
		return cmpy_cd;
	}

	@Transactional(propagation = Propagation. REQUIRED, isolation = Isolation.SERIALIZABLE)
	public int insert(XdpUser xdpUser) throws ClassNotFoundException, SQLException {
		String sql = "insert into xdp_user (user_id,user_nm,cmpy_cd) values (:user_id,:user_nm,:cmpy_cd)";
		Map pMap = new HashMap();
		pMap.put("user_id", xdpUser.getUser_id());
		pMap.put("user_nm", xdpUser.getUser_nm());
		pMap.put("cmpy_cd", xdpUser.getCmpy_cd());
		int count = namedParameterJdbcTemplate.update(sql, pMap);
		return count;
	}

	@Transactional(propagation = Propagation. REQUIRED, isolation = Isolation.SERIALIZABLE)
	public int delete(String id) throws SQLException, ClassNotFoundException {
		String sql = "delete from xdp_user where user_id = :user_id";
		Map pMap = new HashMap();
		pMap.put("user_id", id);
		int count = namedParameterJdbcTemplate.update(sql, pMap);
		return count;
	}

}
