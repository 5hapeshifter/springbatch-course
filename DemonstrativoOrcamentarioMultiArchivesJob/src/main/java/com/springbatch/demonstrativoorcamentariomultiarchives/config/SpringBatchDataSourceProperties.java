package com.springbatch.demonstrativoorcamentariomultiarchives.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBatchDataSourceProperties {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.springbatch")
    public DataSourceProperties createSpringBatchDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.app")
    public DataSourceProperties createAppDataSourceProperties(){
        return new DataSourceProperties();
    }
}
