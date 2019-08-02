package com.joaodurante.springproject.services;

import com.joaodurante.springproject.domain.*;
import com.joaodurante.springproject.domain.enums.CustomerType;
import com.joaodurante.springproject.domain.enums.PaymentState;
import com.joaodurante.springproject.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

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
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private DemandRepository demandRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private DemandItemRepository demandItemRepository;

    public void instantiateTestDatabase() throws ParseException {
        Category cat1 = new Category(null, "Technology");
        Category cat2 = new Category(null, "Office");
        Category cat3 = new Category(null, "Home");
        Category cat4 = new Category(null, "Eletronics");
        Category cat5 = new Category(null, "Garden");
        Category cat6 = new Category(null, "Decoration");
        Category cat7 = new Category(null, "Perfume");

        Product product1 = new Product(null, "Computer", 500.0);
        Product product2 = new Product(null, "Printer", 100.0);
        Product product3 = new Product(null, "Mouse", 80.0);
        Product product4 = new Product(null, "Desk", 300.0);
        Product product5 = new Product(null, "Towel", 50.0);
        Product product6 = new Product(null, "Blanket", 200.0);
        Product product7 = new Product(null, "TV", 1200.0);
        Product product8 = new Product(null, "Brush cutter", 800.0);
        Product product9 = new Product(null, "Lamp", 100.0);
        Product product10 = new Product(null, "Pendant", 180.0);
        Product product11 = new Product(null, "Shampoo", 90.0);

        cat1.getProducts().addAll(Arrays.asList(product1, product2, product3));
        cat2.getProducts().addAll(Arrays.asList(product2, product4));
        cat3.getProducts().addAll(Arrays.asList(product5, product6));
        cat4.getProducts().addAll(Arrays.asList(product1, product2, product3, product7));
        cat5.getProducts().add(product8);
        cat6.getProducts().addAll(Arrays.asList(product9, product10));
        cat7.getProducts().add(product11);


        product1.getCategories().addAll(Arrays.asList(cat1, cat4));
        product2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
        product3.getCategories().addAll(Arrays.asList(cat1, cat4));
        product4.getCategories().add(cat2);
        product5.getCategories().add(cat3);
        product6.getCategories().add(cat3);
        product7.getCategories().add(cat4);
        product8.getCategories().add(cat5);
        product9.getCategories().add(cat6);
        product10.getCategories().add(cat6);
        product11.getCategories().add(cat7);

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        productRepository.saveAll(Arrays.asList(
                product1, product2, product3, product4, product5, product6, product7, product8, product9, product10, product11
        ));

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

        Demand demand1 = new Demand(null, date.parse("30/09/2017 10:32"), customer1, address1);
        Demand demand2 = new Demand(null, date.parse("10/10/2017 19:35"), customer1, address2);

        Payment payment1 = new CardPayment(null, PaymentState.PAID, demand1, 6);
        demand1.setPayment(payment1);
        Payment payment2 = new TicketPayment(null, PaymentState.PENDING, demand2, null, date.parse("20/10/2017 00:00"));
        demand2.setPayment(payment2);

        customer1.getDemands().addAll(Arrays.asList(demand1, demand2));

        demandRepository.saveAll(Arrays.asList(demand1, demand2));
        paymentRepository.saveAll(Arrays.asList(payment1, payment2));

        DemandItem demandItem1 = new DemandItem(demand1, product1, 0.00, 1, 2000.00);
        DemandItem demandItem2 = new DemandItem(demand1, product3, 0.00, 2, 80.00);
        DemandItem demandItem3 = new DemandItem(demand2, product2, 100.00, 1, 800.00);

        demand1.getDemandItems().addAll(Arrays.asList(demandItem1, demandItem2));
        demand2.getDemandItems().addAll(Arrays.asList(demandItem3));

        product1.getDemandItems().addAll(Arrays.asList(demandItem1));
        product2.getDemandItems().addAll(Arrays.asList(demandItem3));
        product3.getDemandItems().addAll(Arrays.asList(demandItem2));

        demandItemRepository.saveAll(Arrays.asList(demandItem1, demandItem2, demandItem3));
    }
}
