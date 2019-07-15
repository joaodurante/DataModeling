package com.joaodurante.springfirst.resources;

import com.joaodurante.springfirst.domain.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Category> list(){
        Category cat1 = new Category(1, "Technology");
        Category cat2 = new Category(2, "Kids");
        Category cat3 = new Category(3, "Sport");

        List<Category> list = new ArrayList<>();
        list.add(cat1);
        list.add(cat2);
        list.add(cat3);

        return list;
    }
}
