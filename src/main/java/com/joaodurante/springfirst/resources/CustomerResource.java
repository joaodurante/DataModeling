package com.joaodurante.springfirst.resources;

import com.joaodurante.springfirst.domain.Customer;
import com.joaodurante.springfirst.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customers")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity find(@PathVariable Integer id){
        Customer obj = this.customerService.find(id);
        return ResponseEntity.ok().body(obj);
    }
}
