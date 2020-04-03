package com.andregcaires.rabbitmqproducer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.andregcaires.rabbitmqproducer.entity.Employee;
import com.andregcaires.rabbitmqproducer.entity.Picture;
import com.andregcaires.rabbitmqproducer.producer.EmployeeJsonProducer;
import com.andregcaires.rabbitmqproducer.producer.HelloRabbitProducer;
import com.andregcaires.rabbitmqproducer.producer.HumanResourcesProducer;
import com.andregcaires.rabbitmqproducer.producer.MyPictureProducer;
import com.andregcaires.rabbitmqproducer.producer.PictureProducer;
import com.andregcaires.rabbitmqproducer.producer.PictureTopicProducer;

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

	@Autowired
	private PictureProducer pictureProducer;

	@Autowired
	private PictureTopicProducer pictureTopicProducer;

	
	@Autowired
	private MyPictureProducer myPictureProducer;

	private final List<String> SOURCES = List.of("mobile","web");
	private final List<String> TYPES = List.of("jpg", "png", "svg");

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		helloRabbitProducer.sendHello("World! "+ Math.random());

		List<Employee> listEmployees = new ArrayList<>();
		listEmployees.add(new Employee("1", "One", LocalDate.now()));
		listEmployees.add(new Employee("2", "Two", LocalDate.now()));
		listEmployees.add(new Employee("3", "Three", LocalDate.now()));

		//listEmployees.forEach(employeeJsonProducer::sendMessage);

		//listEmployees.forEach(humanResourcesProducer::sendMessage);
		/*
		for (var i = 0; i < 10; i++) {
			pictureProducer.sendMessage(new Picture("Pic"+ i, 
				TYPES.get(i % TYPES.size()), 
				SOURCES.get(i % SOURCES.size()),
				ThreadLocalRandom.current().nextLong(0, 10001))
			);
		}*/
		
		/*
		for (var i = 0; i < 10; i++) {
			pictureTopicProducer.sendMessage(new Picture("PicTopic"+ i, 
				TYPES.get(i % TYPES.size()), 
				SOURCES.get(i % SOURCES.size()),
				ThreadLocalRandom.current().nextLong(0, 10001))
			);
		}*/

		for (var i = 0; i < 1; i++) {
			myPictureProducer.sendMessage(new Picture("PicTopic"+ i, 
				TYPES.get(i % TYPES.size()), 
				SOURCES.get(i % SOURCES.size()),
				ThreadLocalRandom.current().nextLong(9000, 10001))
			);
		}

	}

}
