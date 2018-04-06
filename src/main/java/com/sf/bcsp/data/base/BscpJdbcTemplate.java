package com.sf.bcsp.data.base;



import com.sf.bcsp.base.entity.EmptoyObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Configuration
public class BscpJdbcTemplate extends JdbcTemplate {
	@Override
	public <T> T queryForObject(String sql, Object[] args, Class<T> requiredType) throws DataAccessException {
		try {
			return super.queryForObject(sql, args, requiredType);
		}catch(Exception ex)
		{
			return (T)EmptoyObject.newInstantce();
		}
	}

	@Override
	public <T> T queryForObject(String sql, Object[] args, RowMapper<T> rowMapper) throws DataAccessException {
		try {
			return super.queryForObject(sql, args, rowMapper);
		}catch (Exception ex)
		{
			return  (T)EmptoyObject.newInstantce();
		}
	}
}