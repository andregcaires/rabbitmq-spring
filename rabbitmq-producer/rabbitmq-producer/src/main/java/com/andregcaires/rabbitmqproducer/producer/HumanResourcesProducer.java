package com.andregcaires.rabbitmqproducer.producer;

import com.andregcaires.rabbitmqproducer.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * In order to send a message to an exchange, one must
 * send 3 parameters on convertAndSend method:
 * the exchange name, routing key, and message.
 * In this example, there was no routing key configured, 
 * so an empty string will be sent
 */
@Service
public class HumanResourcesProducer {

    @Autowired
    private RabbitTemplate _rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(Employee employee) {

        try {
            System.out.println("ID sent: "+ employee.getEmployeeId());
            var json = objectMapper.writeValueAsString(employee);
            _rabbitTemplate.convertAndSend("x.hr", "", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}