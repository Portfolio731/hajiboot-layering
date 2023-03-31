package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.example.demo.CustomerRepository;
import com.example.domain.Customer;

@EnableAutoConfiguration
@ComponentScan
public class App implements CommandLineRunner {
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {
		//データ追加
		Customer created = customerRepository.save(new Customer(null,"Hidetoshi","Dekisugi"));
		System.out.println(created+"is created");
		customerRepository.findAll().forEach(System.out::println);
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
