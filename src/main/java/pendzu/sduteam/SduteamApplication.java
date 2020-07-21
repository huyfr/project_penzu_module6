package pendzu.sduteam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SduteamApplication {

    public static void main(String[] args) {
        SpringApplication.run(SduteamApplication.class, args);
    }

    //global config CORS
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.
                        addMapping("/api/sdu/**").
                        allowedOrigins("http://localhost:4200/").
                        allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE");
            }
        };
    }
}
