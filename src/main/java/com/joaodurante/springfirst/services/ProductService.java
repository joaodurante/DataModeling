package com.joaodurante.springfirst.services;

import com.joaodurante.springfirst.domain.Product;
import com.joaodurante.springfirst.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public Product find(Integer id){
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can not find products with id " + id));
    }
}
