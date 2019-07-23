package com.joaodurante.springfirst.DTO;

import com.joaodurante.springfirst.domain.Category;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoryDTO implements Serializable {
    private Integer id;

    @NotEmpty(message = "Required field")
    @Length(min=4, max=20, message = "The size must be between 4 and 20")
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
