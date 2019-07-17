package com.joaodurante.springfirst.services;

import com.joaodurante.springfirst.domain.Demand;
import com.joaodurante.springfirst.repositories.DemandRepository;
import com.joaodurante.springfirst.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemandService {
    @Autowired
    private DemandRepository demandRepository;

    public Demand find(Integer id){
        return demandRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Object was not found using the id: " + id ));
    }
}
