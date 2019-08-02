package com.joaodurante.springproject.services;

import com.joaodurante.springproject.DTO.CategoryDTO;
import com.joaodurante.springproject.domain.Category;
import com.joaodurante.springproject.repositories.CategoryRepository;

import com.joaodurante.springproject.services.exceptions.DataIntegrityException;
import com.joaodurante.springproject.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public Page<Category> findPage(Integer page, Integer size, String direction, String orderBy){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return categoryRepository.findAll(pageRequest);
    }

    public Category insert(Category obj){
        obj.setId(null);
        return this.categoryRepository.save(obj);
    }

    public Category update(Category obj){
        Category newObj = this.find(obj.getId());
        updateData(newObj, obj);
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

    public Category fromDTO(CategoryDTO obj){
        return new Category(obj.getId(), obj.getName());
    }

    private void updateData(Category newObj, Category obj){
        newObj.setName(obj.getName());
    }
}
