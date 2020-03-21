package com.andregcaires.rabbitmqconsumer.consumer;

import java.io.IOException;

import com.andregcaires.rabbitmqconsumer.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MarketingConsumer {

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger Log = LoggerFactory.getLogger(EmployeeJsonConsumer.class);

    @RabbitListener(queues = "q.hr.marketing")
    public void listen(String message) {
        try {
            var emp = objectMapper.readValue(message, Employee.class);
            Log.info("On marketing, employee is: "+ emp.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}