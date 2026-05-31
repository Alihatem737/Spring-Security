package com.course.springscurityex.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityconfig {


    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

      return http.csrf(customizer-> customizer.disable())
            .authorizeHttpRequests(request -> request.anyRequest().authenticated())
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults())
            .sessionManagement( session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
       // provider.setUserDetailsService(UserDetailsService -> );
        return provider;
    }



//    @Bean
//    public UserDetailsService userDetailsService (){
//
//        UserDetails user1 = User
//                .withDefaultPasswordEncoder()
//                .username("Hatem")
//                .password("1234")
//                .roles("USER")
//                .build();
//
//
//        UserDetails user2 = User
//                .withDefaultPasswordEncoder()
//                .username("oamr")
//                .password("a123")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user1 , user2);
//
//
//    }

}
