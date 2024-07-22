package org.yearup.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.yearup.security.JwtAccessDeniedHandler;
import org.yearup.security.JwtAuthenticationEntryPoint;
import org.yearup.security.UserModelDetailsService;
import org.yearup.security.jwt.TokenProvider;

import static org.mockito.Mockito.mock;

@Configuration
@EnableWebSecurity
@Order(1)
public class TestSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public TokenProvider tokenProvider() {
        return mock(TokenProvider.class);
    }

    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        return mock(JwtAuthenticationEntryPoint.class);
    }

    @Bean
    public JwtAccessDeniedHandler jwtAccessDeniedHandler() {
        return mock(JwtAccessDeniedHandler.class);
    }

    @Bean
    public UserModelDetailsService userModelDetailsService() {
        return mock(UserModelDetailsService.class);
    }
}