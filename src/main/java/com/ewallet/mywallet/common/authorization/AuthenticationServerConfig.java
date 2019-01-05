/*
package com.ewallet.mywallet.common.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@Configuration("AuthenticationServerConfig")
@EnableResourceServer
@Order(1000)
public class AuthenticationServerConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin().disable()
                .authorizeRequests()
                .antMatchers("/ewallet/login/**")
                .permitAll()
                .antMatchers("/ewallet/**", "/transactions/**")
                .authenticated();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
            auth.parentAuthenticationManager(authenticationManager);
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
*/
