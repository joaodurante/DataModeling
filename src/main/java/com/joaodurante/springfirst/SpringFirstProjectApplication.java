package com.joaodurante.springfirst;

import com.joaodurante.springfirst.domain.Category;
import com.joaodurante.springfirst.domain.City;
import com.joaodurante.springfirst.domain.Product;
import com.joaodurante.springfirst.domain.State;
import com.joaodurante.springfirst.repositories.CategoryRepository;
import com.joaodurante.springfirst.repositories.CityRepository;
import com.joaodurante.springfirst.repositories.ProductRepository;
import com.joaodurante.springfirst.repositories.StateRepository;
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

        State state1 = new State(null, "São Paulo");
        State state2 = new State(null, "Santa Catarina");
        City city1 = new City(null, "Limeira", state1);
        City city2 = new City(null, "Campinas", state1);
        City city3 = new City(null, "Florianópolis", state2);

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().add(p2);

        p1.getCategories().add(cat1);
        p2.getCategories().addAll(Arrays.asList(cat1, cat2));
        p3.getCategories().add(cat1);

        state1.getCities().addAll(Arrays.asList(city1, city2));
        state2.getCities().add(city3);

        categoryRepository.saveAll(Arrays.asList(cat1, cat2));
        productRepository.saveAll(Arrays.asList(p1, p2, p3));
        stateRepository.saveAll(Arrays.asList(state1, state2));
        cityRepository.saveAll(Arrays.asList(city1, city2, city3));
    }
}
