package org.springcourse.project.springsocialnetwork.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer 
public class OAuthResourceConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
    http.
        antMatcher("/api/v1/users/**")
        .authorizeRequests().anyRequest().authenticated()
        .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

}
