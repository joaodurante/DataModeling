package com.joaodurante.springfirst.repositories;

import com.joaodurante.springfirst.domain.DemandItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandItemRepository extends JpaRepository<DemandItem, Integer> {
}
