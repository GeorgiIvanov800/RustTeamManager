package org.rtm.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Georgi Ivanov",
                        email = "Georgi_Ivanov92@pm.me"
                ),
                description = "OpenApi documentation for Spring security",
                title = "OpenApi specification - Georgi",
                version = "1.0",
                license = @License(
                        name = "MIT"
                )
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8088/api/v1"
                )
        }
)
//TODO: Include @Security and @SecurityScheme letter
public class OpenApiConfig {
}
