package com.ecommerce.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.api.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

	/**
	 * Método para buscar por e-mail
	 * Implementação jpa data
	 * @param email
	 * @return
	 */
	@Transactional(readOnly=true) // fala para o db que a query é apenas leitura (para deixar mais rápido)
	Client findByEmail(String email);
}
