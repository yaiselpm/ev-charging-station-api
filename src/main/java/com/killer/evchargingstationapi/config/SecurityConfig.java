package com.killer.evchargingstationapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                // .antMatchers(HttpMethod.POST, "/stations")
                .antMatchers("/stations/**")                                
                .hasRole("ADMIN")
                .and()
                .csrf().disable()
                .httpBasic();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService testOnlyUsers(PasswordEncoder passwordEncoder) {
        User.UserBuilder users = User.builder();
        UserDetails operator = users
                .username("operator")
                .password(passwordEncoder.encode("abc123"))
                .roles("ADMIN") // new role
                .build();
        UserDetails client = users
                .username("client1")
                .password(passwordEncoder.encode("qrs456"))
                .roles("CLIENT") // new role
                .build();
       
        return new InMemoryUserDetailsManager(operator, client);
    }
}
