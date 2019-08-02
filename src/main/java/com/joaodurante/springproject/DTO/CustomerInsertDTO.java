package com.joaodurante.springproject.DTO;

import com.joaodurante.springproject.services.validations.CustomerInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@CustomerInsert
public class CustomerInsertDTO implements Serializable {

    @NotEmpty(message = "Required field")
    @Length(min = 3, max = 40, message = "The size must be between 3 and 40")
    private String name;

    @NotEmpty(message = "Required field")
    @Email(message = "Invalid email")
    private String email;

    @NotEmpty(message = "Required field")
    private String document;

    private Integer type;

    @NotEmpty(message = "Required field")
    private String street;

    @NotEmpty(message = "Required field")
    private String number;

    private String complement;

    @NotEmpty(message = "Required field")
    private String district;

    @NotEmpty(message = "Required field")
    private String zipCode;

    @NotEmpty(message = "Required field")
    private String phone;

    private String secondaryPhone;

    private Integer cityId;

    public CustomerInsertDTO(){}

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

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSecondaryPhone() {
        return secondaryPhone;
    }

    public void setSecondaryPhone(String secondaryPhone) {
        this.secondaryPhone = secondaryPhone;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
