package com.joaodurante.springfirst.repositories;

import com.joaodurante.springfirst.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Integer> {
}
