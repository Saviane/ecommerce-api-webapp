package com.ecommerce.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.api.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	
}
