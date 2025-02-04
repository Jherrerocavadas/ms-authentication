package br.com.jherrerocavadas.msauthentication.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
public class SwaggerConfig {
    @Value("${application.name:}")
    private String applicationName;

    @Value("${application.description:}")
    private String applicationDescription;

    @Value("${application.version:}")
    private String applicationVersion;

    @Bean
    public OpenAPI apiConfig() {
        return new OpenAPI()
                .info(new Info().title(applicationName)
                        .description(new String(applicationDescription.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8))
                        .version(applicationVersion)
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Jherrerocavadas Github")
                        .url("https://github.com/JHerrerocavadas"));
    }
}
