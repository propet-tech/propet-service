package br.com.senai.propetservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@SecurityScheme(
    type = SecuritySchemeType.HTTP,
    name = "bearer",
    bearerFormat = "JWT",
    scheme = "bearer",
    in = SecuritySchemeIn.HEADER,
    description = """
        A JWT token is required to access this API,
        can be obtained from keycloak.
    """
)
@OpenAPIDefinition(
    security = {
        @SecurityRequirement(
            name = "bearer"
        )
    }
)
public class ProPetApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProPetApplication.class, args);
    }

}
