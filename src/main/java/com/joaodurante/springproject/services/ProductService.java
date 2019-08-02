package com.joaodurante.springproject.services;

import com.joaodurante.springproject.domain.Category;
import com.joaodurante.springproject.domain.Product;
import com.joaodurante.springproject.repositories.CategoryRepository;
import com.joaodurante.springproject.repositories.ProductRepository;
import com.joaodurante.springproject.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product find(Integer id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Object was not found using the id: " + id));
    }

    public Page<Product> search(String name, List<Integer> categoriesIds, Integer page, Integer size, String direction, String orderBy){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        List<Category> categories = categoryRepository.findAllById(categoriesIds);

        return productRepository.search(name, categories, pageRequest);
    }
}
