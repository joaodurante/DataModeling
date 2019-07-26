package com.joaodurante.springfirst.resources;

import com.joaodurante.springfirst.domain.Demand;
import com.joaodurante.springfirst.services.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/demands")
public class DemandResource {
    @Autowired
    DemandService demandService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Demand> find(@PathVariable Integer id){
        Demand obj = demandService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Demand obj){
        obj = demandService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
