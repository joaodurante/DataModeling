package com.joaodurante.springfirst.repositories;

import com.joaodurante.springfirst.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
