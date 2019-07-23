package com.ecommerce.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ecommerce.api.domain.Category;
import com.ecommerce.api.dto.CategoryDTO;
import com.ecommerce.api.repositories.CategoryRepository;
import com.ecommerce.api.services.exceptions.DataIntegrityException;
import com.ecommerce.api.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Category findById(Integer id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Category not found"));
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Category> findAll(){
		return repo.findAll();
	}

	/**
	 * 
	 * @param category
	 * @return
	 */
	public Category insert(Category category) {
		category.setId(null);
		return repo.save(category);
	}
	
	/**
	 * 
	 * @param client
	 * @return
	 */
	public Category update(Category category) {
	
		Category newCategory = findById(category.getId());
		updateData(newCategory, category);
		
		return repo.save(newCategory);
	}

	/**
	 * 
	 * @param id
	 */
	public void delete(Integer id) {

		findById(id);

		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Not allowed");
		}
	}
	
	/**
	 * 
	 * @param page
	 * @param linesPerPage
	 * @param orderBy
	 * @param direction
	 * @return
	 */
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	/**
	 * 
	 * @param categoryDTO
	 * @return
	 */
	public Category fromDTO(CategoryDTO categoryDTO) {
		return new Category(categoryDTO.getId(), categoryDTO.getName());
	}
	
	/**
	 * 
	 * @param newClient
	 * @param client
	 */
	private void updateData(Category newCategory, Category category) {
		newCategory.setName(category.getName());
	}

}