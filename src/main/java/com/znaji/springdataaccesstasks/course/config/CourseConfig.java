package com.znaji.springdataaccesstasks.course.config;

import com.znaji.springdataaccesstasks.course.entity.Course;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
public class CourseConfig {

    private final Environment environment;

    @Bean
    public SessionFactory sessionFactory() {
        var configuration = new org.hibernate.cfg.Configuration()
                .setProperty(AvailableSettings.JAKARTA_JDBC_URL, environment.getProperty("spring.datasource.url"))
                .setProperty(AvailableSettings.JAKARTA_JDBC_USER, environment.getProperty("spring.datasource.username"))
                .setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, environment.getProperty("spring.datasource.password"))
                .setProperty(AvailableSettings.SHOW_SQL, environment.getProperty("spring.jpa.show-sql"))
                .setProperty(AvailableSettings.HBM2DDL_AUTO, environment.getProperty("spring.jpa.hibernate.ddl-auto"))
                .setProperty(AvailableSettings.JAKARTA_JDBC_DRIVER, environment.getProperty("spring.datasource.driver-class-name"))
                .addAnnotatedClass(Course.class);
        return configuration.buildSessionFactory();
    }
}
