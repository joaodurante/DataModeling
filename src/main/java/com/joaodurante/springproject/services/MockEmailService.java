package com.joaodurante.springproject.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;


public class MockEmailService extends AbstractEmailService {
    private static final Logger logger = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        logger.info("Sending email simulation!");
        logger.info(msg.toString());
        logger.info("Email sent!");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        logger.info("Sending html email simulation!");
        logger.info(msg.toString());
        logger.info("Email sent!");
    }
}
