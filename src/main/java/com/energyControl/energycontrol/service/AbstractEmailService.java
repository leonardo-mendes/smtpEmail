package com.energyControl.energycontrol.service;

import com.energyControl.energycontrol.domains.Consume;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService{

    private String sender = "energycontrol03@gmail.com";

    @Override
    public void sendConsumeConfirmationEmail (Consume obj) {
        SimpleMailMessage msg = prepareSimpleMailMessageFromConsume(obj);
        sendEmail(msg);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromConsume(Consume obj) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(obj.getUser().getEmail());
        msg.setFrom(this.sender);
        msg.setSubject("Consume Client");
        msg.setSentDate(new Date(System.currentTimeMillis()));
        msg.setText(obj.toString());
        return msg;
    }

}