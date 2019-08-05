package com.joaodurante.springproject.services;

import com.joaodurante.springproject.domain.Demand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService{
    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

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

    protected String htmlFromTemplateDemand(Demand demand){
        Context context = new Context();
        context.setVariable("demand", demand);
        return templateEngine.process("email/demandConfirmation", context);
    }

    @Override
    public void sendDemandConfirmationHtml(Demand demand){
        try{
            MimeMessage mimeMessage = prepareMimeMessageFromDemand(demand);
            sendHtmlEmail(mimeMessage);
        }catch(MessagingException e){
            sendDemandConfirmation(demand);
        }
    }

    protected MimeMessage prepareMimeMessageFromDemand(Demand demand) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(demand.getCustomer().getEmail());
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setSubject("Demand " + demand.getId() + " has been confirmed!");
        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
        mimeMessageHelper.setText(htmlFromTemplateDemand(demand), true);

        return mimeMessage;
    }
}
