package grupin.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI openAPIConfig() {
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        Info info = new Info();
        info.title("Grupin API REST").description("Api criada pelo voluntário Lucas Mateus para gerenciar o projeto")
                .version("v1 beta")
                .contact(new Contact().name("Lucas Mateus").email("lucas.jdev1@gmail.com")
                        .url("https://lucas-dev-back.github.io/portfolio-site/"))
                .license(new License().name("Licença de software").url("https://www.apache.org/licenses/LICENSE-2.0"))
                .termsOfService(
                        "O código disposto no github pode ser distribuído, desde que esteja com menções ao autor do software, a menção deve ser atribuída ao grupin que detêm da propriedade intelectual e ao desenvolvedor");
        return info;
    }
}
