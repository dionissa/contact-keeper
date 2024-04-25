package org.vaadin.example.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(
                "jdbc:postgresql://cb4l59cdg4fg1k.cluster-czrs8kj4isg7.us-east-1.rds.amazonaws.com:5432/deu6p3lk86k3on");
        dataSource.setUsername("u37gu9sucfv22k");
        dataSource.setPassword("p1f0d00949457e99618ba2112213140d9155fdc290bb8fdcf3d75c4d82e7f914c");
        return dataSource;
    }
}
