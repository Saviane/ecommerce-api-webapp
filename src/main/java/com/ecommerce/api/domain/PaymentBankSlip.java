package com.ecommerce.api.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.ecommerce.api.enums.PaymentState;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("paymentWithBankSplip")
public class PaymentBankSlip extends Payment{

	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date dueDate;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date paymentDate;
	
	/**
	 * 
	 */
	public PaymentBankSlip() {

	}

	/**
	 * 
	 * @param id
	 * @param state
	 * @param order
	 * @param dueDate
	 * @param paymentDate
	 */
	public PaymentBankSlip(Integer id, PaymentState state, Order order, Date dueDate, Date paymentDate) {
		super(id, state, order);
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
}
