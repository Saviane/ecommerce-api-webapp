package com.ecommerce.api.domain;

import javax.persistence.Entity;

import com.ecommerce.api.enums.PaymentState;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("paymentWithCard")
public class PaymentCard extends Payment{

	private static final long serialVersionUID = 1L;
	private Integer installmentsNumber;
	
	/**
	 * 
	 */
	public PaymentCard() {
	
	}

	/**
	 * 
	 * @param id
	 * @param state
	 * @param order
	 */
	public PaymentCard(Integer id, PaymentState state, Order order, Integer installmentsNumber) {
		super(id, state, order);
		this.installmentsNumber = installmentsNumber;
	}

	public Integer getInstallmentsNumber() {
		return installmentsNumber;
	}

	public void setInstallmentsNumber(Integer installmentsNumber) {
		this.installmentsNumber = installmentsNumber;
	}

	
	
}
