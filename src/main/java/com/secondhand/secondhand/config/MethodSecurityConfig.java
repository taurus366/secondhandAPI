package com.secondhand.secondhand.config;

import com.secondhand.secondhand.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Autowired
    private SecondHandDefaultMethodSecurityExpressionHandler secondHandDefaultMethodSecurityExpressionHandler;


    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return secondHandDefaultMethodSecurityExpressionHandler;
    }

    @Bean
    public SecondHandDefaultMethodSecurityExpressionHandler creExpressionHandler(UserService userService) {
        return new SecondHandDefaultMethodSecurityExpressionHandler(userService);
    }
}
