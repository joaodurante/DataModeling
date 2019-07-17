package com.joaodurante.springfirst;

import com.joaodurante.springfirst.domain.*;
import com.joaodurante.springfirst.domain.enums.CustomerType;
import com.joaodurante.springfirst.domain.enums.PaymentState;
import com.joaodurante.springfirst.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class SpringFirstProjectApplication implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private RequestItemRepository requestItemRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringFirstProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Category cat1 = new Category(null, "Technology");
        Category cat2 = new Category(null, "Office");
        Product product1 = new Product(null, "Computer", 500.0);
        Product product2 = new Product(null, "Printer", 100.0);
        Product product3 = new Product(null, "Mouse", 80.0);

        cat1.getProducts().addAll(Arrays.asList(product1, product2, product3));
        cat2.getProducts().add(product2);
        product1.getCategories().add(cat1);
        product2.getCategories().addAll(Arrays.asList(cat1, cat2));
        product3.getCategories().add(cat1);

        categoryRepository.saveAll(Arrays.asList(cat1, cat2));
        productRepository.saveAll(Arrays.asList(product1, product2, product3));

        State state1 = new State(null, "São Paulo");
        State state2 = new State(null, "Minas Gerais");
        City city1 = new City(null, "Uberlandia", state2);
        City city2 = new City(null, "São Paulo", state1);
        City city3 = new City(null, "Campinas", state1);

        state1.getCities().addAll(Arrays.asList(city1, city2));
        state2.getCities().add(city3);

        stateRepository.saveAll(Arrays.asList(state1, state2));
        cityRepository.saveAll(Arrays.asList(city1, city2, city3));

        Customer customer1 = new Customer(null, "Maria Silva", "maria@gmail.com", "36378912377", CustomerType.PHYSICALPERSON);
        customer1.getPhones().addAll(Arrays.asList("27363323", "93838393"));

        Address address1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", customer1, city1);
        Address address2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", customer1, city2);
        customer1.getAddress().addAll(Arrays.asList(address1, address2));

        customerRepository.saveAll(Arrays.asList(customer1));
        addressRepository.saveAll(Arrays.asList(address1, address2));

        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Request request1 = new Request(null, date.parse("30/09/2017 10:32"), customer1, address1);
        Request request2 = new Request(null, date.parse("10/10/2017 19:35"), customer1, address2);

        Payment payment1 = new CardPayment(null, PaymentState.PAID, request1, 6);
        request1.setPayment(payment1);
        Payment payment2 = new TicketPayment(null, PaymentState.PENDING, request2, null, date.parse("20/10/2017 00:00"));
        request2.setPayment(payment2);

        customer1.getRequests().addAll(Arrays.asList(request1, request2));

        requestRepository.saveAll(Arrays.asList(request1, request2));
        paymentRepository.saveAll(Arrays.asList(payment1, payment2));

        RequestItem requestItem1 = new RequestItem(request1, product1, 0.00, 1, 2000.00);
        RequestItem requestItem2 = new RequestItem(request1, product3, 0.00, 2, 80.00);
        RequestItem requestItem3 = new RequestItem(request2, product2, 100.00, 1, 800.00);

        request1.getRequestItems().addAll(Arrays.asList(requestItem1, requestItem2));
        request2.getRequestItems().addAll(Arrays.asList(requestItem3));

        product1.getRequestItems().addAll(Arrays.asList(requestItem1));
        product2.getRequestItems().addAll(Arrays.asList(requestItem3));
        product3.getRequestItems().addAll(Arrays.asList(requestItem2));

        requestItemRepository.saveAll(Arrays.asList(requestItem1, requestItem2, requestItem3));
    }
}
