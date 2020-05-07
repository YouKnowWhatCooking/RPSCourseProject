package com.config;

import com.dao.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.controller")
public class SpringMvcConfig implements WebMvcConfigurer {

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/course?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }

    @Bean
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");

        return resolver;
    }

    @Bean
    public ClothesDAO getClothesDAO() {
        return new ClothesDAO(getDataSource());
    }
    @Bean
    public ImageDAO getImageDAO() {
        return new ImageDAO(getDataSource());
    }
    @Bean
    public CartDAO getCartDAO() {
        return new CartDAO(getDataSource());
    }
    @Bean
    public TemplateDAO getTemplateDAO() {
        return new TemplateDAO(getDataSource());
    }
    @Bean
    public PrintDAO getPrintDAO() {
        return new PrintDAO(getDataSource());
    }
    @Bean
    public CombinationDAO getCombinationDAO() {
        return new CombinationDAO(getDataSource());
    }
}
