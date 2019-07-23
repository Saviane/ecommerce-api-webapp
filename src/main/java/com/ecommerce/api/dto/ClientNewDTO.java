package com.ecommerce.api.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.ecommerce.api.services.validation.ClientInsert;

@ClientInsert
public class ClientNewDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preencimento obrigatório")
	@Length(min=5, message="O tamnho deve ser maior ou igual a 5 caracteres")
	private String name;
	
	@NotEmpty(message = "Preencimento obrigatório")
	@Email(message="E-mail invalido")
	private String email;
	
	@NotEmpty(message = "Preencimento obrigatório")
	private String documentNumber;
	
	private Integer clientType;
	
	@NotEmpty(message = "Preencimento obrigatório")
	private String street;
	
	@NotEmpty(message = "Preencimento obrigatório")
	private String number;
	
	private String complement;
	
	private String neighborhood;
	
	@NotEmpty(message = "Preencimento obrigatório")
	private String zipCode;
	
	@NotEmpty(message = "Preencimento obrigatório")
	private String tel1;
	
	private String tel2;
	
	private String tel3;
	
	private Integer city_id;
	
	public ClientNewDTO() {
		
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

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getTel3() {
		return tel3;
	}

	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}

	public Integer getCity_id() {
		return city_id;
	}

	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}
	
}
