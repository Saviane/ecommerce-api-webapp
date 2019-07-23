package com.ecommerce.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.ecommerce.api.domain.PaymentBankSlip;
import com.ecommerce.api.domain.PaymentCard;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JackonConfig {

	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PaymentCard.class);
				objectMapper.registerSubtypes(PaymentBankSlip.class);
				super.configure(objectMapper);
			};
		};
		return builder;
	}
}
