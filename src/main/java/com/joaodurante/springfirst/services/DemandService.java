package com.joaodurante.springfirst.services;

import com.joaodurante.springfirst.domain.Demand;
import com.joaodurante.springfirst.domain.DemandItem;
import com.joaodurante.springfirst.domain.TicketPayment;
import com.joaodurante.springfirst.domain.enums.PaymentState;
import com.joaodurante.springfirst.repositories.DemandItemRepository;
import com.joaodurante.springfirst.repositories.DemandRepository;
import com.joaodurante.springfirst.repositories.PaymentRepository;
import com.joaodurante.springfirst.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Demand find(Integer id){
        return demandRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Object was not found using the id: " + id ));
    }

    public Demand insert(Demand obj){
        obj.setId(null);
        obj.setTime(new Date());
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
            i.setPrice(productService.find(i.getProduct().getId()).getPrice());
            i.setDemand(obj);
        }
        demandItemRepository.saveAll(obj.getDemandItems());
        return obj;
    }
}
