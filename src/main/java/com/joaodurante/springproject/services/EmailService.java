package com.joaodurante.springproject.services;

import com.joaodurante.springproject.domain.Demand;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendDemandConfirmation(Demand demand);

    void sendEmail(SimpleMailMessage msg);
}
