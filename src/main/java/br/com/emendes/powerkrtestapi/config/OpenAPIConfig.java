package br.com.emendes.powerkrtestapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static br.com.emendes.powerkrtestapi.util.constant.ApplicationConstants.SECURITY_SCHEME_KEY;

/**
 * Classe de configuraçao do Springdoc OpenAPI
 */
@Configuration
public class OpenAPIConfig {

  @Bean
  public OpenAPI openAPI() {
    Contact contact = new Contact()
        .name("Edson Mendes")
        .email("edson.luiz.mendes@hotmail.com")
        .url("https://github.com/Edson-Mendes");

    Info info = new Info()
        .title("PowerKR Test API")
        .description("REST API para avaliação de desenvolvedor Backend Java na PowerKR.")
        .version("v1.0")
        .contact(contact);

    SecurityScheme securityScheme = new SecurityScheme()
        .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT");

    return new OpenAPI()
        .info(info)
        .components(new Components().addSecuritySchemes(SECURITY_SCHEME_KEY, securityScheme));
  }

}
