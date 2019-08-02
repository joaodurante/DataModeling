package com.joaodurante.springproject.repositories;


import com.joaodurante.springproject.domain.Demand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandRepository extends JpaRepository<Demand, Integer> {
}
