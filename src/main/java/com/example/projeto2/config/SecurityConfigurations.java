package com.example.projeto2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       return http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .authorizeHttpRequests(authorize -> authorize
                       .requestMatchers(HttpMethod.POST, "/auth/*").permitAll()
                       .requestMatchers(HttpMethod.GET, "/auth/*").permitAll()
                       .requestMatchers(HttpMethod.GET, "/eventos").hasRole("userManager")
                       .requestMatchers(HttpMethod.POST, "/eventos").hasRole("userManager")
                       .requestMatchers(HttpMethod.GET, "/eventosOrganizador").hasRole("organizador")
                       .requestMatchers(HttpMethod.POST, "/eventosOrganizador").hasRole("organizador")
                       .requestMatchers(HttpMethod.GET, "/participanteEventos").hasRole("participante")
                       .requestMatchers(HttpMethod.POST, "/participanteEventos").hasRole("participante")
                       .anyRequest().permitAll()
               )
               .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
               .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
