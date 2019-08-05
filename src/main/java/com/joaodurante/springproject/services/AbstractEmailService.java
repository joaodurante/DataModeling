package com.joaodurante.springproject.services;

import com.joaodurante.springproject.domain.Demand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService{
    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendDemandConfirmation(Demand demand) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromDemand(demand);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromDemand(Demand demand){
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(demand.getCustomer().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Demand " + demand.getId() + " has been confirmed!");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(demand.toString());

        return sm;
    }
}
