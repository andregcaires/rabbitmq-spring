package com.andregcaires.rabbitmqproducer.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloRabbitProducer {

    // Handle publish to RabbitMQ
    @Autowired
    private RabbitTemplate rabbitTemplate;

    // Sends a message to RabbitMQ
    public void sendHello(String name) {

        // Parameters: queue name and message to send
        rabbitTemplate.convertAndSend("hello.world", "Hello, "+ name);
    }

}