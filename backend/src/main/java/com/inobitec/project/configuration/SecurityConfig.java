package com.inobitec.project.configuration;

import com.inobitec.project.security.JwtFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(POST,"/api/v1/nodes").hasRole("ADMIN")
                .antMatchers(DELETE,"/api/v1/nodes/*").hasRole("ADMIN")
                .antMatchers(GET,"/api/v1/nodes/*").permitAll()
                .antMatchers(GET,"/api/v1/nodes/selected/*").permitAll()
                .antMatchers(GET,"/api/v1/me").authenticated()
                .antMatchers(POST,"/api/v1/security/register/admin").hasRole("ADMIN")
                .antMatchers("/api/v1/security/*").anonymous()
                .antMatchers("/api/v1/nodes").permitAll()

                .and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
