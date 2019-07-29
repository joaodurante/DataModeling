package com.joaodurante.springfirst;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataModelingApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DataModelingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
