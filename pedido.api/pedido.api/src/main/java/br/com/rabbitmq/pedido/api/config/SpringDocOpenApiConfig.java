package br.com.rabbitmq.pedido.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {
    @Bean
    public OpenAPI openAPI(){

        return new OpenAPI()
                .info(
                        new Info()
                                .title("Rest Api - Pedidos")
                                .description("Api para realizacao de pedidos")
                                .license(new License().name("Apache").url("https://www.apache.org/licenses/LICENSE-2.0"))
                );
    }
}
