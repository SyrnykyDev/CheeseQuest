package com.mykyda.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Дозволяємо всі шляхи API
                .allowedOrigins("http://localhost:3000") // Дозволяємо запити з React (або інший фронт)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Дозволені методи
                .allowedHeaders("*") // Дозволяємо всі заголовки
                .allowCredentials(true); // Дозволяємо cookies та авторизацію
    }
}
