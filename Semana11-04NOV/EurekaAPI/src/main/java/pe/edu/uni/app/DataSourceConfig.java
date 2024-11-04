package pe.edu.uni.app;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
          .driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
          .url("jdbc:sqlserver://localhost:1433;databaseName=EUREKABANK;encrypt=True;TrustServerCertificate=True")
          .username("sa")
          .password("sql")
          .build();	
    }
}