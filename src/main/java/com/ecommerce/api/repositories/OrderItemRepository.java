package com.ecommerce.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.api.domain.OrderItem;;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

	
}
