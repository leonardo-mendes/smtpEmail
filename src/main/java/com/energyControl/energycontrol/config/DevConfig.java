package com.energyControl.energycontrol.config;

import com.energyControl.energycontrol.service.EmailService;
import com.energyControl.energycontrol.service.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }

}