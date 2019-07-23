package com.ecommerce.api.enums;

public enum PaymentState {

	OK(1, "OK"),
	PENDING(2, "Pendente"),
	CANCELED(3, "Cancelado");
	
	private int cod;
	private String description;
	
	private PaymentState(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}
	
	/**
	 * 
	 * @param cod
	 * @return
	 */
	public static PaymentState toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(PaymentState x : PaymentState.values()) {
			
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("[PaymentState] cod not found: " + cod);
	}
	
}
