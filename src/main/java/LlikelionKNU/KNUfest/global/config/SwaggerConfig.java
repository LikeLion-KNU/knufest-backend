package LlikelionKNU.KNUfest.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI api(){
        Info info = new Info()
                .title("2024 경북대 대동제 안내 웹 BE Server")
                .version("v0.0.1")
                .description("2024 경북대 대동제 안내 웹사이트 BE 서버입니다.");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
