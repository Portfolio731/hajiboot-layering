package com.example;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import net.sf.log4jdbc.Log4jdbcProxyDataSource;

@Configuration
public class AppConfig {
	@Autowired
	DataSourceProperties dataSourceProperties;
	DataSource dataSource;
	
	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean(destroyMethod = "close")
	DataSource realDataSource() {
		DataSourceBuilder factory = DataSourceBuilder
				.create(this.dataSourceProperties.getClassLoader())
				.url(this.dataSourceProperties.getUrl())
				.username(this.dataSourceProperties.getUsername())
				.password(this.dataSourceProperties.getPassword());
		this.dataSource =factory.build();
		return this.dataSource;
	}
	
	@Primary
	@Bean
	DataSource dataSource() {
		return new Log4jdbcProxyDataSource(this.dataSource);
	}
}
