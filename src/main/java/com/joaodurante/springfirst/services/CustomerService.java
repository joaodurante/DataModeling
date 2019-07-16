package com.joaodurante.springfirst.services;

import com.joaodurante.springfirst.domain.Customer;
import com.joaodurante.springfirst.repositories.CustomerRepository;
import com.joaodurante.springfirst.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer find(Integer id){
        return this.customerRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Object was not found using the id: " + id));
    }
}
