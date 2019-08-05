package com.joaodurante.springproject.services;

import com.joaodurante.springproject.domain.Demand;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {
    void sendDemandConfirmation(Demand demand);
    void sendEmail(SimpleMailMessage msg);

    void sendDemandConfirmationHtml(Demand demand);
    void sendHtmlEmail(MimeMessage msg);
}
