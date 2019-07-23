package com.ecommerce.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.api.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{

	
}
