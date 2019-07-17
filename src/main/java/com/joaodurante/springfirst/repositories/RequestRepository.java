package com.joaodurante.springfirst.repositories;

import com.joaodurante.springfirst.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Integer> {
}
