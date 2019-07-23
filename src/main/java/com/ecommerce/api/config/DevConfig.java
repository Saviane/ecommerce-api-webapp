package com.ecommerce.api.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ecommerce.api.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String stragety;
	
	/**
	 * método para iniciar o db de dev/teste
	 * 
	 * @return
	 * @throws ParseException
	 */
	@Bean
	public boolean instatiateDatabase() throws ParseException {
		
		// verificação se a configuração do propoerties (spring.jpa.hibernate.ddl-auto) está como create
		if(!"create".equals(stragety)) {
			return false;
		}
		dbService.instantiateTestDatabase();
		return true;
	}
	
}
