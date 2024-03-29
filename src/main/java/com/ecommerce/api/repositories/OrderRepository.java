package com.ecommerce.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.api.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

	
}
