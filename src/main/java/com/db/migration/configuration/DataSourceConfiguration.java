package com.db.migration.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.starships")
    public DataSourceProperties starshipsDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.vessels")
    public DataSourceProperties vesselsDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource starshipsDataSource(@Qualifier("starshipsDataSourceProperties") DataSourceProperties dataSourceProperties) {
        System.out.println(dataSourceProperties);
        return dataSourceProperties
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public DataSource vesselsDataSource(@Qualifier("vesselsDataSourceProperties") DataSourceProperties dataSourceProperties) {
        System.out.println(dataSourceProperties);
        return dataSourceProperties
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public JdbcTemplate starshipsJdbcTemplate(@Qualifier("starshipsDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JdbcTemplate vesselsJdbcTemplate(@Qualifier("vesselsDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
