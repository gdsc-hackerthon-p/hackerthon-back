package com.gdsc.hackerthon.config;

import com.gdsc.hackerthon.user.application.PrinciplalOauthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        http

                //요청 보안 검사
                //.authorizeRequests()
                .authorizeHttpRequests(config -> config
                .requestMatchers("/login").permitAll()
                                .requestMatchers("/").permitAll()
                //어떤 요청에도 인증을 받음
                .anyRequest().authenticated()
                );


        http
                .oauth2Login(config -> config
                        .defaultSuccessUrl("/success")
                );
        return http.build();
    }


}
