package com.joaodurante.springfirst.resources;

import com.joaodurante.springfirst.domain.Product;
import com.joaodurante.springfirst.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/products")
public class ProductResource {

    @Autowired
    private ProductService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity list(@PathVariable Integer id){
        Product obj = this.service.find(id);
        return ResponseEntity.ok().body(obj);
    }


}
