package com.ecommerce.api.enums;

public enum ClientType {

	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String description;
	
	private ClientType(int cod, String description) {
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
	public static ClientType toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(ClientType x : ClientType.values()) {
			
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("cod not found: " + cod);
	}
}
