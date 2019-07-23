package com.ecommerce.api.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.api.domain.Address;
import com.ecommerce.api.domain.Category;
import com.ecommerce.api.domain.City;
import com.ecommerce.api.domain.Client;
import com.ecommerce.api.domain.Order;
import com.ecommerce.api.domain.OrderItem;
import com.ecommerce.api.domain.Payment;
import com.ecommerce.api.domain.PaymentBankSlip;
import com.ecommerce.api.domain.PaymentCard;
import com.ecommerce.api.domain.Product;
import com.ecommerce.api.domain.State;
import com.ecommerce.api.enums.ClientType;
import com.ecommerce.api.enums.PaymentState;
import com.ecommerce.api.repositories.AddressRepository;
import com.ecommerce.api.repositories.CategoryRepository;
import com.ecommerce.api.repositories.CityRepository;
import com.ecommerce.api.repositories.ClientRepository;
import com.ecommerce.api.repositories.OrderItemRepository;
import com.ecommerce.api.repositories.OrderRepository;
import com.ecommerce.api.repositories.PaymentRepository;
import com.ecommerce.api.repositories.ProductRepository;
import com.ecommerce.api.repositories.StateRepository;

@Service
public class DBService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	/**
	 * Cria as categorias ao iniciar a aplicação
	 * 
	 * @throws ParseException 
	 * 
	 */
	public void instantiateTestDatabase() throws ParseException {

		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		Category cat3 = new Category(null, "Cama, mesa e banho");
		Category cat4 = new Category(null, "Eletrônicos");
		Category cat5 = new Category(null, "Jardinagem");
		Category cat6 = new Category(null, "Decoração");
		Category cat7 = new Category(null, "Perfumaria");

		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));

		State s1 = new State(null, "São Paulo");
		State s2 = new State(null, "Minas Gerais");

		City c1 = new City(null, "Campinas", s1);
		City c2 = new City(null, "Belo Horizonte", s2);
		City c3 = new City(null, "Jaguariúna", s1);

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		s1.getCities().addAll(Arrays.asList(c1, c3));
		s2.getCities().addAll(Arrays.asList(c2));

		stateRepository.saveAll(Arrays.asList(s1, s2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

		Client client1 = new Client(null, "Tiago Saviane", "tiago@test.com", "123456452-05", ClientType.PESSOAFISICA);

		client1.getPhones().addAll(Arrays.asList("19 1234-1234", "19 8765-4321"));

		Address addr1 = new Address(null, "Rua Test", "10", "Apt 21", "Centro", "453214-000", c3, client1);
		Address addr2 = new Address(null, "Rua XV de Nobembro", "155", "", "Jardim Taquaral", "12345-567", c1, client1);

		client1.getAddresses().addAll(Arrays.asList(addr1, addr2));

		clientRepository.saveAll(Arrays.asList(client1));
		addressRepository.saveAll(Arrays.asList(addr1, addr2));

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Order o1 = new Order(null, dateFormat.parse("20/03/2019 20:25"), client1, addr1);
		Order o2 = new Order(null, dateFormat.parse("25/03/2018 14:08"), client1, addr2);

		Payment pay1 = new PaymentCard(null, PaymentState.OK, o1, 6);
		o1.setPayment(pay1);

		Payment pay2 = new PaymentBankSlip(null, PaymentState.PENDING, o2, dateFormat.parse("28/03/2019 00:00"), null);
		o2.setPayment(pay2);

		client1.getOrders().addAll(Arrays.asList(o1, o2));

		orderRepository.saveAll(Arrays.asList(o1, o2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));

		OrderItem item1 = new OrderItem(o1, p1, 0.00, 1, 2000.00);
		OrderItem item2 = new OrderItem(o1, p3, 0.00, 2, 80.00);
		OrderItem item3 = new OrderItem(o2, p2, 100.00, 1, 800.00);

		o1.getItems().addAll(Arrays.asList(item1, item2));
		o2.getItems().addAll(Arrays.asList(item3));

		p1.getItems().addAll(Arrays.asList(item1));
		p1.getItems().addAll(Arrays.asList(item3));
		p1.getItems().addAll(Arrays.asList(item2));

		orderItemRepository.saveAll(Arrays.asList(item1, item2, item3));
	}

}
