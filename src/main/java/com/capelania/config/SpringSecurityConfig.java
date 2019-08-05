package com.capelania.config;

import static com.capelania.config.security.TokenAuthenticationService.COOKIE_AUTH_NAME;

import com.capelania.config.exception.GlobalExceptionHandler;
import com.capelania.config.filter.AuthenticationFilter;
import com.capelania.config.filter.LoginFilter;
import com.capelania.config.security.AccessDeniedHandlerImpl;
import com.capelania.config.security.AuthenticationEntryPointImpl;
import com.capelania.config.security.TokenAuthenticationService;
import com.capelania.config.security.UserRoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService())
            .passwordEncoder(encoder());
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserRoleService userDetailsService() {
        return new UserRoleService();
    }

    @Bean
    public AccessDeniedHandlerImpl accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }

    @Bean
    public AuthenticationEntryPointImpl authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().authorizeRequests()
                .antMatchers("/public/**", "/favicon.ico", "/swagger-resources").permitAll()
                .antMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .addFilterBefore(new LoginFilter(tokenAuthenticationService, authenticationManager(), objectMapper), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new AuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler()).authenticationEntryPoint(authenticationEntryPoint())
            .and()
                .logout()
                .logoutUrl("/public/logout")
                .logoutSuccessUrl("/public/logout/success")
                .deleteCookies(COOKIE_AUTH_NAME);
    }
}
