package com.ecommerce.api.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ecommerce.api.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;
	
	/**
	 * método para iniciar o db de dev/teste
	 * 
	 * @return
	 * @throws ParseException
	 */
	@Bean
	public boolean instatiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();
		return true;
	}
	
}
