package com.cg.bankapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.cg.bankapp.service.BankappService;

/**
 * The main class for the BankappApplication that serves as the entry point for the Spring Boot application.
 * @springBootApplication An annotation that combines three commonly used annotations: @Configuration, @EnableAutoConfiguration, and @ComponentScan.
 * @scanBasePackages The base package(s) to be scanned by Spring Boot for components and configurations. In this case, "com.cg.*" is specified as the base package to scan.
 * @apiNote This class contains the main() method, which is the starting point of the Spring Boot application. It uses the SpringApplication.run() method to start the application.
*/
@SpringBootApplication(scanBasePackages = "com.cg.")
public class BankappApplication {

public static void main(String[] args) {
SpringApplication.run(BankappApplication.class, args);
}

}