package com.course.graphql.datasource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import net.datafaker.Faker;

@Configuration
public class DataSourceConfig {
    
    @Bean
    Faker faker() {
        return new Faker();
    }
}