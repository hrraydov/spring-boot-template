package com.hrraydov.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ComponentScan(basePackages = { "com.hrraydov.service" })
@EnableJpaRepositories(basePackages = "com.hrraydov.data.repository")
@EntityScan(basePackages = { "com.hrraydov.data.entity", "com.hrraydov.security.entity" })
public class DefaultConfig {

	@Bean
	public DataSource dataSource() {
		// no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL).build();
		// .addScript("classpath:schema.sql").addScript("classpath:test-data.sql").build();
		return db;
	}

}
