package com.joaodurante.springfirst.repositories;

import com.joaodurante.springfirst.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
}
