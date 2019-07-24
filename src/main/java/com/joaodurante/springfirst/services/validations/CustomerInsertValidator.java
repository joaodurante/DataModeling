package com.joaodurante.springfirst.services.validations;

import com.joaodurante.springfirst.DTO.CustomerInsertDTO;
import com.joaodurante.springfirst.domain.enums.CustomerType;
import com.joaodurante.springfirst.resources.exceptions.FieldMessage;
import com.joaodurante.springfirst.services.validations.utils.DocumentUtil;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomerInsertValidator implements ConstraintValidator<CustomerInsert, CustomerInsertDTO> {
    @Override
    public void initialize(CustomerInsert ann) {
    }

    @Override
    public boolean isValid(CustomerInsertDTO obj, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if(obj.getType().equals(CustomerType.PHYSICALPERSON.getCode()) && !DocumentUtil.isValidSsn(obj.getDocument()))
            list.add(new FieldMessage("Document", "Invalid CPF"));

        else if(obj.getType().equals(CustomerType.LEGALENTITY.getCode()) && !DocumentUtil.isValidTin(obj.getDocument()))
            list.add(new FieldMessage("Document", "Invalid CNPJ"));


        for (FieldMessage x : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(x.getMessage())
                    .addPropertyNode(x.getField()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
