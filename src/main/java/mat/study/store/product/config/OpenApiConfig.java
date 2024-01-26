package mat.study.store.product.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        contact = @Contact(
            name = "MarketPlace - Pepito",
            email = "admin@pepito.com",
            url = "https://www.youtube.com/watch?v=2o_3hjUPAfQ"
        ),
        description = "OpenApi documentation for Spring Security",
        title = "OpenApi Specification - Products",
        version = "1.0",
        license = @License(
            name = "License name",
            url = "https://some-url.com"
        ),
        termsOfService = "Terms of service"
    ),
    servers = {
        @Server(
            description = "Local ENV",
            url = "http://localhost:8081"
        ),
        @Server(
            description = "PROD ENV",
            url = "http://localhost:8081"
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
