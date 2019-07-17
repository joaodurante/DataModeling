package com.joaodurante.springfirst.resources;

import com.joaodurante.springfirst.domain.Demand;
import com.joaodurante.springfirst.services.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/demands")
public class DemandResource {
    @Autowired
    DemandService demandService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity list(@PathVariable Integer id){
        Demand obj = demandService.find(id);
        return ResponseEntity.ok().body(obj);
    }
}
