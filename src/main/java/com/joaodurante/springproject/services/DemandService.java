package com.joaodurante.springproject.services;

import com.joaodurante.springproject.domain.Demand;
import com.joaodurante.springproject.domain.DemandItem;
import com.joaodurante.springproject.domain.TicketPayment;
import com.joaodurante.springproject.domain.enums.PaymentState;
import com.joaodurante.springproject.repositories.DemandItemRepository;
import com.joaodurante.springproject.repositories.DemandRepository;
import com.joaodurante.springproject.repositories.PaymentRepository;
import com.joaodurante.springproject.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class DemandService {
    @Autowired
    private DemandRepository demandRepository;

    @Autowired
    private TicketPaymentService ticketPaymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private DemandItemRepository demandItemRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmailService emailService;

    public Demand find(Integer id){
        return demandRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Object was not found using the id: " + id ));
    }

    @Transactional
    public Demand insert(Demand obj){
        obj.setId(null);
        obj.setTime(new Date());
        obj.setCustomer(customerService.find(obj.getCustomer().getId()));
        obj.getPayment().setState(PaymentState.PENDING);
        obj.getPayment().setDemand(obj);

        if(obj.getPayment() instanceof TicketPayment){
            TicketPayment payment = (TicketPayment) obj.getPayment();
            ticketPaymentService.setTicketPayment(payment, obj.getTime());
        }
        obj = demandRepository.save(obj);
        paymentRepository.save(obj.getPayment());

        for(DemandItem i : obj.getDemandItems()){
            i.setDiscount(0.0);
            i.setProduct(productService.find(i.getProduct().getId()));
            i.setPrice(i.getProduct().getPrice());
            i.setDemand(obj);
        }
        demandItemRepository.saveAll(obj.getDemandItems());
        emailService.sendDemandConfirmationHtml(obj);
        return obj;
    }
}
