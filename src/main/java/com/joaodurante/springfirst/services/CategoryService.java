package com.joaodurante.springfirst.services;

import com.joaodurante.springfirst.domain.Category;
import com.joaodurante.springfirst.repositories.CategoryRepository;

import com.joaodurante.springfirst.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repo;

    public Category find(Integer id){
        return this.repo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Object was not found using the id: " + id ));
    }
}
