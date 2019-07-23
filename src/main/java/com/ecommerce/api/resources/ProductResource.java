package com.ecommerce.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.domain.Product;
import com.ecommerce.api.dto.ProductDTO;
import com.ecommerce.api.resources.utils.URL;
import com.ecommerce.api.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	/**
	 * 
	 */
	@Autowired
	private ProductService service;

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Product obj = service.get(id);
		return ResponseEntity.ok().body(obj);
	}
	
	/**
	 * 
	 * @param page
	 * @param linesPerPage
	 * @param orderBy
	 * @param direction
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProductDTO>> findPage(
			@RequestParam(value="name", defaultValue="") String name, 
			@RequestParam(value="categories", defaultValue="") String categories, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {

		String nameDecoded = URL.decodeParam(name);
		
		List<Integer> ids = URL.decodeIntList(categories);
		
		Page<Product> products = service.search(nameDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProductDTO> listDTO = products.map(obj -> new ProductDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}

}
