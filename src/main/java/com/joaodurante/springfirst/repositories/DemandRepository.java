package com.joaodurante.springfirst.repositories;


import com.joaodurante.springfirst.domain.Demand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandRepository extends JpaRepository<Demand, Integer> {
}
