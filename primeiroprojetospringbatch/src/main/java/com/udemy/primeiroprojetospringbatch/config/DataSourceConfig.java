package com.udemy.primeiroprojetospringbatch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Autowired
    private SpringBatchDataSourceProperties springBatchDataSourceProperties;

    @Primary
    @Bean
    public DataSource springDataSource(){
        return springBatchDataSourceProperties
                .createSpringBatchDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public DataSource appDataSource(){
        return springBatchDataSourceProperties
                .createAppDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
}
