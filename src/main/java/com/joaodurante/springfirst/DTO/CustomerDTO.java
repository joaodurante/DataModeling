package com.joaodurante.springfirst.DTO;

import com.joaodurante.springfirst.domain.Customer;
import com.joaodurante.springfirst.services.validations.CustomerUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@CustomerUpdate
public class CustomerDTO implements Serializable {
    private Integer id;

    @NotEmpty(message = "Required field")
    @Length(min = 3, max = 40, message = "The size must be between 3 and 40")
    private String name;

    @NotEmpty(message = "Required field")
    @Email(message = "Invalid email")
    private String email;

    public CustomerDTO(){}
    public CustomerDTO(Customer customer){
        this.id = customer.getId();
        this.name = customer.getName();
        this.email = customer.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
