package com.andregcaires.rabbitmqproducer.producer;

import com.andregcaires.rabbitmqproducer.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeJsonProducer {

    @Autowired
    private RabbitTemplate _rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(Employee employee) {

        try {
            System.out.println("ID sent: "+ employee.getEmployeeId());
            var json = objectMapper.writeValueAsString(employee);
            _rabbitTemplate.convertAndSend("json.employee", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}