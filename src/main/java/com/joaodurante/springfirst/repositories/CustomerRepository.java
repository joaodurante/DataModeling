package com.joaodurante.springfirst.repositories;

import com.joaodurante.springfirst.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
