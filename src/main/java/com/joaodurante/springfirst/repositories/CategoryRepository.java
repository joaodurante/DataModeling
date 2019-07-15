package com.joaodurante.springfirst.repositories;

import com.joaodurante.springfirst.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
// JpaRepository<Data type that will be accessed, Data type that identifies the data>
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
