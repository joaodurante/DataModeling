package com.joaodurante.springfirst.DTO;

import com.joaodurante.springfirst.domain.Category;

import java.io.Serializable;

public class CategoryDTO implements Serializable {
    private Integer id;
    private String name;

    public CategoryDTO(){}
    public CategoryDTO(Category obj){
        this.id = obj.getId();
        this.name = obj.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
