package com.felix.fonteneau.contentdrivenrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.felix.fonteneau.contentdrivenrest.controller",
        "com.felix.fonteneau.contentdrivenrest.service",
        "com.felix.fonteneau.contentdrivenrest.dao",
        "com.felix.fonteneau.contentdrivenrest.util"})
public class ContentDrivenRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentDrivenRestApplication.class, args);
    }
}
