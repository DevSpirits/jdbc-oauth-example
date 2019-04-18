package kr.co.woosuk.oauth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import kr.co.woosuk.oauth.util.DataBaseProperties;

@Configuration
public class DataSourceConfig {
	
	@Autowired
	private DataBaseProperties dataBaseProperties;
	    	
	@Bean(name="dataSource")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName(dataBaseProperties.getDriverClassName());
		dataSource.setUrl(dataBaseProperties.getUrl());
		dataSource.setUsername(dataBaseProperties.getUsername());
		dataSource.setPassword(dataBaseProperties.getPassword());
		
		return dataSource;
	}
	
}
