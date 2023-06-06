package mlclover.appplication.config;


import mlclover.appplication.services.pedidos.EmailService;
import mlclover.appplication.services.pedidos.MockEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("test")
public class TestConfig {


    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }
}
