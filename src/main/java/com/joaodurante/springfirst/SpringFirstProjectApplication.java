package com.joaodurante.springfirst;

import com.joaodurante.springfirst.domain.*;
import com.joaodurante.springfirst.domain.enums.CustomerType;
import com.joaodurante.springfirst.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

    public static void main(String[] args) {
        SpringApplication.run(SpringFirstProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Category cat1 = new Category(null, "Technology");
        Category cat2 = new Category(null, "Office");
        Product p1 = new Product(null, "Computer", 500.0);
        Product p2 = new Product(null, "Printer", 100.0);
        Product p3 = new Product(null, "Mouse", 80.0);

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().add(p2);
        p1.getCategories().add(cat1);
        p2.getCategories().addAll(Arrays.asList(cat1, cat2));
        p3.getCategories().add(cat1);

        categoryRepository.saveAll(Arrays.asList(cat1, cat2));
        productRepository.saveAll(Arrays.asList(p1, p2, p3));

        State state1 = new State(null, "São Paulo");
        State state2 = new State(null, "Santa Catarina");
        City city1 = new City(null, "Limeira", state1);
        City city2 = new City(null, "Campinas", state1);
        City city3 = new City(null, "Florianópolis", state2);

        state1.getCities().addAll(Arrays.asList(city1, city2));
        state2.getCities().add(city3);

        stateRepository.saveAll(Arrays.asList(state1, state2));
        cityRepository.saveAll(Arrays.asList(city1, city2, city3));

        Customer customer1 = new Customer(null, "João", "joao@gmail.com", "48477428859", CustomerType.PHYSICALPERSON);
        Customer customer2 = new Customer(null, "Maria", "maria@gmail.com", "11022033040", CustomerType.LEGALENTITY);
        Address address1 = new Address(null, "Rua XY", "12A", "Marketplace", "Avenida 13B", "13482462", customer1, city1);
        Address address2 = new Address(null, "Rua Flores", "128", "Condominio", "Avenida Margarida", "13445231", customer2, city2);

        customer1.getPhones().addAll(Arrays.asList("19988504771", "1937013295"));
        customer2.getPhones().addAll(Arrays.asList("1934432200", "1932714566"));
        customer1.getAddress().add(address1);
        customer2.getAddress().add(address2);

        customerRepository.saveAll(Arrays.asList(customer1, customer2));
        addressRepository.saveAll(Arrays.asList(address1, address2));
    }
}
