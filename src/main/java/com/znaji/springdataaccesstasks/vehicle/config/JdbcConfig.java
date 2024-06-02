package com.znaji.springdataaccesstasks.vehicle.config;

import com.zaxxer.hikari.HikariDataSource;
import com.znaji.springdataaccesstasks.vehicle.dao.PlainJdbcVehicleDao;
import com.znaji.springdataaccesstasks.vehicle.dao.VehicleDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import org.postgresql.Driver;

@Configuration
public class JdbcConfig {
    @Bean
    public VehicleDao vehicleDao(DataSource dataSource) {
        return new PlainJdbcVehicleDao(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        var dataSource = new HikariDataSource();
        dataSource.setDriverClassName(Driver.class.getName());
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/spring_boot");
        dataSource.setUsername("root");
        dataSource.setPassword("toor");
        dataSource.setMaximumPoolSize(5);
        dataSource.setMinimumIdle(2);
        return dataSource;
    }
}
