package com.arun.authorizationserver.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author arun on 12/26/21
 */

@Configuration
@Profile("local")
@Order(100)
public class CustomWebSecurity extends WebSecurityConfigurerAdapter {

    /**
     * The below method will authenticate the user using the authentication manager builder
     * where we pass username and password which is in memory details for the user
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //the below code is for InMemory Authentication
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
        UserDetails user1 = User.withUsername("admin").password(passwordEncoder().encode("abc")).authorities("admin").build();
        UserDetails user2 = User.withUsername("develop").password(passwordEncoder().encode("def")).authorities("develop").build();
        userDetailsService.createUser(user1);
        userDetailsService.createUser(user2);

        auth.userDetailsService(userDetailsService);

    }

    /**
     * Override the configure method, which will require authentication
     * for all the endpoints which starts with v1 and
     * all the endpoints which starts v2 will not be authenticated and can
     * be accessed without spring security
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests()
                .mvcMatchers("/v1/**").authenticated()
                .mvcMatchers("/v2/**").permitAll()
                .and().formLogin().and().httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
