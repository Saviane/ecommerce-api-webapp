package com.ecommerce.api.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.ecommerce.api.domain.Client;
import com.ecommerce.api.dto.ClientDTO;
import com.ecommerce.api.repositories.ClientRepository;
import com.ecommerce.api.resources.exception.FieldMessage;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO>{

	// responsável por trazer os dados do request
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClientRepository repo;
	
	@Override
	public void initialize(ClientUpdate ann) {
		
	}
	
	@Override
	public boolean isValid(ClientDTO objDTO, ConstraintValidatorContext context) {
		
		// transforma os dados do request em um map key/value
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
			
		// verifica se o email que está sendo inserido já existe no db
		Client aux = repo.findByEmail(objDTO.getEmail());
		if(aux !=null && !aux.getId().equals(uriId)) {
			// se existir, adicionar um erro na validação
			list.add(new FieldMessage("email", "Email já existente"));
		}
				
		for(FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		
		return list.isEmpty();
	}

}
