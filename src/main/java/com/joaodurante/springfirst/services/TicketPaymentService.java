package com.joaodurante.springfirst.services;

import com.joaodurante.springfirst.domain.TicketPayment;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TicketPaymentService {
    public void setTicketPayment(TicketPayment payment, Date time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        payment.setDuedate(calendar.getTime());
    }
}
