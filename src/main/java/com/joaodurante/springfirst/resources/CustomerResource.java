package com.joaodurante.springfirst.resources;

import com.joaodurante.springfirst.DTO.CustomerDTO;
import com.joaodurante.springfirst.domain.Customer;
import com.joaodurante.springfirst.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/customers")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDTO>> findAll(){
        List<Customer> list = customerService.findAll();
        List<CustomerDTO> listDTO = list.stream().map(elem -> new CustomerDTO(elem)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CustomerDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "24") Integer size,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    ){
        Page<Customer> list = customerService.findPage(page, size, direction, orderBy);
        Page<CustomerDTO> listDTO = list.map(elem -> new CustomerDTO(elem));
        return ResponseEntity.ok().body(listDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> find(@PathVariable Integer id){
        Customer obj = this.customerService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CustomerDTO objDTO, @PathVariable Integer id){
        Customer obj = customerService.fromDTO(objDTO);
        obj.setId(id);
        obj = customerService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Integer id){
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
