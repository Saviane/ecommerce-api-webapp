package com.ecommerce.api.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.ecommerce.api.domain.Client;
import com.ecommerce.api.services.validation.ClientUpdate;

@ClientUpdate
public class ClientDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Preencimento obrigatório")
	@Length(min=5, message="O tamnho deve ser maior ou igual a 5 caracteres")
	private String name;

	@NotEmpty(message = "Preencimento obrigatório")
	@Email(message="E-mail invalido")
	private String email;

	public ClientDTO() {

	}

	/**
	 * 
	 * @param client
	 */
	public ClientDTO(Client client) {
		id = client.getId();
		name = client.getName();
		email = client.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
