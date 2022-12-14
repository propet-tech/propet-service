package br.com.senai.propetservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI configOpenApi() {
        return new OpenAPI().info(
            new Info()
                .title("ProPet Service API")
                .description("Backend of ProPet System")
                .version("v1")
                .license(
                    new License()
                        .name("GPL v3")
                        .url("https://www.gnu.org/licenses/gpl-3.0.txt")
                )
        );
    }
}
