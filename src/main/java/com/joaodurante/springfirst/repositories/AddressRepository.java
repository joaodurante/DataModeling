package com.joaodurante.springfirst.repositories;

import com.joaodurante.springfirst.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
