package com.ecommerce.configuracao;

// Importa classes necessárias para configuração do Spring e CORS
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

// Indica que esta classe é uma classe de configuração do Spring
@Configuration
public class ConfiguracaoCors {

    // Define um Bean que será gerenciado pelo Spring (um filtro de CORS)
    @Bean
    public CorsFilter corsFilter() {

        // Cria a fonte de configuração baseada em URLs
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // Cria um objeto de configuração CORS
        CorsConfiguration config = new CorsConfiguration();

        // Permite requisições de qualquer origem (frontend, etc)
        config.addAllowedOriginPattern("*");

        // Permite qualquer tipo de header (Authorization, etc)
        config.addAllowedHeader("*");

        // Permite todos os métodos HTTP (GET, POST, PUT, DELETE, etc)
        config.addAllowedMethod("*");

        // Permite envio de credenciais (cookies, tokens de autenticação)
        config.setAllowCredentials(true);

        // Aplica essa configuração para todas as rotas que começam com /api/
        source.registerCorsConfiguration("/api/**", config);

        // Retorna o filtro CORS configurado
        return new CorsFilter(source);
    }
}