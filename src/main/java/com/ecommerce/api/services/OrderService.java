package com.ecommerce.api.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.api.domain.Order;
import com.ecommerce.api.domain.OrderItem;
import com.ecommerce.api.domain.Payment;
import com.ecommerce.api.domain.PaymentBankSlip;
import com.ecommerce.api.enums.PaymentState;
import com.ecommerce.api.repositories.OrderItemRepository;
import com.ecommerce.api.repositories.OrderRepository;
import com.ecommerce.api.repositories.PaymentRepository;
import com.ecommerce.api.repositories.ProductRepository;
import com.ecommerce.api.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private PaymentBankSlipService bankSlipService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderItemRepository itemRepository;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Order get(Integer id) {
		Optional<Order> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado"));
	}
	
	/**
	 * 
	 * @param order
	 * @return
	 */
	@Transactional
	public Order insert(Order order) {
		order.setId(null);
		order.setCreated_at(new Date());
		order.getPayment().setState(PaymentState.PENDING);
		order.getPayment().setOrder(order);
		
		// simulação de integração de um web service com serviço de boleto
		if(order.getPayment() instanceof PaymentBankSlip) {
			PaymentBankSlip bankSlip = (PaymentBankSlip) order.getPayment();
			bankSlipService.simulationWebService(bankSlip, order.getCreated_at());
		}
				
		order = repo.save(order); // insere no db o pedido
		paymentRepository.save(order.getPayment()); // insere no db os dados de pagamento
		
		for(OrderItem item : order.getItems()) {
			
			item.setDiscount(0.0);
			item.setPrice(productService.get(item.getProduct().getId()).getPrice());
			item.setOrder(order);
		}
		
		itemRepository.saveAll(order.getItems());
		
		return order;
	}
}
