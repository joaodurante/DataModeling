package com.joaodurante.springfirst.services;

import com.joaodurante.springfirst.DTO.CustomerDTO;
import com.joaodurante.springfirst.DTO.CustomerInsertDTO;
import com.joaodurante.springfirst.domain.Address;
import com.joaodurante.springfirst.domain.City;
import com.joaodurante.springfirst.domain.Customer;
import com.joaodurante.springfirst.domain.enums.CustomerType;
import com.joaodurante.springfirst.repositories.AddressRepository;
import com.joaodurante.springfirst.repositories.CustomerRepository;
import com.joaodurante.springfirst.services.exceptions.DataIntegrityException;
import com.joaodurante.springfirst.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer find(Integer id){
        return this.customerRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Object was not found using the id: " + id));
    }

    public Page<Customer> findPage(Integer page, Integer size, String direction, String orderBy){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return customerRepository.findAll(pageRequest);
    }

    public Customer insert(Customer obj){
        obj.setId(null);
        obj = this.customerRepository.save(obj);
        this.addressRepository.saveAll(obj.getAddress());
        return obj;
    }

    public Customer update(Customer obj){
        Customer newObj = this.find(obj.getId());
        updateData(newObj, obj);
        return this.customerRepository.save(newObj);
    }

    public void delete(Integer id){
        this.find(id);
        try{
            this.customerRepository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityException("It's not possible to delete a customer with demands in it");
        }
    }

    public Customer fromDTO(CustomerDTO obj){
        return new Customer(obj.getId(), obj.getName(), obj.getEmail(), null, null);
    }

    public Customer fromDTO(CustomerInsertDTO obj){
        Customer customer = new Customer(null, obj.getName(), obj.getEmail(), obj.getDocument(), CustomerType.toEnum(obj.getType()));
        City city = new City(obj.getCityId(), null, null);
        Address address = new Address(null, obj.getStreet(), obj.getNumber(), obj.getComplement(), obj.getDistrict(), obj.getZipCode(), customer, city);

        customer.getAddress().add(address);
        customer.getPhones().add(obj.getPhone());
        if(obj.getSecondaryPhone() != null)
            customer.getPhones().add(obj.getSecondaryPhone());

        return customer;
    }

    private void updateData(Customer newObj, Customer obj){
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }
}
