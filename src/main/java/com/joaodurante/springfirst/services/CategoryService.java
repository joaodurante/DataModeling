package com.joaodurante.springfirst.services;

import com.joaodurante.springfirst.domain.Category;
import com.joaodurante.springfirst.repositories.CategoryRepository;

import com.joaodurante.springfirst.services.exceptions.DataIntegrityException;
import com.joaodurante.springfirst.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category find(Integer id){
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Object was not found using the id: " + id ));
    }

    public Category insert(Category obj){
        obj.setId(null);
        return this.categoryRepository.save(obj);
    }

    public Category update(Category obj){
        this.find(obj.getId());
        return this.categoryRepository.save(obj);
    }

    public void delete(Integer id){
        this.find(id);
        try{
            this.categoryRepository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityException("It's not possible to delete a category with products in it");
        }
    }
}
