package com.joaodurante.springfirst.services;

import com.joaodurante.springfirst.domain.Category;
import com.joaodurante.springfirst.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repo;

    public Category find(Integer id){
        return this.repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can not find categories with id " + id));
    }
}
