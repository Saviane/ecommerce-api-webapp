package com.ecommerce.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ecommerce.api.domain.Category;
import com.ecommerce.api.domain.Product;
import com.ecommerce.api.repositories.CategoryRepository;
import com.ecommerce.api.repositories.ProductRepository;
import com.ecommerce.api.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Product get(Integer id) {
		Optional<Product> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado"));
	}
	
	/**
	 * 
	 * @param name
	 * @param ids
	 * @param page
	 * @param linesPerPage
	 * @param orderBy
	 * @param direction
	 * @return
	 */
	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		List<Category> categories = categoryRepository.findAllById(ids);
		
		return repo.search(name, categories, pageRequest);
		
	}
	
}
