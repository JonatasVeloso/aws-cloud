package br.com.aws.cloud.distance.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("AWS Cloud API")
                        .description("API para calcular distância em linha reta entre dois pontos de latitude e longitude.")
                        .version("1.0.0"));
    }
}