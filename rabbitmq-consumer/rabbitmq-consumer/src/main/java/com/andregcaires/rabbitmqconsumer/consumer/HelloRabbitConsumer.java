package com.andregcaires.rabbitmqconsumer.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class HelloRabbitConsumer {

    @RabbitListener(queues = "hello.world")
    public void listen(String message) {        
        System.out.println("\n\nConsuming: "+ message + "\n\n");
    }

}