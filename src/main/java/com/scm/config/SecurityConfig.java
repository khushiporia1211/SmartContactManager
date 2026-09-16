package com.scm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration 
public class SecurityConfig {

    // // user create and login using java code with in memory service
    // public UserDetailsService userDetailsService(){
        
    //    UserDetails user1 =  User
    //    .withDefaultPasswordEncoder()
    //    .username("admin123")
    //    .password("admin123")
    //    .roles("ADMIN","USER")
    //    .build();

    //    UserDetails user2 =  User.withUsername("user123")
    //    .password("password")
    // //    .roles("ADMIN","USER")
    //    .build();

    //     var inMemoryUserDetailsManager =  new InMemoryUserDetailsManager(user1);
    //     return inMemoryUserDetailsManager;
    // }
    @Bean 
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(null);
        daoAuthenticationProvider.setUserDetailsService(null);
        daoAuthenticationProvider.setPasswordEncoder(null);
        return daoAuthenticationProvider;
    }
    
    @Bean 
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
