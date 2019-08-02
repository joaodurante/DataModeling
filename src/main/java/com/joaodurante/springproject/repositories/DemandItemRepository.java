package com.joaodurante.springproject.repositories;

import com.joaodurante.springproject.domain.DemandItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandItemRepository extends JpaRepository<DemandItem, Integer> {
}
