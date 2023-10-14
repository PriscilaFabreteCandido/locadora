package br.com.locadora.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")            // Permitir origens específicas
                .allowedMethods("GET", "POST", "PUT", "DELETE")   // Métodos permitidos
                .allowedHeaders("*");                             // Headers permitidos
    }
}
