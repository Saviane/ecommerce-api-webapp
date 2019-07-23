package com.ecommerce.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.api.domain.Address;
import com.ecommerce.api.domain.City;
import com.ecommerce.api.domain.Client;
import com.ecommerce.api.dto.ClientDTO;
import com.ecommerce.api.dto.ClientNewDTO;
import com.ecommerce.api.enums.ClientType;
import com.ecommerce.api.repositories.AddressRepository;
import com.ecommerce.api.repositories.CityRepository;
import com.ecommerce.api.repositories.ClientRepository;
import com.ecommerce.api.services.exceptions.DataIntegrityException;
import com.ecommerce.api.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;

	@Autowired 
	private CityRepository cityRepo;
	
	@Autowired
	private AddressRepository addrRepo;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Client findById(Integer id) {
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
	}

	/**
	 * 
	 * @return
	 */
	public List<Client> findAll() {
		return repo.findAll();
	}
	
	/**
	 * 
	 * @param category
	 * @return
	 */
	@Transactional
	public Client insert(Client client) {
		client.setId(null);
		client = repo.save(client);
		addrRepo.saveAll(client.getAddresses());
		return client;
	}

	/**
	 * 
	 * @param client
	 * @return
	 */
	public Client update(Client client) {
	
		Client newClient = findById(client.getId());
		updateData(newClient, client);
		
		return repo.save(newClient);
	}

	/**
	 * 
	 * @param id
	 */
	public void delete(Integer id) {

		findById(id);

		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Not allowed");
		}
	}

	/**
	 * 
	 * @param page
	 * @param linesPerPage
	 * @param orderBy
	 * @param direction
	 * @return
	 */
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	/**
	 * 
	 * @param clientDTO
	 * @return
	 */
	public Client fromDTO(ClientDTO clientDTO) {
		return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail(), null, null);
	}
	
	/**
	 * 
	 * @param clientNewDTO
	 * @return
	 */
	public Client fromDTO(ClientNewDTO clientNewDTO) {
		Client cli = new Client(
				null,
				clientNewDTO.getName(), 
				clientNewDTO.getEmail(), 
				clientNewDTO.getDocumentNumber(), 
				ClientType.toEnum(clientNewDTO.getClientType())
		);
		
		City city = new City(clientNewDTO.getCity_id(), null, null);
		
		Address addr = new Address(
				null, 
				clientNewDTO.getStreet(), 
				clientNewDTO.getNumber(), 
				clientNewDTO.getComplement(), 
				clientNewDTO.getNeighborhood(),
				clientNewDTO.getZipCode(), city, cli);
		
		cli.getAddresses().add(addr);
		cli.getPhones().add(clientNewDTO.getTel1());
		
		if(clientNewDTO.getTel2() != null) {
			cli.getPhones().add(clientNewDTO.getTel2());
		}
		
		if(clientNewDTO.getTel3() != null) {
			cli.getPhones().add(clientNewDTO.getTel3());
		}
		
		return cli;
	}
	
	/**
	 * 
	 * @param newClient
	 * @param client
	 */
	private void updateData(Client newClient, Client client) {
		newClient.setName(client.getName());
		newClient.setEmail(client.getEmail());
	}
}
