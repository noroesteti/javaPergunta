package com.example.javaPergunta.infra.springdoc;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
@Configuration
public class SpringDocConfiguration {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Java Perguntas API")
                        .version("v1")
                        .description("Perguntas comuns do Java")
                        .license(new License().name("Software proprietário")))
                .externalDocs( new ExternalDocumentation()
                        .description("java Pergunta")
                        .url("http://localhost:80/swagger-ui/index.html")
                ).tags(Arrays.asList(
                        new Tag().name("JavaPergunta").description("Response perguntas comuns do java")
                ));
    }

}
