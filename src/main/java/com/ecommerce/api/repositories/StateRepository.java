package com.ecommerce.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.api.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{

	
}
