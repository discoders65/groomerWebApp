package com.wjadczak.groomerWebApp.config.documentation;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "GroomerWebApp API specification",
                contact = @Contact(
                        name = "Wojciech Jadczak",
                        email = "w.jadczak@yahoo.com",
                        url = "https://github.com/w-jadczak"

                ),
                description = "OpenApi documentation for GroomerWebApp",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local dev server",
                        url = "http://localhost:8081"
                ),
                @Server(
                        description = "Production server",
                        url = "TBA"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
