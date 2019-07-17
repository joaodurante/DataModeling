package com.joaodurante.springfirst.repositories;

import com.joaodurante.springfirst.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
