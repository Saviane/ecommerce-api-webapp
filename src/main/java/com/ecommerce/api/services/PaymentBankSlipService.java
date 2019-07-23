package com.ecommerce.api.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.ecommerce.api.domain.PaymentBankSlip;

@Service
public class PaymentBankSlipService {

	/**
	 * 
	 * @param payment
	 * @param createdAtFromOrder
	 */
	public void simulationWebService(PaymentBankSlip payment, Date createdAtFromOrder) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(createdAtFromOrder);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		payment.setDueDate(cal.getTime());
	}
}
