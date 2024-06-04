package com.znaji.springdataaccesstasks.course.config;

import com.znaji.springdataaccesstasks.course.entity.Course;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class CourseConfig {

    private final Environment environment;

    @Bean
    @Profile("hibernate")
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

    @Bean
    @Profile("jpa")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        var entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan("com.znaji.springdataaccesstasks.course.entity");

        var jpaProvider = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(jpaProvider);
        entityManagerFactoryBean.setJpaProperties(jpaProperties());
        return entityManagerFactoryBean;
    }

    private Properties jpaProperties() {
        var properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.setProperty("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", environment.getProperty("spring.jpa.show-sql"));
        return properties;
    }
}
