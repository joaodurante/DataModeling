package com.joaodurante.springfirst;

import com.joaodurante.springfirst.domain.Category;
import com.joaodurante.springfirst.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringFirstProjectApplication implements CommandLineRunner {

    @Autowired
    private CategoryRepository repo;

    public static void main(String[] args) {
        SpringApplication.run(SpringFirstProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Category cat1 = new Category(null, "Technology");
        Category cat2 = new Category(null, "Office");
        Category cat3 = new Category(null, "Kids");

        repo.saveAll(Arrays.asList(cat1, cat2, cat3));
    }
}
