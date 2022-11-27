package br.com.senai.propetservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebConfigSecurity {
   
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();

        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests(
            auth -> {
                auth.antMatchers("/api/swagger-ui/**").permitAll();
                auth.antMatchers("/api/v3/api-docs/**").permitAll();
                auth.anyRequest().authenticated();
            }
        );

        http.oauth2ResourceServer(
            oauth -> {
                oauth.jwt();
            }
        );

        return http.build();
    }
}
