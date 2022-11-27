package br.com.senai.propetservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.senai.propetservice.config.properties.CorsOriginPatterns;

@Configuration
@EnableConfigurationProperties(CorsOriginPatterns.class)
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private CorsOriginPatterns corsOriginPattern;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedMethods("*")
            .allowedOriginPatterns(corsOriginPattern.getOriginPatterns())
            .allowCredentials(true);
    }
}
