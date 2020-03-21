package com.andregcaires.rabbitmqproducer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.andregcaires.rabbitmqproducer.entity.Employee;
import com.andregcaires.rabbitmqproducer.producer.EmployeeJsonProducer;
import com.andregcaires.rabbitmqproducer.producer.HelloRabbitProducer;
import com.andregcaires.rabbitmqproducer.producer.HumanResourcesProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RabbitmqProducerApplication implements CommandLineRunner {

	@Autowired
	private HelloRabbitProducer helloRabbitProducer;

	@Autowired
	private EmployeeJsonProducer employeeJsonProducer;

	@Autowired
	private HumanResourcesProducer humanResourcesProducer;

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		helloRabbitProducer.sendHello("World! "+ Math.random());

		List<Employee> list = new ArrayList<>();
		list.add(new Employee("1", "One", LocalDate.now()));
		list.add(new Employee("2", "Two", LocalDate.now()));
		list.add(new Employee("3", "Three", LocalDate.now()));

		//list.forEach(employeeJsonProducer::sendMessage);

		list.forEach(humanResourcesProducer::sendMessage);
	}

}
