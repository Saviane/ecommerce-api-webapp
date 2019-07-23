package com.ecommerce.api.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.api.domain.Client;
import com.ecommerce.api.dto.ClientNewDTO;
import com.ecommerce.api.enums.ClientType;
import com.ecommerce.api.repositories.ClientRepository;
import com.ecommerce.api.resources.exception.FieldMessage;
import com.ecommerce.api.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO>{

	@Autowired
	private ClientRepository repo;
	
	@Override
	public void initialize(ClientInsert ann) {
		
	}
	
	@Override
	public boolean isValid(ClientNewDTO objDTO, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		// validação de CPF
		if(objDTO.getClientType().equals(ClientType.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDTO.getDocumentNumber())) {
			list.add(new FieldMessage("documentNumber", "invalid CPF"));
		}
		
		// validação de cnpj
		if(objDTO.getClientType().equals(ClientType.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDTO.getDocumentNumber())) {
			list.add(new FieldMessage("documentNumber", "invalid CNPJ"));
		}
		
		// verifica se o email que está sendo inserido já existe no db
		Client aux = repo.findByEmail(objDTO.getEmail());
		if(aux !=null) {
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
