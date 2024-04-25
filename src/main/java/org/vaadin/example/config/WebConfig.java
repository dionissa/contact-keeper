package org.vaadin.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Mapeia para todas as URLs
                .allowedOrigins("*") // Permite de qualquer origem
                .allowedMethods("*"); // Permite todos os métodos HTTP
    }
}
