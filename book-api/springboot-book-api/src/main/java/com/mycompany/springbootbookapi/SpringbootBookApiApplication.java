package com.mycompany.springbootbookapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
public class SpringbootBookApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBookApiApplication.class, args);
    }

}
