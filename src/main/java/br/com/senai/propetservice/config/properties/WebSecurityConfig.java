package br.com.senai.propetservice.config.properties;

import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSecurityConfig {
    
    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     http.authorizeRequests()
    //         .anyRequest().authenticated()
    //         .and().oauth2ResourceServer(oauth2 -> oauth2.jwt());
    //     return http.build();
    // }
}
