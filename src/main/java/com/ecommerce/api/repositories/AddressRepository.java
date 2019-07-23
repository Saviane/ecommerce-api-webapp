package com.ecommerce.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.api.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

	
}
