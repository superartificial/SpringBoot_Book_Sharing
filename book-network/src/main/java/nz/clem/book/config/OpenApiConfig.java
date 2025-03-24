package nz.clem.book.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info (
                contact = @Contact(
                        name = "Clem",
                        email = "contact@clem.nz",
                        url = "http://clem.nz"
                ),
                description = "OpenApi Documentation for Spring security",
                title = "OpenApi specification",
                version = "1.0",
                license = @License (
                        name = "License name",
                        url = "http://clem.nz/info"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local Environment"
                )
        }
)
public class OpenApiConfig {
}
