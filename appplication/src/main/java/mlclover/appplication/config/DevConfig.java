package mlclover.appplication.config;


import mlclover.appplication.services.pedidos.EmailService;
import mlclover.appplication.services.pedidos.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@Profile("dev")
public class DevConfig {

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }

    // permite acesso de outras aplicações configurando o CORS
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

}
