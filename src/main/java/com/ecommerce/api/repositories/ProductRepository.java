package com.ecommerce.api.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.api.domain.Category;
import com.ecommerce.api.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	/**
	 * 
	 * @param name
	 * @param categories
	 * @param pageRequest
	 * @return
	 */
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cat WHERE obj.name LIKE %:name% AND cat IN :categories")
	Page<Product> search(@Param("name") String name, @Param("categories") List<Category> categories, Pageable pageRequest);
	
}
