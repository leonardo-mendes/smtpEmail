package com.energyControl.energycontrol.service;

import com.energyControl.energycontrol.domains.Consume;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendConsumeConfirmationEmail (Consume p);

    void sendEmail (SimpleMailMessage msg);
}
