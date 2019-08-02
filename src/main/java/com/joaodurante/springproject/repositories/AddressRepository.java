package com.joaodurante.springproject.repositories;

import com.joaodurante.springproject.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
