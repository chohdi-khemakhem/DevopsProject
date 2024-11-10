package tn.esprit.tpfoyer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        String allowedOrigin = System.getenv("ALLOWED_ORIGIN");
        if (allowedOrigin != null && !allowedOrigin.isEmpty()) {
            config.setAllowedOrigins(Arrays.asList(allowedOrigin.split(",")));
        } else {
            config.setAllowedOrigins(Arrays.asList("http://192.168.50.4", "http://localhost:3000"));
        }
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
