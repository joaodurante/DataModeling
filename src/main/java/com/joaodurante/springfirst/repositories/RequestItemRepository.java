package com.joaodurante.springfirst.repositories;

import com.joaodurante.springfirst.domain.RequestItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestItemRepository extends JpaRepository<RequestItem, Integer> {
}
