package br.com.senai.propetservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
    jsr250Enabled = true,
    prePostEnabled = true
)
public class WebConfigSecurity {

    @Autowired
    private JwtRoleConveter jwtRoleConveter;
   
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Get User roles from Jwt
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwtRoleConveter);

        // Disable Cors and csrf
        http.cors(cors -> cors.disable());
        http.csrf().disable();

        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/api/swagger-ui/**").permitAll();
            auth.requestMatchers("/api/v3/api-docs/**").permitAll();
            auth.requestMatchers("/api/ws").permitAll();
            auth.anyRequest().authenticated();
        });

        http.oauth2ResourceServer(
            oauth -> {
                oauth.jwt()
                    .jwtAuthenticationConverter(converter);
            }
        );

        return http.build();
    }
}
