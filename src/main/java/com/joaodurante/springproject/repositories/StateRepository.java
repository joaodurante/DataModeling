package com.joaodurante.springproject.repositories;

import com.joaodurante.springproject.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Integer> {
}
