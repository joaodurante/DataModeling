package com.joaodurante.springproject.repositories;

import com.joaodurante.springproject.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
