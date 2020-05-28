package com.config;

import com.dao.*;
import com.service.SecurityService;
import com.service.UserDetailsService;
import com.service.UserService;
import com.validator.UserValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.controller")
public class SpringMvcConfig implements WebMvcConfigurer {


    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/course?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }

    @Bean
    public ViewResolver getViewResolver() {
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

    @Bean
    public OrderDAO getOrderDAO() {
        return new OrderDAO(getDataSource());
    }

    @Bean
    public OrderItemDAO getOrderItemDAO() {
        return new OrderItemDAO(getDataSource());
    }

    @Bean
    public UsersDAO getUsersDAO() {
        return new UsersDAO(getDataSource());
    }

    @Bean
    public UserService getUserService() {
        return new UserService();
    }
    @Bean
    public SecurityService getSecurityService() {
        return new SecurityService();
    }
    @Bean
    public UserDetailsService getUserDetailsService() {
        return new UserDetailsService();
    }
    @Bean
    public UserValidator getUserValidator() {
        return new UserValidator();
    }
    @Bean
    public AuthenticationManager getAuthenticationManager() {
        return new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return null;
            }
        };
    }
}

