package com.sf.bcsp.conf;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class BcspDBConfig {
	@Bean
	public DataSource dataSource(@Value("${spring.datasource.url}") String url,
			@Value("${spring.datasource.username}") String userName,
			@Value("${spring.datasource.password}") String passWord,
			@Value("${spring.datasource.initialsize}") int initialSize,
			@Value("${spring.datasource.maxactive}") int maxActive, 
			@Value("${spring.datasource.minidle}") int minidle,
			@Value("${spring.datasource.maxwait}") long maxwait,
			@Value("${spring.datasource.checksql}") String checksql) {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(userName);// 用户名
		dataSource.setPassword(passWord);// 密码
		dataSource.setInitialSize(initialSize);
		dataSource.setMaxActive(maxActive);
		dataSource.setMinIdle(minidle);
		dataSource.setMaxWait(maxwait);
		dataSource.setValidationQuery(checksql);
		dataSource.setTestOnBorrow(false);
		dataSource.setTestOnReturn(false);
		dataSource.setTestWhileIdle(true);
		dataSource.setTimeBetweenEvictionRunsMillis(25000);
		dataSource.setMinEvictableIdleTimeMillis(300000);
		dataSource.setRemoveAbandoned(true);
		dataSource.setRemoveAbandonedTimeout(18000);
		return dataSource;
	}
}
