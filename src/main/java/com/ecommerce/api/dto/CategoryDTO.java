package com.ecommerce.api.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.ecommerce.api.domain.Category;

public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preencimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String name;
	
	/**
	 * 
	 */
	public CategoryDTO() {
		
	}
	
	/**
	 * 
	 * @param category
	 */
	public CategoryDTO(Category category) {
		id = category.getId();
		name = category.getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
