package com.joaodurante.springproject.services.validations;

import com.joaodurante.springproject.DTO.CustomerDTO;
import com.joaodurante.springproject.domain.Customer;
import com.joaodurante.springproject.repositories.CustomerRepository;
import com.joaodurante.springproject.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomerUpdateValidator implements ConstraintValidator<CustomerUpdate, CustomerDTO> {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void initialize(CustomerUpdate ann) {
    }

    @Override
    public boolean isValid(CustomerDTO obj, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer idUri = Integer.parseInt(map.get("id"));

        Customer customer = customerRepository.findByEmail(obj.getEmail());
        if(customer != null && !customer.getId().equals(idUri))
                list.add(new FieldMessage("Email", "This email is already in use."));

        for (FieldMessage x : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(x.getMessage())
                    .addPropertyNode(x.getField()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}